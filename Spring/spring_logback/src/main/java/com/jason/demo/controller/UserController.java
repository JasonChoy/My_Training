package com.jason.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView){
        try {
            int a = 1/0;
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error("Main错误：" + sw.toString());
        }
        modelAndView.addObject("username","jasoncai");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
