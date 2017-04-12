package com.jason.demo.service;

import com.jason.demo.pojo.User;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by cjs on 2016/11/16.
 */
@WebService() //将此接口发布成WS
public interface IMyService {

    @WebResult(name="loginUser") //在生成wsdl文件时，返回值显示成为loginUser
    //在生成wsdl文件时，参数不是arg，而显示为username，password
    public User login(@WebParam(name="username")String username, @WebParam(name="password")String password);
}

/*
此接口称为服务提供接口，SEI（Service Endpoint Interface），以上代码中，@WebResult和@WebParam是可选注
解，读者在去掉前后观察wsdl文档，看看究竟发生了哪些变化。
*/
