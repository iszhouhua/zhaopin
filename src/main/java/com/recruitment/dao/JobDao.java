package com.recruitment.dao;

import com.recruitment.pojo.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhouHua
 */

@Repository
public interface JobDao {
	/**
	 * 插入职位信息
	 * @throws Exception
	 */
	public void insertJoblist(List<Job> jobs)throws Exception;
	
	/**
	 * 统计公司不同公司类型的总数
	 * @param cityname
	 * @return
	 * @throws Exception
	 */
	public List<Job> findCompanynatureTotal(@Param("cityname")String cityname)throws Exception;
	
	/**
	 * 统计不同规模的公司的数目
	 * @param cityname
	 * @return
	 * @throws Exception
	 */
	public List<Job> findScaleTotal(@Param("cityname")String cityname)throws Exception;
	
	/**
	 * 统计不同行业公司的数量
	 * @param cityname
	 * @return
	 * @throws Exception
	 */
	public List<Job> findindustry(@Param("cityname")String cityname)throws Exception;
	
	/**
	 * 统计工作经验与工资的关系
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public List<Job> findpriceExp(Job job)throws Exception;
	
	/**
	 * 统计学历与工资的关系
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public List<Job> findpriceEducation(Job job)throws Exception;
	
	/**
	 * 统计工作经验和用人需求的关系
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public List<Job> finddemandExp(Job job)throws Exception;
	
	/**
	 * 学历与用人需求的关系
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public List<Job> finddemandEducation(Job job)throws Exception;
	
}
