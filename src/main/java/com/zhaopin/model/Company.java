package com.zhaopin.model;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class Company {
    private String id;

    private String name;

    private String nature;

    private String scale;

    private String url;

    private String industry;

    private String address;

    private String description;

    private LocalDateTime createTime;

    private int total;
}