package com.zhaopin.dao;

import com.zhaopin.model.Company;

import java.util.List;

public interface CompanyMapper {
    /**
     * 批量添加公司信息
     * @param company 公司集合
     * @return
     */
    int insertCompanyList(List<Company> company);

    /**
     * 统计不同性质的公司数量
     * @return
     */
    List<Company> countCompanyForNature();

    /**
     * 统计不同规模的公司数量
     * @return
     */
    List<Company> countCompanyForScale();

    /**
     * 统计不同行业的公司数量
     * @return
     */
    List<Company> countCompanyForIndustry();
}