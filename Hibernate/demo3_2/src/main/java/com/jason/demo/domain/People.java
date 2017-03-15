package com.jason.demo.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cjs on 2016/11/2.
 */
@Entity
@Table(name="many_to_many_people")
public class People {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;
    @ManyToMany
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
