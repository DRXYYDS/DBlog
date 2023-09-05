package com.sangeng.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 曼迪卡尔多
 * @ClassName TestJob
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@Component
public class TestJob {
    //秒 分 时  日 月 星期
    @Scheduled(cron = "0/5 * * * * ?")
    public void testJob(){
        //要执行的代码
        System.out.println("定时任务执行了");
    }
}
