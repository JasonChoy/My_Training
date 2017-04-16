package com.jason.demo.test;

import com.jason.demo.service.Impl.MyServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by cjs on 2017/4/13.
 */
public class CXFServiceTest {
    public static void main(String[] args) {
        String address = "http://localhost:8888/jasonws";
        Endpoint.publish(address,new MyServiceImpl());
        System.out.println("服务已启动...");
    }
}
