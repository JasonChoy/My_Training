package com.jason.demo.service;

import com.jason.demo.pojo.User;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by cjs on 2017/4/12.
 * 提供ws接口
 */
@WebService()
public interface IHelloService {
    public String hello();

    public String bye();

    @WebResult(name="loginUser") //在生成wsdl文件时，返回值显示成为loginUser
    //在生成wsdl文件时，参数不是arg，而显示为username，password
    public User login(@WebParam(name="username")String username, @WebParam(name="password")String password);
}
