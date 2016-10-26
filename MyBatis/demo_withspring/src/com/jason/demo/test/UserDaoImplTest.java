package com.jason.demo.test;

import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by cjs on 2016/10/8.
 */
public class UserDaoImplTest {
    private ApplicationContext applicationContext;

    //在setUp这个方法得到spring容器
    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }

    @Test
    public void testFindUserById() throws Exception {
        // 创建UserDao的对象
        UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper");

        // 调用UserDao的方法
        User user = userMapper.findUserById(1);

        System.out.println(user);

    }

    @Test
    public void testFindUserByUserName() throws Exception {
        // 创建UserDao的对象
        UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper");

        // 调用UserDao的方法
        User queryVo = new User();
        queryVo.setUsername("张");
        List<User> result = userMapper.findUserByUserName(queryVo);
        for(User user : result){
            System.out.println(user);
        }

    }
}
