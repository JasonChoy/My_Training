package com.jason.demo.pojo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by cjs on 2017/4/20.
 * 要在添加之前要修改managed-schema(就是schema.xml)添加<field/>
 *
 *
 <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false" />
 <field name="userName" type="string" indexed="true" stored="true" required="true" multiValued="false" />
 <field name="loginPwd" type="string" indexed="true" stored="true" required="true" multiValued="false" />
 <field name="email" type="string" indexed="true" stored="true" required="true" multiValued="false" />
 */
public class User {
    private static final String ID="id";// 用户编号
    private static final String USER_NAME="userName";// 用户名
    private static final String LOGIN_PWD="loginPwd";// 用户登录密码
    private static final String E_MAIL="email";// 用户邮箱

    @Field(ID)
    private Long id;// 用户编号
    @Field(USER_NAME)
    private String userName;// 用户名
    @Field(LOGIN_PWD)
    private String loginPwd;// 用户登录密码
    @Field(E_MAIL)
    private String email;// 用户邮箱

    public User(){}

    public User(Long id, String userName, String loginPwd, String email) {
        this.id = id;
        this.userName = userName;
        this.loginPwd = loginPwd;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}