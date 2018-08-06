package com.zhaopin;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouHua
 */
public class StrTest extends TestCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testPlus() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        logger.info("+ cost {} ms", te - ts);
    }

    public void testConcat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s.concat(String.valueOf(i));
        }
        long te = System.currentTimeMillis();
        logger.info("concat cost {} ms", te - ts);
    }

    public void testFormat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = String.format("%s%d",s, i);
        }
        long te = System.currentTimeMillis();
        logger.info("format cost {} ms", te - ts);
    }

    public void testJoin() {
        List<String> list = new ArrayList<String>();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te = System.currentTimeMillis();
        logger.info("StringUtils.join cost {} ms", te - ts);
    }

    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuffer cost {} ms", te - ts);
    }

    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuilder cost {} ms", te - ts);
    }

    public void testStringFormat() {
        String str="";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            str=String.format("%s%s",str,String.valueOf(i));

        }
        str.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuilder cost {} ms", te - ts);
    }

    public void testStr() {
        String success_code = "124";
        char splite = 'a';
        String resultMsg = "";
        long time1 = System.nanoTime();
        for (int i=0;i<10000;i++) {
            resultMsg=String.format("ErrorCode=%s%cErrorMsg=心跳包接收成功%c", success_code, splite, splite);
        }
        long time2 = System.nanoTime();
        System.out.println("StringFormat：" + (time2 - time1) + "ns");

        long time3 = System.nanoTime();
        for (int i=0;i<10000;i++) {
            resultMsg = "ErrorCode=" + success_code + splite + "ErrorMsg=心跳包接收成功" + splite;
        }
        long time4 = System.nanoTime();
        System.out.println("StrTest add：" + (time4 - time3) + "ns");

        long time5 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<10000;i++) {
            resultMsg=sb.append("ErrorCode=").append(success_code).append(splite).append("ErrorMsg=心跳包接收成功").append(splite).toString();
        }
        long time6 = System.nanoTime();
        System.out.println("StringBuilder add：" + (time6 - time5) + "ns");
        System.out.println("-------------------------------------------------");
    }

    public void test() {
        String str="15000-20000元/月";
        System.out.println(str.split("-")[1].replace("元/月",""));
    }

    public void test2() {
        Object str=null;
        Long i=(Long)str;
        System.out.println(i);
    }
}
