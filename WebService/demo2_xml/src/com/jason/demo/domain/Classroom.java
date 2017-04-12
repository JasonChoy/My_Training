package com.jason.demo.domain;

/**
 * Created by cjs on 2016/11/17.
 */
public class Classroom {
    private int id;
    private String name;
    private String years;

    public Classroom() {}

    public Classroom(int id, String name, String years) {
        this.id = id;
        this.name = name;
        this.years = years;
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
