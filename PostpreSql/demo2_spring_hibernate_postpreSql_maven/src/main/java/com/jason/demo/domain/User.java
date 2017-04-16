package com.jason.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjs on 2017/4/14.
 */
@Entity
@Table(name = "JASON_USER")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer id;

    @Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    private String name;

    @Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private Integer sex;

    @Column(name = "AGE", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private Integer age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTHDAY", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private Date birthDay;

    public User(){};

    public User(Integer id, String name, Integer sex, Integer age, Date birthDay) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthDay = birthDay;
    }

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
