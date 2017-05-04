package com.jason.demo.domain;

import java.io.Serializable;

/**
 * Created by cjs on 2017/5/2.
 */
public class User implements Serializable{

    private Integer uid;
    private String userName;
    private String password;
    //private Date birthDay;


    public User() {
    }

    public User(Integer uid, String userName, String password) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
