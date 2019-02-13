package com.iszhouhua.config;

/**
 * @author ZhouHua
 * @date 2018/7/25
 */
public class Const {
    /**
     * 爬虫超时时间，单位毫秒
     */
    public static final int TIMEOUT=10000;
    /**
     * 智联搜索页链接
     */
    public static final String ZHAOPIN_URL = "https://sou.zhaopin.com/jobs/searchresult.ashx";
    /**
     * 智联用于去重的redis key
     */
    public static final String ZHAOPIN_DISTINCT_KEY="zhaopin";
    /**
     * 智联用于存储待抓取的详情页的redis key
     */
    public static final String ZHAOPIN_PENDING_KEY="zhaopin_pending";
}
