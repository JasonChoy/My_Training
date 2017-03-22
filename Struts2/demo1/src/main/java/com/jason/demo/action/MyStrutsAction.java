package com.jason.demo.action;

import com.jason.demo.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2017/3/22.
 */
public class MyStrutsAction extends ActionSupport{
    //属性驱动
/*    private String username;
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/

    //模型驱动
    private User user;

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
/*        System.out.println("username="+username);
        System.out.println("age="+age);
        if(username==null || username.isEmpty()  || age == null || age < 0){
            return "error";
        }else{
            ActionContext.getContext().getSession().put("username",username);
            return "success";
        }*/
        System.out.println(user.getUserName());
        System.out.println(user.getAge());
        if(user.getUserName()==null || user.getUserName().isEmpty()  || user.getAge() == null || user.getAge() < 0){
            return "error";
        }else{
            ActionContext.getContext().getSession().put("username",user.getUserName());
            return "success";
        }
    }
}
