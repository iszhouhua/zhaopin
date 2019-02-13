package com.iszhouhua.model;

import lombok.Data;
import java.util.Date;

@Data
public class Job {
    /**
     * 职位ID
     */
    private String id;

    /**
     * 工作城市
     */
    private String city;

    /**
     * 职位名
     */
    private String jobName;

    /**
     * 职位链接
     */
    private String url;

    /**
     * 最低薪资
     */
    private Integer minSalary;

    /**
     * 最高薪资
     */
    private Integer maxSalary;

    /**
     * 最低学历
     */
    private String education;

    /**
     * 工作经验
     */
    private String experience;

    /**
     * 工作地址
     */
    private String address;

    /**
     * 职位描述
     */
    private String description;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 创建时间（数据抓取时间）
     */
    private Date createTime;

    /**
     * 招聘信息发布时间
     */
    private Date releaseTime;
}