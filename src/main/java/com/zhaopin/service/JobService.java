package com.zhaopin.service;

import java.util.Map;

/**
 * @author ZhouHua
 * @date 2018/8/8
 */
public interface JobService {
    Map<String,Integer> skillStatistics(String city, String name);
}
