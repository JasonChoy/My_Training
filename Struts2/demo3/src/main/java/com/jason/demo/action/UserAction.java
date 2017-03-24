package com.jason.demo.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by cjs on 2017/3/23.
 */
public class UserAction extends ActionSupport {
    private String username;  //用户名
    private String password;  //密码
    //用户登录
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
