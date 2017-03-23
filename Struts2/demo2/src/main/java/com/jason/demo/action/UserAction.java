package com.jason.demo.action;

import com.jason.demo.UserDao;
import com.jason.demo.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by cjs on 2017/3/23.
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    UserDao userDao;
    {
        userDao = new UserDao();
    }

    private Collection<User> users = new ArrayList<User>();
    private Integer id;
    private User user;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public User getModel() {
        return user;
    }

    public String list(){
        Map<Integer, User> userMap = userDao.getUserMap();
        users = userMap.values();
        return "list";
    }
    public String add(){
        return "add";
    }
    public String edit(){
        user = userDao.getUserMap().get(id);
        return "edit";
    }
    public String update(){
        userDao.getUserMap().put(id,user);
        return "listchian";
    }
    public String del(){
        userDao.getUserMap().remove(id);
        return "listchian";
    }

    @Override
    public void validate() {
        if(user!=null && (user.getUserName() ==null || user.getUserName().trim().length() == 0)){
            this.addFieldError("userNameFieldError","用户名不能为空");
        }
        super.validate();
    }
}
