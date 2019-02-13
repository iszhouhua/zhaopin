package com.iszhouhua.controller;

import com.iszhouhua.mapper.JobMapper;
import com.iszhouhua.model.Job;
import com.iszhouhua.service.SkillService;
import com.iszhouhua.spider.ZhaopinSpider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhouHua
 * @date 2018/7/30
 */
@RestController
@Slf4j
public class DataController {
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ZhaopinSpider zhaopinSpider;

    /**
     * 爬取数据
     * @param url 拼接好的参数
     * @return
     */
    @RequestMapping("updateData")
    public String updateData(String url) {
        return zhaopinSpider.start(url);
    }

    /**
     * 各城市招聘信息数量统计
     * @return
     */
    @GetMapping("cityStatistics")
    public List<Map> cityStatistics() {
        return jobMapper.selectCityJobNumber();
    }

    /**
     * 工作经验与工资的关系
     * @param city 城市名
     * @param name 工作关键字
     * @return
     */
    @PostMapping("jobExpAndSalary")
    public Map jobExpAndSalary(String city, String name) {
        name=StringUtils.isBlank(name)?null:"%"+name+"%";
        List<Job> jobs=jobMapper.countJobForExpAndSalary(city,name);
        List<String> experience=new ArrayList<>();
        List<Integer> minPrice=new ArrayList<>(),maxPrice=new ArrayList<>(),avgPrice=new ArrayList<>();
        for (Job job:jobs){
            experience.add(job.getExperience());
            minPrice.add(job.getMinSalary());
            maxPrice.add(job.getMaxSalary());
            Integer avg=(job.getMaxSalary()+job.getMinSalary())/2;
            avgPrice.add(avg);
        }
        Map<String,List> map=new HashMap<>(8);
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
        name=StringUtils.isBlank(name)?null:"%"+name+"%";
        List<Job> jobs=jobMapper.countJobForEducationAndSalary(city,name);
        List<String> education=new ArrayList<>();
        List<Integer> minPrice=new ArrayList<>(),maxPrice=new ArrayList<>(),avgPrice=new ArrayList<>();
        for (Job job:jobs){
            education.add(job.getEducation());
            minPrice.add(job.getMinSalary());
            maxPrice.add(job.getMaxSalary());
            Integer avg=(job.getMaxSalary()+job.getMinSalary())/2;
            avgPrice.add(avg);
        }
        Map<String,List> map=new HashMap<>(8);
        map.put("education",education);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("avgPrice",avgPrice);
        return map;
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
        Map<String,Integer> map=skillService.skillStatistics(city,name);
        result.put("skill",map.keySet());
        result.put("total",map.values());
        return result;
    }
}