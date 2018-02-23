package com.recruitment.service.impl;

import com.recruitment.dao.JobDao;
import com.recruitment.pojo.Job;
import com.recruitment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhouHua on 2018/1/22.
 */

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobDao jobDao;
	
	
	@Override
	public void insertJoblist(List<Job> jobs) throws Exception {
		// TODO Auto-generated method stub
		jobDao.insertJoblist(jobs);
	}


	@Override
	public List<Job> findCompanynatureTotal(String cityname) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.findCompanynatureTotal(cityname);
	}


	@Override
	public List<Job> findScaleTotal(String cityname) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.findScaleTotal(cityname);
	}
	
	@Override
	public List<Job> findindustry(String cityname) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.findindustry(cityname);
	}

	@Override
	public List<Job> findpriceExp(Job job) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.findpriceExp(job);
	}


	@Override
	public List<Job> findpriceEducation(Job job) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.findpriceEducation(job);
	}


	@Override
	public List<Job> finddemandExp(Job job) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.finddemandExp(job);
	}


	@Override
	public List<Job> finddemandEducation(Job job) throws Exception {
		// TODO Auto-generated method stub
		return jobDao.finddemandEducation(job);
	}


	

}
