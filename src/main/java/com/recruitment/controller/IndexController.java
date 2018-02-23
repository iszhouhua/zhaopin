package com.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZhouHua
 */
@Controller
public class IndexController {
	//所有index.*格式的路径均匹配进入首页，index.html，index.htm......
	@RequestMapping("/index.*")
	public String index(){
		System.out.println("进入首页");
		return "index";//首页
	}

	//进入招聘统计页面
	@RequestMapping("/job.html")
	public String loadJob(){
		return "job";//job页面
	}

	//进入公司统计页面
	@RequestMapping("/company.html")
	public String loadCompany(){
		return "company";//company页面
	}

	//进入数据更新页面
	@RequestMapping("/update.html")
	public String loadUpdate(){
		return "update";//update页面
	}
}
