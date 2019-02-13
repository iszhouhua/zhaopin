package com.iszhouhua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.iszhouhua.mapper")
@SpringBootApplication
public class ZhaopinApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZhaopinApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZhaopinApplication.class, args);
    }
}