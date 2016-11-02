package com.jason.demo.domain;

/**
 * Created by cjs on 2016/10/28.
 */
public class IdCardDouble {
    private int id;
    private String cardNo;
    //双向一对一
    private  PersonDouble personDouble;

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
