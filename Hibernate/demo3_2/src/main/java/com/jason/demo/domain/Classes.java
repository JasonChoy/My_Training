package com.jason.demo.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cjs on 2016/11/1.
 */
@Entity
@Table(name="one_to_many_classes")
public class Classes {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private Integer id;
    @Column
    private String name;

    @OneToMany
    @JoinColumn(name="classesId")
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

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
