package com.jason.demo.timertask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by cjs on 2017/5/6.
 */
@Component
public class MyTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void process() {
        System.out.println("MyTask定时运行："+ new Date());
    }
}
