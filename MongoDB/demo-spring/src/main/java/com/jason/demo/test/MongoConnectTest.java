package com.jason.demo.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/5/9.
 */
public class MongoConnectTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mongoDB.xml");
        System.out.println(applicationContext.getApplicationName());
    }
}
