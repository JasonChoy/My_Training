package com.jason.demo.test;


import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.OrderDetail;
import com.jason.demo.domain.Orders;
import com.jason.demo.vo.OrdersCustom;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
        List<OrdersCustom> list = userMapper.findOrdersUser();
        for (OrdersCustom ordersCustom : list){
            System.out.println(ordersCustom);
        }

    }

    @Test
    public void findOrdersAndOrderDetailResultMapTest() throws Exception {
        //创建UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        List<Orders> list = userMapper.findOrdersAndOrderDetailResultMap();
        for (Orders orders : list){
            System.out.println(orders);
            for (OrderDetail orderDetail : orders.getOrderDetails()) {
                System.out.println(orderDetail);
            }
            System.out.println("------------------------------");
        }

    }

    @After
    public void end(){
        // 释放资源
        sqlSession.close();
    }
}
