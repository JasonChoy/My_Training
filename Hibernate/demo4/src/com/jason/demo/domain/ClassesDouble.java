package com.jason.demo.domain;

import java.util.List;

/**
 * Created by cjs on 2016/11/1.
 */
public class ClassesDouble {
    private Integer id;
    private String name;
    private List<StudentDouble> students;

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

    public List<StudentDouble> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDouble> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassesDouble{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
