package com.jason.demo.domain;

import javax.persistence.*;

/**
 * Created by cjs on 2016/11/1.
 */
@Entity
@Table(name="one_to_many_double_student")
public class StudentDouble {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private Integer id;
    @Column
    private String name;

    //@ManyToOne(cascade = CascadeType.PERSIST,targetEntity = ClassesDouble.class)
    @ManyToOne
    private ClassesDouble classes;

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
        return "StudentDouble{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                '}';
    }
}
