package com.zhaopin.service.impl;

import com.zhaopin.dao.JobMapper;
import com.zhaopin.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ZhouHua
 * @date 2018/8/8
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;

    @Override
    public Map<String,Integer> skillStatistics(String city, String name) {
        List<String> descriptions = jobMapper.selectDescriptionByCityAndName(city,name);
        String regex = "[a-zA-Z]+";
        Map<String,Integer> map=new LinkedCaseInsensitiveMap<>(4096);
        for (String description : descriptions) {
            List<String> list=getMatchers(regex, description);
            for (String str : list) {
                if (map.containsKey(str)){
                    map.put(str,map.get(str)+1);
                }else{
                    map.put(str,1);
                }
            }
        }
        return sortMapByValue(map,50);
    }

    /**
     * 获得所有匹配正则表达式的内容
     * @param regex 正则表达式
     * @param source 需要匹配的字符串
     * @return
     */
    List<String> getMatchers(String regex, String source){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * 对map的value倒序排序并取出前n条
     * @param map 待排序对象
     * @param size 需要取出的条数
     * @return 结果
     */
    public Map<String, Integer> sortMapByValue(Map<String, Integer> map,Integer size) {
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        List<Map.Entry<String,Integer>> lists= new ArrayList<>(map.entrySet());
//        Collections.sort(lists, (o1, o2) -> {
//            int p=o2.getValue()-o1.getValue();
//            return p>0?1:-1;
//        });

        lists=lists.stream().sorted(Comparator.comparing(Map.Entry<String,Integer>::getValue).reversed()).collect(Collectors.toList());

        if(lists.size()>=size){
            //lists.subList()用法
            for(Map.Entry<String, Integer> set:lists.subList(0, size)){
                sortedMap.put(set.getKey(), set.getValue());
            }
        }else {
            for(Map.Entry<String, Integer> set:lists){
                sortedMap.put(set.getKey(), set.getValue());
            }
        }
        return sortedMap;
    }
}
