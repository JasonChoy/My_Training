package com.jason.demo.service.impl;

import com.jason.demo.dao.TestDao;
import com.jason.demo.service.TestService;

/**
 * Created by cjs on 2016/11/8.
 */
public class TestImplService implements TestService{
    private TestDao dao;

    @Override
    public void setDao(TestDao dao) {
        this.dao=dao;
    }

    //Dao4MySqlImpl dao4MySqlImpl = (Dao4MySqlImpl)Container.getComponent("dao4Mysql");

    @Override
    public void serviceMethod() {
        //调用Dao实现的方法
        dao.daoMethod();
    }
}
