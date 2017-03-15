package com.jason.demo.domain;


import javax.persistence.*;

/**
 * Created by cjs on 2016/10/27.
 */
@Entity
@Table(name="many_to_one_user")
public class User {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;
    //一个用户 对应 一个组     一个组 对应 多个用户
    @ManyToOne
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
}