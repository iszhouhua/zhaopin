package com.zhaopin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.zhaopin.dao")
@SpringBootApplication // 相当于使用 @Configuration @EnableAutoConfiguration @ComponentScan
public class ZhaopinApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZhaopinApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZhaopinApplication.class, args);
    }
}