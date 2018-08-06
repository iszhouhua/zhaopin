package com.zhaopin.model;

import lombok.Data;

@Data
public class City {
    private Integer id;

    private String province;

    private String city;

    private Integer total;
}