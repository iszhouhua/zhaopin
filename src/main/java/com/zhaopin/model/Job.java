package com.zhaopin.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Job {
    private String id;

    private String city;

    private String name;

    private String url;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String welfare;

    private String education;

    private String experience;

    private String nature;

    private int demand;

    private String type;

    private String address;

    private String description;

    private String companyId;

    private LocalDateTime releaseTime;

    private LocalDateTime createTime;

    private Company company;

    private int total;
}