package com.zhaopin.dao;

import com.zhaopin.model.City;

import java.util.List;

public interface CityMapper {
    /**
     * 统计各省的工作数量
     * @return
     */
    List<City> countProvinceJobNumber();
    /**
     * 统计各城市的工作数量
     * @return
     */
    List<City> countCityJobNumber();

    /**
     * 获得所有城市信息
     * @return
     */
    List<City> selectCity();
}