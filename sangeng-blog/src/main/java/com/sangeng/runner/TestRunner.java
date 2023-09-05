package com.sangeng.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 曼迪卡尔多
 * @ClassName TestRunner
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
