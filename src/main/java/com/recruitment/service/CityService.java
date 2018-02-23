package com.recruitment.service;

import com.recruitment.pojo.City;
import com.recruitment.pojo.Province;

import java.util.List;

/**
 * Created by ZhouHua
 */
public interface CityService {
	//查询所有省份
	public List<Province> findProvinceList()throws Exception;
	//根据省份查询市区
	public List<City> findCityList(Integer provinceId)throws Exception;
}
