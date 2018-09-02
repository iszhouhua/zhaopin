package com.zhaopin.dao;

import com.zhaopin.model.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {
    /**
     * 批量插入职位信息
     * @param jobs 职位集合
     * @return
     */
    int insertJobList(List<Job> jobs);

    /**
     * 统计工作经验与工资的关系
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForExpAndSalary(@Param("city")String cityName,@Param("name")String jobName);

    /**
     * 统计工作经验与用人需求的关系
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForExpAndDemand(@Param("city")String cityName,@Param("name")String jobName);

    /**
     * 统计学历与工资的关系
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForEducationAndSalary(@Param("city")String cityName,@Param("name")String jobName);

    /**
     * 统计学历与用人需求的关系
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForEducationAndDemand(@Param("city")String cityName,@Param("name")String jobName);

    /**
     * 查询所有描述
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<String> selectDescriptionByCityAndName(@Param("city")String cityName,@Param("name")String jobName);
}