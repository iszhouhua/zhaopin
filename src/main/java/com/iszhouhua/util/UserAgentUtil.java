package com.iszhouhua.util;

/**
 * @author ZhouHua
 * @date 2019/1/26
 */
public class UserAgentUtil {
    /**
     * userAgent池
     */
    private static final String[] userAgents={"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0",
                                              "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36",
                                              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36",
                                              "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                                              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763"};

    /**
     * 随机获取一个userAgent
     * @return
     */
    public static String getUserAgent(){
        int index = (int) (Math.random() * userAgents.length);
        return userAgents[index];
    }
}
