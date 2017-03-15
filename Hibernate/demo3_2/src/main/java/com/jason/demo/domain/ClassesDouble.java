package com.jason.demo.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cjs on 2016/11/1.
 */
@Entity
@Table(name="one_to_many_double_classes")
public class ClassesDouble {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private Integer id;
    @Column
    private String name;

    //添加mappedBy属性，否则将创建中间表
/*  @OneToMany(cascade = CascadeType.PERSIST,targetEntity = StudentDouble.class,mappedBy = "classes")
    @OrderBy("id")
    值得注意的是，以StudentDouble作为外键的表会自动生成一个对应的字段并以@OrderBy内值自动创建，
    mappedBy属性内容为目标实体的对应关联字段，如果没有该属性，hibernate会自动创建StudentDouble和ClassesDouble的一个中间表。*/
    @OneToMany(mappedBy = "classes")
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
        return "Classes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
