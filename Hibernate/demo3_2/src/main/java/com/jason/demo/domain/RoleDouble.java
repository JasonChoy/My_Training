package com.jason.demo.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cjs on 2016/11/2.
 */
@Entity
@Table(name="many_to_many_double_role")
public class RoleDouble {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<PeopleDouble> peoples;

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

    public Set<PeopleDouble> getPeoples() {
        return peoples;
    }

    public void setPeoples(Set<PeopleDouble> peoples) {
        this.peoples = peoples;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
