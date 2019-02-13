package com.iszhouhua.spider;

import com.iszhouhua.config.Const;
import com.iszhouhua.mapper.JobMapper;
import com.iszhouhua.model.Job;
import com.iszhouhua.util.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.io.IOException;

/**
 * 用于抓取详情数据的定时任务
 */
@Slf4j
public class ZhaopinQuartz extends QuartzJobBean {

    @Autowired
    private SetOperations<String,Object> setOperations;

    @Autowired
    private JobMapper jobMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        String url=setOperations.pop(Const.ZHAOPIN_PENDING_KEY).toString();
        Document doc;
        try {
            doc = Jsoup.connect(url)
                  .userAgent(UserAgentUtil.getUserAgent())
                  .timeout(Const.TIMEOUT).get();
        } catch (IOException e) {
            log.error("链接请求失败",e);
            return;
        }
        Job job=new Job();
        job.setUrl(url);
        job.setAddress(doc.select("p.add-txt").text());
        job.setDescription(doc.select("div.pos-ul").text());
        jobMapper.updateByIdOrUrl(job);
    }
}
