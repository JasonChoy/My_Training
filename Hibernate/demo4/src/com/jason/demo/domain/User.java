package com.jason.demo.domain;


/**
 * Created by cjs on 2016/10/27.
 */
public class User {
    private int id;
    private String name;
    //一个用户 对应 一个组     一个组 对应 一个用户
    private Group group;

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
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}