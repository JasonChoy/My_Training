package com.jason.demo.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class MyQuartzJob {

    public void runMyQuartzJob(){
        System.out.println("Quartz定时器被调用:" + new Date());
    }

}
