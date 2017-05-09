package com.jason.demo.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by cjs on 2017/5/7.
 */
@Component
public class MyQuartzJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void runMyQuartzJob(){
        System.out.println("Quartz定时器被调用:" + new Date());
    }

}
