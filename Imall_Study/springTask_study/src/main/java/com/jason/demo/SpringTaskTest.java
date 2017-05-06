package com.jason.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cjs on 2017/5/6.
 */
public class SpringTaskTest {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("spring-application.xml");
        System.out.println("项目启动。。。");
    }
}
