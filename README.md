# 招聘信息统计平台
此平台基于Maven搭建的Spring+SpringMVC+MyBatis三大框架开发，数据库使用的是MySql，开发工具是IntelliJ IDEA，服务器为Jetty。
---
## 1.数据来源
  招聘信息的数据来源为智联招聘，智联招聘的职位搜索页面的链接为：
  http://sou.zhaopin.com/jobs/searchresult.ashx?jl=北京&kw=java （jl后边的是城市，kw为职位名），
  根据此url解析第一页获取工作数量，智联招聘一页最多显示60条工作职位的信息，一共显示90页，
  所以我们根据获取的工作数量来确定需要抓取数据的页数，条数大于90页的则获取90页，小于90页的则使用实际页数。
  然后根据页数构建出最终的url：http://sou.zhaopin.com/jobs/searchresult.ashx?jl=北京&kw=java&p=1（p为页码）。
  最后抓取每一页上对应具体工作的url所对应的工作信息及其公司信息。
## 2.数据展示
  统计出最大工资与最小工资，平均工资，中位数工资，以及工资和工作经验的关系、工资与学历的关系、工作经验与用人需求的关系、学历与用人需求的关系等信息进行展示，
  对于公司类型、规模、行业等信息也进行了统计显示。
## 3.其他
  HTML解析器使用的是Jsoup，数据可视化使用的是纯Javascript的图表库ECharts，数据获取主要通过JQuery+AJax异步获取。