package com.jason.demo.domain;

/**
 * Created by cjs on 2016/11/1.
 */
public class StudentDouble {
    private  Integer id;
    private  String name;
    private  ClassesDouble classes;
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

    public ClassesDouble getClasses() {
        return classes;
    }

    public void setClasses(ClassesDouble classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
