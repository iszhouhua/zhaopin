package com.recruitment.controller;

import com.recruitment.pojo.City;
import com.recruitment.pojo.Province;
import com.recruitment.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ZhouHua.
 */

@Controller
@RequestMapping("/city")
public class CityController {

	//注入CityService
	@Autowired
	private CityService cityService;

	@RequestMapping("/province")
	public @ResponseBody
		List<Province> queryProvinces() throws Exception{
			System.out.println("------省份查询start-----");
			List<Province> provincelist=cityService.findProvinceList();
			System.out.println("------省份查询end-----");
			//@ResponseBody将itemsCustom转成json输出
			return provincelist;
	}

	@RequestMapping("/city")
	public @ResponseBody List<City> queryCitys(@RequestBody Integer provinceId) throws Exception{
		System.out.println("------市区查询start-----");
		System.out.println(provinceId);
		List<City> citylist=cityService.findCityList(provinceId);
		System.out.println("------市区查询end-----");
		//@ResponseBody将itemsCustom转成json输出
		return citylist;
	}
}
