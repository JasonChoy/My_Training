package com.jason.demo.domain;

import java.util.List;

/**
 * Created by cjs on 2016/11/1.
 */
public class Classes {
    private Integer id;
    private String name;

    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
