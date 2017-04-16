package com.jason.demo.controller;

import com.jason.demo.domain.User;
import com.jason.demo.service.IUserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cjs on 2017/4/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /*   使用InitBinder来处理Date类型的参数*/
    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("/userList")
    public String getlist(Model model){
        ArrayList<User> list = userService.getList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model){
        return "addUser";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user){
        userService.addUser(user);
        return "redirect:/user/userList.ac";
    }
}
