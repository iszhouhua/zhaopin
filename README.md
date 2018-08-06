# 招聘信息统计平台

## 1.数据来源

  招聘信息的数据来源为智联招聘，根据智联搜索规则对工作内容进行爬取分析并入库。

  例如链接：

  [http://sou.zhaopin.com/jobs/searchresult.ashx?jl=深圳&kw=java](http://sou.zhaopin.com/jobs/searchresult.ashx?jl=深圳&kw=java) （jl是**城市**，kw为**职位名**），
  根据此url解析第一页获取工作数量，智联招聘一页最多显示60条工作职位的信息，一共最多显示90页，
  所以我们根据获取的工作数量来确定需要抓取数据的页数，条数大于90页的则获取90页，小于90页的则使用实际页数。
  然后根据页数构建出最终的url：[http://sou.zhaopin.com/jobs/searchresult.ashx?jl=深圳&kw=java&p=1](http://sou.zhaopin.com/jobs/searchresult.ashx?jl=深圳&kw=java&p=1)（p为页码）。
  最后抓取每一页上对应具体工作的url所对应的工作信息及其公司信息。

## 2.数据展示

  统计出最大工资与最小工资，平均工资，中位数工资，以及工资和工作经验的关系、工资与学历的关系、工作经验与用人需求的关系、学历与用人需求的关系等信息进行展示，
  对于公司类型、规模、行业等信息也进行了统计显示。
## 3.使用技术

  java框架使用的是[SpringBoot](http://spring.io/projects/spring-boot)，HTML解析器使用的是[Jsoup](https://jsoup.org/)，数据可视化使用的是百度的Javascript的图表库[ECharts](http://echarts.baidu.com/)，数据库为[MySQL](https://www.mysql.com/)，同时通过[Redis](https://redis.io/ )对抓取到的链接进行去重处理。