package com.iszhouhua.spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iszhouhua.config.Const;
import com.iszhouhua.mapper.JobMapper;
import com.iszhouhua.model.Job;
import com.iszhouhua.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 智联爬虫
 * @author ZhouHua
 * @date 2018/7/26
 */
@Slf4j
@Component
public class ZhaopinSpider {
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SetOperations<String, Object> setOperations;

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    public String start(String url) {
        JSONObject jsonObject=HttpUtils.get(url);
        //结果集为空或返回code非200，表示请求失败
        if(jsonObject==null||jsonObject.getIntValue("code")!=200)return "数据请求失败";
        JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("results");
        if(CollectionUtils.isEmpty(jsonArray))return "符合条件的数据为空";
        //计算总页数
        int totalPageNum = (jsonObject.getJSONObject("data").getIntValue("numTotal")  +  90  - 1) / 90;
        if(totalPageNum>1){
            //获取后面几页的数据
            for (int i = 2; i <= totalPageNum; i++) {
                JSONObject object=HttpUtils.get(url);
                jsonArray.addAll(object.getJSONObject("data").getJSONArray("results"));
            }
        }
        List<Job> jobList=new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject json=(JSONObject) obj;
            String positionUrl=json.getString("positionURL");
            //往redis中成功插入详情页的url，表示未抓取过
            if(setOperations.add(Const.ZHAOPIN_DISTINCT_KEY,positionUrl)>0){
                //添加到待抓取列表
                setOperations.add(Const.ZHAOPIN_PENDING_KEY,positionUrl);
                jobList.add(parseJob(json));
            }
        }
        //集合不为空，批量插入数据，并开启定时任务抓取详情信息
        if(!CollectionUtils.isEmpty(jobList)){
            jobMapper.insertBatch(jobList);
        }
        if(setOperations.size(Const.ZHAOPIN_PENDING_KEY)>0){
            try {
                startQuartz();
            } catch (SchedulerException e) {
                log.error("定时任务开启失败",e);
            }
        }
        return "基本信息抓取成功，工作描述等信息将由定时任务完成。";
    }
    /**
     * 从json对象中解析出job数据
     * @param json 包含job数据的json对象
     * @return 职位信息
     */
    private static Job parseJob(JSONObject json){
        Job job=new Job();
        String positionUrl=json.getString("positionURL");
        job.setUrl(positionUrl);
        job.setJobName(json.getString("jobName"));
        job.setCity(json.getJSONObject("city").getJSONArray("items").getJSONObject(0).getString("name"));
        String[] salary = json.getString("salary").replace("K","000").split("-");
        if (salary.length > 1) {
            //检查薪资是否存在小数点，如5.5K
            for (int i = 0; i < salary.length; i++) {
                if(salary[i].contains(".")){
                    salary[i]=salary[i].replace(".","").substring(i,salary[i].length() - 1);
                }
            }
            try {
                job.setMinSalary(Integer.valueOf(salary[0]));
                job.setMaxSalary(Integer.valueOf(salary[1]));
            }catch (NumberFormatException e){
                log.error("将薪资转换成int类型时出错",e);
            }
        }
        job.setExperience(json.getJSONObject("workingExp").getString("name"));
        job.setEducation(json.getJSONObject("eduLevel").getString("name"));
        try {
            Date date=DateUtils.parseDate(json.getString("createDate"), Locale.SIMPLIFIED_CHINESE, "yyyy-MM-dd hh:mm:ss");
            job.setReleaseTime(date);
        } catch (ParseException e) {
            log.error("日期转换出错",e);
        }
        job.setCompanyName(json.getJSONObject("company").getString("name"));
        return job;
    }

    /**
     * 开始执行抓取详情数据的定时任务，每5秒抓取一次，防止被反爬虫
     */
    public void startQuartz() throws SchedulerException {
        TriggerKey triggerKey=new TriggerKey(ZhaopinQuartz.class.getName());
        //检查是否已存在
        boolean isExists=scheduler.checkExists(triggerKey);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .withRepeatCount(setOperations.size(Const.ZHAOPIN_PENDING_KEY).intValue()-1))
                .build();
        if(isExists){
            //重新设置触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        }else{
            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(ZhaopinQuartz.class).withIdentity(ZhaopinQuartz.class.getName()).build();
            //将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
