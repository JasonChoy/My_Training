package com.jason.demo.service.Impl;

import com.jason.demo.service.IMyService;

import javax.jws.WebService;

/**
 * Created by cjs on 2017/4/13.
 */
@WebService(endpointInterface = "com.jason.demo.service.IMyService")
public class MyServiceImpl implements IMyService{
    public String hello() {
        System.out.println("hello接口被调用");
        return "lao tie 666!";
    }
}
