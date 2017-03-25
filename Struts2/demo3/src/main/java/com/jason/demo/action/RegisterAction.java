package com.jason.demo.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/25.
 */
public class RegisterAction extends ActionSupport{
    private String username;
    private String password;
    private String repassword;
    private int age;
    private Date birthday;

    private String users;
    public String getUsers() {
        return users;
    }
    public void setUsers(String users) {
        this.users = users;
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
    public String getRepassword() {
        return repassword;
    }
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    // 自定义的执行方法
    public String test() throws Exception {
        System.out.println("test method invoked!");
        // TODO Auto-generated method stub
        return super.execute();
    }

    // 自定义的校验方法
    public void validateTest() {
        System.out.println("validateTest method invoked!");
    }

    @Override
    public void validate() {
        System.out.println("validate method invoked!");
        if (null == this.getPassword()
                || "".equals(this.getPassword()) || null ==
                this.getRepassword()
                || "".equals(this.getRepassword())) {
            return;
        }

        if (!this.getPassword().equals(this.getRepassword())) {
            this.addFieldError("repassword",
                    "repassword should be same password");
        }
    }
}
