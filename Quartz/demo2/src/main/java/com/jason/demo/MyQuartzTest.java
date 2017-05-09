package com.jason.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/5/7.
 */
public class MyQuartzTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("spring-application.xml");
        System.out.println("项目启动。。。");

    }
}
