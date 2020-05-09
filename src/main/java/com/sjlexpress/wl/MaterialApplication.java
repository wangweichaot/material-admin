package com.sjlexpress.wl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wangweichao
 */
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Configuration
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.sjlexpress.wl")
@MapperScan(basePackages = {"com.sjlexpress.wl.dao"})
public class MaterialApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialApplication.class, args);
    }

}
