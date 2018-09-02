package com.zhaopin.controller;

import com.zhaopin.config.Const;
import com.zhaopin.dao.CityMapper;
import com.zhaopin.dao.CompanyMapper;
import com.zhaopin.dao.JobMapper;
import com.zhaopin.dto.Result;
import com.zhaopin.model.City;
import com.zhaopin.model.Company;
import com.zhaopin.model.Job;
import com.zhaopin.service.JobService;
import com.zhaopin.spider.SpiderThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ZhouHua
 * @date 2018/7/30
 */
@RestController
@RequestMapping("/")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private JobService jobService;

    /**
     * 爬取数据
     * @param param 拼接好的参数
     * @return
     */
    @PostMapping("updateJob")
    public Result updateJob(String param) {
        Result result=new Result();
        String url = Const.BASE_URL+"?" + param;
        logger.info("搜索链接为：{}",url);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                    .timeout(10000).get();
            String count=doc.select("span.search_yx_tj>em").text().trim();
            int num=Integer.parseInt(count);
            // 取得总页数
            int page=num/60;
            if(page>90){
                // 超过90页只取90页
                page=90;
            }else if(page%60>0){
                // 不足90页且余数不为0，说明最后面还有一页，页数+1
                page++;
            }
            logger.info("符合条件的职位数：{}个，总页数为：{}页",num,page);
            // 用10个线程进行爬取
            ExecutorService executor=Executors.newFixedThreadPool(10);
            for(int i=1;i<=page;i++){
                executor.execute(new SpiderThread(url,i));
            }
            executor.shutdown();
            while (!executor.isTerminated()){
                logger.info("爬虫未完成，继续等待...");
                Thread.sleep(5000);
            }
        }catch (IOException e) {
            result.setCode(Const.FAIL);
            result.setMsg("发送get请求失败");
            e.printStackTrace();
        } catch (InterruptedException e) {
            result.setCode(Const.FAIL);
            result.setMsg("等待线程结束失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 各省招聘信息数量统计
     * @return
     */
    @GetMapping("provinceStatistics")
    public List<City> provinceStatistics() {
        return cityMapper.countProvinceJobNumber();
    }

    /**
     * 招聘数量前20的城市
     * @return
     */
    @GetMapping("cityStatistics")
    public List<City> cityStatistics() {
        return cityMapper.countCityJobNumber();
    }

    /**
     * 工作经验与工资的关系
     * @param city 城市
     * @param name 工作关键字
     * @return
     */
    @PostMapping("jobExpAndSalary")
    public Map jobExpAndSalary(String city, String name) {
        List<Job> jobs=jobMapper.countJobForExpAndSalary(city,name);
        List<String> experience=new ArrayList<>();
        List<BigDecimal> minPrice=new ArrayList<>(),maxPrice=new ArrayList<>(),avgPrice=new ArrayList<>();
        for (Job job:jobs){
            experience.add(job.getExperience());
            minPrice.add(job.getMinPrice());
            maxPrice.add(job.getMaxPrice());
            BigDecimal avg=job.getMaxPrice().add(job.getMinPrice()).divide(new BigDecimal(2));
            avgPrice.add(avg);
        }
        Map<String,List> map=new HashMap<>(4);
        map.put("experience",experience);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("avgPrice",avgPrice);
        return map;
    }

    /**
     * 学历与工资的关系
     * @param city 城市
     * @param name 工作关键字
     * @return
     */
    @PostMapping("jobEducationAndSalary")
    public Map jobEducationAndSalary(String city, String name) {
        List<Job> jobs=jobMapper.countJobForEducationAndSalary(city,name);
        List<String> education=new ArrayList<>();
        List<BigDecimal> minPrice=new ArrayList<>(),maxPrice=new ArrayList<>(),avgPrice=new ArrayList<>();
        for (Job job:jobs){
            education.add(job.getEducation());
            minPrice.add(job.getMinPrice());
            maxPrice.add(job.getMaxPrice());
            BigDecimal avg=job.getMaxPrice().add(job.getMinPrice()).divide(new BigDecimal(2));
            avgPrice.add(avg);
        }
        Map<String,List> map=new HashMap<>(4);
        map.put("education",education);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("avgPrice",avgPrice);
        return map;
    }

    /**
     * 工作经验与用人需求的关系
     * @param city 城市
     * @param name 工作关键字
     * @return
     */
    @PostMapping("jobExpAndDemand")
    public List<Job> jobExpAndDemand(String city, String name) {
        return jobMapper.countJobForExpAndDemand(city,name);
    }

    /**
     * 学历与用人需求的关系
     * @param city 城市
     * @param name 工作关键字
     * @return
     */
    @PostMapping("jobEducationAndDemand")
    public List<Job> jobEducationAndDemand(String city, String name) {
        return jobMapper.countJobForEducationAndDemand(city,name);
    }

    /**
     * 不同性质的公司数量
     * @return
     */
    @GetMapping("companyNature")
    public List<Company> companyNature() {
        return companyMapper.countCompanyForNature();
    }

    /**
     * 不同规模的公司数量
     * @return
     */
    @GetMapping("companyScale")
    public List<Company> companyScale() {
        return companyMapper.countCompanyForScale();
    }

    /**
     * 不同行业的公司数量
     * @return
     */
    @GetMapping("companyIndustry")
    public List<Company> companyIndustry() {
        return companyMapper.countCompanyForIndustry();
    }

    /**
     * 职位技术要求统计
     * @param city 城市
     * @param name 工作关键字
     * @return
     */
    @PostMapping("skillStatistics")
    public Map skillStatistics(String city, String name) {
        Map result=new HashMap(2);
        Map<String,Integer> map=jobService.skillStatistics(city,name);
        result.put("skill",map.keySet());
        result.put("total",map.values());
        return result;
    }
}