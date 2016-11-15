package com.jason.demo.service.impl;

import com.jason.demo.service.UserManager;

/**
 * Created by cjs on 2016/11/15.
 *
 */
public class UserManagerImpl implements UserManager {

    public void addUser(String userId, String userName) {

        try {
            //System.out.println("开始执行");
            System.out.println("HelloWorld!");
            //System.out.println("执行成功！");
        }catch(Exception e) {
            e.printStackTrace();
            //System.out.println("执行失败！");
            throw new RuntimeException();
        }
    }
}

/*
从代码可以看出，注释里面的日志内容和业务逻辑毫无关系，无形中使耦合性增加，如果很多类中需要添加这些日志代码，工作量不言而喻，修改起来也非常麻烦。
如果采用代理把打印日志的代码抽取到代理类中，通过代理类和业务逻辑类继承自同一个父类，客户端直接调用代理类完成需求，这样就解决了客户端与业务逻辑类的耦合。*/
