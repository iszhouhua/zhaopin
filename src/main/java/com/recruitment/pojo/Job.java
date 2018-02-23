package com.recruitment.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by ZhouHua
 */
public class Job implements Serializable {
    private Integer id;//工作ID

    private String cityname;//城市名

    private String name;//标题

    private String company;//公司名

    private String joburl;//职位链接

    private String minprice;//最低工资

    private String maxprice;//最高工资

	private String monthprice;//工资

    private String location;//工作地点

    private String education;//最低学历

    private String scale;//公司规模

    private String jobnature;//工作性质

    private String description;//岗位职责

    private String address;//工作地址

    private String companynature;//公司性质

    private String joblabel;//福利待遇
    
    private String exp;//工作经验
    
    private String demand;//招聘人数
    
    private String jobtype;//职位类别

    private String industry;//公司行业

    private String releasetime;//发布时间

    private Date creatTime;//创建时间
    
    private String total;//统计结果

	public String getMonthprice() {
		return monthprice;
	}

	public void setMonthprice(String monthprice) {
		this.monthprice = monthprice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJoburl() {
		return joburl;
	}

	public void setJoburl(String joburl) {
		this.joburl = joburl;
	}

	public String getMinprice() {
		return minprice;
	}

	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}

	public String getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getJobnature() {
		return jobnature;
	}

	public void setJobnature(String jobnature) {
		this.jobnature = jobnature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanynature() {
		return companynature;
	}

	public void setCompanynature(String companynature) {
		this.companynature = companynature;
	}

	public String getJoblabel() {
		return joblabel;
	}

	public void setJoblabel(String joblabel) {
		this.joblabel = joblabel;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}