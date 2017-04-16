package com.jason.demo.domain;

import java.io.Serializable;

/**
 * Created by cjs on 2017/4/15.
 */
public class User implements Serializable{
    private int id;
    private String name;

    public User(){}

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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
