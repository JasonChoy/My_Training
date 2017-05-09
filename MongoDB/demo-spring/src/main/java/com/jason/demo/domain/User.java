package com.jason.demo.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/8.
 */
@Document(collection="user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private ObjectId _id;
    @Field(value = "user_id")
    private Integer id;
    @Field(value = "user_userName")
    private String userName;
    @Field(value = "user_sex")
    private String sex;
    @Field(value = "user_birthDay")
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

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
