package com.jason.demo.test;


import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.User;
import com.jason.demo.vo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cjs on 2016/9/22.
 */
public class MybatisTest {
    //根据id查询用户信息，得到一条记录结果

    @Test
    public void findUserByIdTest() throws IOException {
        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        User user = userMapper.findUserById(1);
        System.out.println(user);

        // 释放资源
        sqlSession.close();

    }

    @Test
    public void findUserByUserQueryVoTest() throws IOException {
        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream =  Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis配置文件的信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setId(1);
        userQueryVo.setUsername("张卫健");
        User user = userMapper.findUserByUserQueryVo(userQueryVo);
        System.out.println(user);
/*        Map map =new HashMap();
        map.put("id",1);
        map.put("username","张卫健");
        User user = userMapper.findUserByUserQueryVo(map);
        System.out.println(user);*/

        // 释放资源
        sqlSession.close();

    }
}
