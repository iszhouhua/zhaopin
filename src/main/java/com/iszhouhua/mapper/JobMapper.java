package com.iszhouhua.mapper;

import com.iszhouhua.model.Job;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JobMapper {
    /**
     * 批量插入职位信息
     * @param jobs 职位集合
     * @return
     */
    int insertBatch(List<Job> jobs);

    /**
     * 根据ID或者URL修改职位
     * @param job 职位信息
     * @return 受影响的行数
     */
    int updateByIdOrUrl(Job job);

    /**
     * 统计工作经验与工资的关系
     * @param cityName 城市
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForExpAndSalary(@Param("city")String cityName, @Param("jobName")String jobName);

    /**
     * 统计学历与工资的关系
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<Job> countJobForEducationAndSalary(@Param("city")String cityName, @Param("jobName")String jobName);

    /**
     * 查询描述
     * @param cityName 城市名
     * @param jobName 工作关键字
     * @return
     */
    List<String> selectDescriptionByCityAndJobName(@Param("city")String cityName,@Param("jobName")String jobName);

    /**
     * 查询所有城市
     * @return
     */
    @Select("select distinct city from job")
    List<String> selectCity();

    /**
     * 统计招聘数前20的城市
     * @return
     */
    @Select("SELECT city,COUNT(city) total FROM job GROUP BY city ORDER BY total DESC")
    List<Map> selectCityJobNumber();
}