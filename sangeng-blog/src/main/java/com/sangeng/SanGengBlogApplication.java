package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;

/**
 * @author 曼迪卡尔多
 * @ClassName SanGengBlogApplication
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.sangeng.mapper")
@EnableSwagger2
@EnableScheduling
public class SanGengBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanGengBlogApplication.class,args);
    }
}
