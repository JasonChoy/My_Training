package com.jason.demo.action;

import com.jason.demo.domain.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by cjs on 2017/3/22.
 */
public class MyStrutsAction extends ActionSupport{
    //属性驱动
   private String mark;
    //模型驱动
    private User user;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String index() throws Exception {
        System.out.println("执行 MyStrutsAction.index()...");
        return "index";
    }

    public String login() throws Exception {
        System.out.println("执行 MyStrutsAction.login()...");
        System.out.println("userName:"+user.getUserName());
        System.out.println("age:"+user.getAge());
        System.out.println("mark:"+mark);
        if(user.getUserName()==null || user.getUserName().isEmpty()  || user.getAge() == null || user.getAge() < 0){
            return "error";
        }else{
            return "success";
        }
    }
}
