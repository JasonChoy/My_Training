package com.jason.demo.domain;

import javax.persistence.*;

/**
 * Created by cjs on 2016/10/28.
 */
@Entity
@Table(name="one_to_one_person")
public class Person {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;
    //一个人对应一张身份证
    @OneToOne
    @JoinColumn(name="idCardId")
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
