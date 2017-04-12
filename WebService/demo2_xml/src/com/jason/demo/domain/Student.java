package com.jason.demo.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cjs on 2016/11/17.
 */
@XmlRootElement
public class Student {
    private int id;
    private String name;
    private String years;
    private Classroom classroom;

    public Student() {}

    public Student(int id, String name, String years, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.classroom = classroom;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
