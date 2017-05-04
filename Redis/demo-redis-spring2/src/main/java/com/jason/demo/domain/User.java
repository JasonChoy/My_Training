package com.jason.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjs on 2017/5/2.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1267719235225203410L;

    private Integer uid;
    private String userName;
    private String password;
    private Date birthDay;


    public User() {
    }

    public User(Integer uid, String userName, String password, Date birthDay) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.birthDay = birthDay;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
