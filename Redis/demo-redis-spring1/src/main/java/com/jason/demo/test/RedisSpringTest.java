package com.jason.demo.test;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cjs on 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class RedisSpringTest extends AbstractJUnit4SpringContextTests{
    @Autowired
    IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void saveOrUpdateUserTest(){
        User user = new User(1,"haha","123456");
        userDao.saveOrUpdateUser(user);
    }
    @Test
    public void getUserTest(){
        User user = userDao.getById(1);
        System.out.println(user);
    }

    @Test
    public void saveOrUpdateUserTest2(){
        User user = new User(1,"jasoncai","123456");
        userDao.saveOrUpdateUser(user);
    }

    @Test
    public void delUserTest(){
        userDao.delById(1);
    }
}
