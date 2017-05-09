package com.jason.demo.test;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-*.xml")
public class MongoSpringTest{
    @Resource
    IUserDao userDao;

    @Test
    public void testAdd(){
        User user = new User(0L, "jasoncai", "man", new Date());
        userDao.add(user);
    }

    @Test
    public void testDel(){
        userDao.del(2);
    }

    @Test
    public void testUpdate(){
        User user = new User(2, "xiaohong", "woman", new Date());
        userDao.update(user);
    }

    @Test
    public void testFind(){
        List<User> list = userDao.findAll();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("-------------------根据id查询--------------------");
        User user = userDao.findById(1);
        System.out.println(user);
        System.out.println("-----------------分页查询-----------------------");
        List<User> users = userDao.findByPage(3,1);
        System.out.println(Arrays.toString(users.toArray()));

    }

}
