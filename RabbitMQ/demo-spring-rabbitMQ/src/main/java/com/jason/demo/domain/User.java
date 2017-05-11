package com.jason.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/11.
 */
public class User implements Serializable{
    Integer id;
    String userName;
    Date birth;

    public User() {
    }

    public User(Integer id, String userName, Date birth) {
        this.id = id;
        this.userName = userName;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", birth=" + birth +
                '}';
    }
}
