package com.iszhouhua.controller;

import com.iszhouhua.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ZhouHua
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private JobMapper jobMapper;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("job")
    public String job(Model model) {
        List<String> cityList=jobMapper.selectCity();
        model.addAttribute("cityList",cityList);
        return "job";
    }

    @GetMapping("company")
    public String company() {
        return "company";
    }

    @GetMapping("skill")
    public String hello(Model model) {
        List<String> cityList=jobMapper.selectCity();
        model.addAttribute("cityList",cityList);
        return "skill";
    }
}
