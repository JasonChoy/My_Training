package com.jason.demo;

import com.jason.demo.service.impl.MyServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by cjs on 2016/11/16.
 */
public class TestServiceDeploy {

    public static void main(String[] args) {

        String address = "http://localhost:8888/ws";

        //两个参数，一个是发布服务地址，一个是发布的服务实现
        Endpoint.publish(address, new MyServiceImpl());
    }
}
