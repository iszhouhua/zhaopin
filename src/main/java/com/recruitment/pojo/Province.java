package com.recruitment.pojo;

import java.io.Serializable;

/**
 * Created by ZhouHua
 */
public class Province implements Serializable {
	private Integer id;//省份ID

	private String provinceName;//省份

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
