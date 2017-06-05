package com.jason.framework.test;

import com.jason.framework.dao.UserMapper;
import com.jason.framework.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-dao.xml")
public class TestMabatis {
    @Autowired
    UserMapper userMapper;

    @Test
    public void TestInsert(){
        User user = new User(null, "xiaoming", new Date(), "man", "guangzhou china");
        //userMapper.insert(user);
        List<User> allUser = userMapper.getAll();
        for (User temp : allUser){
            System.out.println(temp);
        }
        User user2 = userMapper.getByid(2);
        user2.setSex("woman");
        //userMapper.update(user2);
    }
}
