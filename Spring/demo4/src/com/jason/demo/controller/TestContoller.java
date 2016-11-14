package com.jason.demo.controller;

import com.jason.demo.Container;
import com.jason.demo.service.TestService;

/**
 * Created by cjs on 2016/11/8.
 */
public class TestContoller {
    public static void main(String[] args) {
        //容器初始化，这个可以用filter完成，只需在整个项目中初始化一次
        Container.init();

        TestService testService  = (TestService)Container.getComponent("service");
        //调用Service实现的方法
        testService.serviceMethod();
    }

}
