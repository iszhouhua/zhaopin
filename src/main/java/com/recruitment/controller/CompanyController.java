package com.recruitment.controller;

import com.recruitment.pojo.Job;
import com.recruitment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ZhouHua
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private JobService jobService;
	/**
	 *	统计公司不同公司类型的总数
	 * @param job
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCompanynatureTotal")
	public @ResponseBody
	List<Job> findCompanynatureTotal(@RequestBody Job job)throws Exception{

		List<Job> companynaturelist=jobService.findCompanynatureTotal(job.getCityname());

		return companynaturelist;
	}

	/**
	 *	统计不同规模的公司的数目
	 * @param job
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findScaleTotal")
	public @ResponseBody List<Job> findScaleTotal(@RequestBody Job job)throws Exception{

		List<Job> scalelist=jobService.findScaleTotal(job.getCityname());

		return scalelist;
	}

	/**
	 *	统计不同行业公司的数量
	 * @param job
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findindustry")
	public @ResponseBody List<Job> findindustry(@RequestBody Job job)throws Exception{

		List<Job> industrylist=jobService.findindustry(job.getCityname());

		return industrylist;
	}


}
