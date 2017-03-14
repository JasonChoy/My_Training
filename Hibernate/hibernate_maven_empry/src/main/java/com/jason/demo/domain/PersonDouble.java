package com.jason.demo.domain;

import javax.persistence.*;

/**
 * Created by cjs on 2016/10/28.
 */
@Entity
@Table(name="one_to_one_double_person")
public class PersonDouble {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String name;
    //一个人对应一张身份证
    @OneToOne()
    @JoinColumn(name = "idCardDouble")
    private IdCardDouble idCardDouble;


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

    public IdCardDouble getIdCardDouble() {
        return idCardDouble;
    }

    public void setIdCardDouble(IdCardDouble idCardDouble) {
        this.idCardDouble = idCardDouble;
    }
}
