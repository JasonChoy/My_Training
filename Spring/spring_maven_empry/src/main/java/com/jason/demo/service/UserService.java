package com.jason.demo.service;

/**
 * Created by cjs on 2016/11/8.
 */
public interface UserService {
    void addUser(String userName, String password);

    void delUser(int userId);

    String findUserById(int userId);

    void modifyUser(int userId, String userName, String password);
}
