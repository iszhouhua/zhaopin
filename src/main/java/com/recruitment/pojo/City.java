package com.recruitment.pojo;

import java.io.Serializable;

/**
 * Created by ZhouHua
 */
public class City implements Serializable {
	private Integer id;//城市ID

	private Integer province_id;//省份ID

	private String cityName;//城市名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvince_id() {
		return province_id;
	}

	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
