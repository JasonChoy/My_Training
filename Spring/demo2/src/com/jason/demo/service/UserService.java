package com.jason.demo.service;

/**
 * Created by cjs on 2016/11/8.
 */
public interface UserService {
    public void addUser(String userName,String password);

    public void delUser(int userId);

    public String findUserById(int userId);

    public void modifyUser(int userId,String userName,String password);
}
