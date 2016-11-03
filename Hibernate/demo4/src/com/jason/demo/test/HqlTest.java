package com.jason.demo.test;


import com.jason.demo.domain.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by cjs on 2016/11/3.
 */
public class HqlTest {
    Session session = null;
    @Before
    public void before(){
        String resource = "hibernate.cfg.xml";
        //读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure(resource);

        //建立SessionFactory
        SessionFactory factory =cfg.buildSessionFactory();

        session = factory.openSession();
    }

/*  hql查询
    createQuery
    createQuery方法被封装在Session中，它能够按照查询语句查询获取数据库中整张表的数据，它会创建一个新的Query接口。
    该方法需要使用HQL查询字符串作为该方法的参数，并将最后查询到的对象保存在List对象中。*/
    @Test
    public void createQueryTest(){
        session.beginTransaction();
        //HQL的查询不支持*查询，但是可以通过使用类的别名来替代*查询，同时HQL可支持Count(*)查询，查询简单易懂
        Query query = session.createQuery("select u from User u");     //这里的User指的是POJO
        List<User> list = query.list();
        for (User user : list){
            System.out.println(user);
        }

        Query query1 = session.createQuery("select count(*) from User");
        Object num  = query1.uniqueResult();
        System.out.println(num);

        session.close();
    }
    /*sql原生查询*/
    @Test
    public void sqlTest(){
        session.beginTransaction();

        List list=session.createSQLQuery("select * from many_to_one_user").list();

        for(Iterator ite=list.iterator();ite.hasNext();){
            Object[] obj=(Object[])ite.next();
            System.out.println(obj[0]+","+obj[1]);
        }

        session.getTransaction().commit();
        session.close();
    }
/*    对象导航查询*/
    @Test
    public void createQueryTest2(){
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.group.name like '%组%'");
        List<User> list = query.list();
        for (User user : list){
            System.out.println(user);
        }

        session.close();
    }
}
