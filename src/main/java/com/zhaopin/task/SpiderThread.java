package com.zhaopin.task;

import com.zhaopin.dao.CompanyMapper;
import com.zhaopin.dao.JobMapper;
import com.zhaopin.model.Company;
import com.zhaopin.model.Job;
import com.zhaopin.util.SpringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ZhouHua
 * @date 2018/7/26
 */
public class SpiderThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SpiderThread.class);

    private CompanyMapper companyMapper;

    private JobMapper jobMapper;

    private String url;

    private int page;

    private SetOperations redis;

    public SpiderThread(String url,int page){
        this.url=url;
        this.page=page;
        this.redis=SpringUtil.getBean(StringRedisTemplate.class).opsForSet();
        this.companyMapper=SpringUtil.getBean(CompanyMapper.class);
        this.jobMapper=SpringUtil.getBean(JobMapper.class);
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(url + "&p=" + page)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                    .timeout(10000).get();
            Elements elements=doc.select("td.zwmc");
            List<Job> jobs=new ArrayList<>();
            List<Company> companys=new ArrayList<>();
            for (Element element : elements) {
                String link=element.selectFirst("a").attr("abs:href");
                String linkMd5= DigestUtils.md5Hex(link.getBytes("utf-8"));
                boolean flag=link.startsWith("http://jobs");
                if(flag&&redis.add("job",linkMd5)>0){
                    Job job=parseJob(link);
                    if (job==null){
                        continue;
                    }
                    job.setId(linkMd5);
                    jobs.add(job);
                    if (job.getCompany()!=null){
                        companys.add(job.getCompany());
                    }
                }else {
                    logger.debug("链接不符合要求或者已经抓取过了：{}",link);
                }
            }
            if(!jobs.isEmpty()){
                jobMapper.insertJobList(jobs);
            }
            if(!companys.isEmpty()){
                companyMapper.insertCompanyList(companys);
            }
            logger.info("第{}页已经抓取完成",page);
        } catch (IOException e) {
            logger.error("爬取过程中出现错误，中断爬虫。");
            e.printStackTrace();
        }
    }


    /**
     * 分析工作详情页
     * @param link 详情页链接
     * @return 工作实体类
     */
    private Job parseJob(String link){
        Job job=new Job();
        Document doc;
        try {
            doc = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                    .timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("工作链接：{}",link);
            job.setUrl(link);
            String jobName=doc.select(".fl>h1").text();
            logger.debug("职位名称：{}",jobName);
            job.setName(jobName);
            String welfare = StringUtils.join(doc.select(".welfare-tab-box>span").eachText(),",");
            logger.debug("福利待遇：{}",welfare);
            job.setWelfare(welfare);
            Elements details=doc.select(".terminalpage-left>ul>li>strong");
            for (int i=0;i<details.size();i++) {
                String detail = details.get(i).text();
                switch (i) {
                    case 0:
                        String[] price = detail.split("-");
                        if (price.length > 1) {
                            BigDecimal minPrice = new BigDecimal(price[0]);
                            BigDecimal maxPrice = new BigDecimal(price[1].replace("元/月", ""));
                            logger.debug("最低工资：{}元-最高工资：{}元", minPrice, maxPrice);
                            job.setMinPrice(minPrice);
                            job.setMaxPrice(maxPrice);
                        } else {
                            logger.debug("薪资为：{}，舍弃！",price);
                        }
                        break;
                    case 1:
                        String city=detail.split("-")[0];
                        logger.debug("工作城市：{}", city);
                        job.setCity(city);
                        break;
                    case 2:
                        logger.debug("发布时间：{}", detail);
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        job.setReleaseTime(LocalDateTime.parse(detail,df));
                        break;
                    case 3:
                        logger.debug("工作性质：{}", detail);
                        job.setNature(detail);
                        break;
                    case 4:
                        logger.debug("工作经验：{}", detail);
                        job.setExperience(detail);
                        break;
                    case 5:
                        logger.debug("最低学历：{}", detail);
                        job.setEducation(detail);
                        break;
                    case 6:
                        int demand=Integer.parseInt(detail.replace("人",""));
                        logger.debug("招聘人数：{}人", demand);
                        job.setDemand(demand);
                        break;
                    case 7:
                        logger.debug("职位类别：{}", detail);
                        job.setType(detail);
                        break;
                    default:
                        logger.warn("未识别的条目：{}", detail);
                        break;
                }
            }
            String address=doc.select(".tab-inner-cont h2").text().replace("查看职位地图","");
            logger.debug("工作地址：{}" ,address);
            job.setAddress(address);
            String description=StringUtils.join(doc.select(".terminalpage-main .tab-inner-cont:first-child>p:not(:last-child)").eachText(),"\n");
            logger.debug("职位描述：{}",description);
            job.setDescription(description);
            String companyName=doc.select(".company-box>.company-name-t>a").text();
        String companyMd5= null;
        try {
            companyMd5 = DigestUtils.md5Hex(companyName.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        logger.debug("公司ID：{}",companyMd5);
            job.setCompanyId(companyMd5);
            if(redis.add("company",companyMd5)>0){
                Company company=parseCompany(doc,companyName,companyMd5);
                job.setCompany(company);
            }
        return job;
    }


    /**
     * 分析公司信息
     * @param doc 详情页文档
     * @return 公司ID
     */
    private Company parseCompany(Document doc,String companyName,String companyMd5){
        Company company=new Company();
        logger.debug("公司ID：{}",companyMd5);
        company.setId(companyMd5);
        logger.debug("公司名：{}",companyName);
        company.setName(companyName);
        Elements details=doc.select(".company-box>ul>li>strong");
        for (int i=0;i<details.size();i++) {
            String detail = details.get(i).text();
            switch (i){
                case 0:
                    logger.debug("公司规模：{}",detail);
                    company.setScale(detail);
                    break;
                case 1:
                    logger.debug("公司性质：{}",detail);
                    company.setNature(detail);
                    break;
                case 2:
                    logger.debug("公司行业：{}",detail);
                    company.setIndustry(detail);
                    break;
                case 3:
                    if(details.size()>4){
                        logger.debug("公司主页：{}",detail);
                        company.setUrl(detail);
                    }else{
                        String address=detail.replace("查看公司地图","");
                        logger.debug("公司地址：{}",address);
                        company.setAddress(address);
                    }
                    break;
                case 4:
                    String address=detail.replace("查看公司地图","");
                    logger.debug("公司地址：{}",address);
                    company.setAddress(address);
                    break;
                default:
                    logger.warn("未识别的条目：{}", detail);
                    break;
            }
        }
        Element element=doc.select(".terminalpage-main .tab-inner-cont").last();
        element.getElementsByTag("h5").remove();
        String description=StringUtils.join(element.children().eachText(),"\n");
        logger.debug("公司介绍：{}",description);
        company.setDescription(description);
        return company;
    }
}
