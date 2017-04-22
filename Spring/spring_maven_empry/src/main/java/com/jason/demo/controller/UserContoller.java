package com.jason.demo.controller;

import com.jason.demo.service.UserService;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by cjs on 2016/11/8.
 */
@Controller
public class UserContoller {
    @Resource
    private UserService userService;
    @Test
    public void test(){
        userService.addUser("ad","123");
    }

}
