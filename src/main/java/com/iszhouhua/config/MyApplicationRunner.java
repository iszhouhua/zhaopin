package com.iszhouhua.config;

import com.iszhouhua.spider.ZhaopinSpider;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

/**
 * 启动时检查是否有为抓取完成的详情数据
 * @author ZhouHua
 * @date 2019/2/12
 */
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private ZhaopinSpider zhaopinSpider;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        if(setOperations.size(Const.ZHAOPIN_PENDING_KEY)>0){
            try {
                zhaopinSpider.startQuartz();
            } catch (SchedulerException e) {
                log.error("定时任务开启失败",e);
            }
        }
    }
}