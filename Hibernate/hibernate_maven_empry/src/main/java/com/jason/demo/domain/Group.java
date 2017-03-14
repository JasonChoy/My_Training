package com.jason.demo.domain;

import javax.persistence.*;

/**
 * Created by cjs on 2016/10/28.
 */
@Entity
@Table(name="many_to_one_group")
public class Group {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;

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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}