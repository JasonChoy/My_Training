package com.jason.demo.test;


import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.User;
import com.jason.demo.vo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjs on 2016/9/22.
 */
public class MybatisTest {
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过工厂得到SqlSession
        sqlSession = sqlSessionFactory.openSession();
    }
    @Test
    public void findUserByIdResultMapTest() throws Exception {
        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        UserQueryVo userQueryVo = userMapper.findUserByIdResultMap(1);
        System.out.println(userQueryVo);

        // 释放资源
        sqlSession.close();
    }

    @Test
    public void findUserByConditionTest() throws Exception {
        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setSex("男");
        //调用userMapper的方法
        List<User> list = userMapper.findUserByCondition(user);
        System.out.println(list);
        System.out.println("总共:"+list.size());

        // 释放资源
        sqlSession.close();
    }

    @Test
    public void findUserByForEachTest() throws Exception {
        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo = new UserQueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        userQueryVo.setIds(list);
        //调用userMapper的方法
        List<User> userList = userMapper.findUserByForEach(userQueryVo);
        System.out.println(userList);
        System.out.println("总共:"+userList.size());

        // 释放资源
        sqlSession.close();
    }
}
