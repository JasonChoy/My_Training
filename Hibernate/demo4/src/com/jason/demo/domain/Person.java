package com.jason.demo.domain;

/**
 * Created by cjs on 2016/10/28.
 */
public class Person {
    private int id;
    private String name;
    //一个人对应一张身份证
    private IdCard idCard;

    public IdCard getIdCard() {
        return idCard;
    }
    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
