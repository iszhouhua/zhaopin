package com.recruitment.service.impl;

import com.recruitment.dao.CityDao;
import com.recruitment.pojo.City;
import com.recruitment.pojo.Province;
import com.recruitment.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhouHua
 */

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	public List<Province> findProvinceList() throws Exception {
		return cityDao.findProvinceList();
	}

	@Override
	public List<City> findCityList(Integer provinceId) throws Exception {
		return cityDao.findCityList(provinceId);
	}

}
