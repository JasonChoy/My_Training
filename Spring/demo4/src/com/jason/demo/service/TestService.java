package com.jason.demo.service;

import com.jason.demo.dao.TestDao;

/**
 * Created by cjs on 2016/11/8.
 */
public interface TestService {
    //增加注入接口的方法
    public void setDao(TestDao dao);

    public void serviceMethod();
}
