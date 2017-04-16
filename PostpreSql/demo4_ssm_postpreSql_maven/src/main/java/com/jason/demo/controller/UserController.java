package com.jason.demo.controller;

import com.jason.demo.domain.User;
import com.jason.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by cjs on 2017/4/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getList")
    public String getList(Model model){
        List<User> list = userService.getList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("/addUser")
    public String addUser(Model modelr){
        return "addUser";
    }

    @RequestMapping("/saveUser")
    public String saveUser(Model model,User user){
        userService.addUser(user);
        return "redirect:/user/getList";
    }
}
