package com.jason.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/8.
 */
public class User implements Serializable{
    private Integer id;
    private String userName;
    private String sex;
    private Date  birthDay;

    public User() {
    }

    public User(Integer id, String userName, String sex, Date birthDay) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.birthDay = birthDay;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
