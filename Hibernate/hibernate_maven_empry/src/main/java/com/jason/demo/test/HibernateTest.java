package com.jason.demo.test;

import com.jason.demo.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjs on 2016/10/27.
 */

public class HibernateTest {
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
    @Test
    public void oneToOneTest(){
        try{

            //开启事务
            session.beginTransaction();

            User user = new User();
            user.setName("jasoncai");
            session.save(user);
            //提交事务
            session.getTransaction().commit();

        }catch(Exception e){
            e.printStackTrace();
            //回滚事务
            session.getTransaction().rollback();
        }finally{
            if(session != null){
                if(session.isOpen()){
                    //关闭session
                    session.close();
                }
            }
        }
    }
    @Test
    public void selectTest(){
        Criteria criteria =session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id",1));
        User user = (User)criteria.uniqueResult();
        System.out.println(user);

        session.close();
    }

}


/*
由于一对一主键关联映射具有以下两个缺点：
        1、灵活性差，没有办法改成多对一关联映射，不能应变多变的需求；
        2、必须先保存关联对象IdCard，之后才能保持Person；
        所以，在映射一对一单向关联映射时，我们采用唯一外键关联映射。
*/
