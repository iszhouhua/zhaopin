package com.zhaopin.controller;

import com.zhaopin.dao.CityMapper;
import com.zhaopin.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZhouHua
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private CityMapper cityMapper;

    @GetMapping("")
    public String layout() {
        return "common/layout";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("job")
    public String job(Model model) {
        List<City> cityList=cityMapper.selectCity();
        model.addAttribute("cityList",cityList);
        return "job";
    }

    @GetMapping("company")
    public String company() {
        return "company";
    }

    @GetMapping("update")
    public String update(Model model) {
        List<City> cityList=cityMapper.selectCity();
        model.addAttribute("cityList",cityList);
        return "update";
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}
