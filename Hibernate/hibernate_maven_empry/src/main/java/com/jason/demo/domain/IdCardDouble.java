package com.jason.demo.domain;

import javax.persistence.*;

/**
 * Created by cjs on 2016/10/28.
 */
@Entity
@Table(name="one_to_one_double_idcard")
public class IdCardDouble {
    @Id //主键
    @GeneratedValue(strategy= GenerationType.AUTO)//采用数据库自增方式生成主键
    @Column
    private int id;
    @Column
    private String cardNo;
    //双向一对一
    @OneToOne(mappedBy = "idCardDouble")
    private PersonDouble personDouble;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public PersonDouble getPersonDouble() {
        return personDouble;
    }

    public void setPersonDouble(PersonDouble personDouble) {
        this.personDouble = personDouble;
    }
}
