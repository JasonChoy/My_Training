package com.jason.demo.test;

import com.jason.demo.service.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by cjs on 2017/4/12.
 */
public class ServiceDeployTest {
    public static void main(String[] args) {
        String address="http://localhost:8080/jason";
        Endpoint.publish(address,new HelloServiceImpl());
        System.out.println("服务已开启...");
    }
}
