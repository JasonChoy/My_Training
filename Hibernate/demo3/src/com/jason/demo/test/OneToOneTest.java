package com.jason.demo.test;

import com.jason.demo.domain.IdCard;
import com.jason.demo.domain.Person;
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

public class OneToOneTest {
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

            Person person = new Person();
            person.setName("张卫健");
            IdCard idCard = new IdCard();
            idCard.setCardNo("123456789");

            person.setIdCard(idCard);

            session.save(idCard);
            session.save(person);
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
    public void selectTest(){       //单向一对一(单方面维护关联关系) 存在外键问题
        Criteria criteria =session.createCriteria(Person.class);
        criteria.add(Restrictions.eq("id",1));
        Person person = (Person)criteria.uniqueResult();
        System.out.println(person.getIdCard().getCardNo());         //只能从person一方加载idcard

        session.close();
    }

}


/*
由于一对一主键关联映射具有以下两个缺点：
        1、灵活性差，没有办法改成多对一关联映射，不能应变多变的需求；
        2、必须先保存关联对象IdCard，之后才能保持Person；
        所以，在映射一对一单向关联映射时，我们采用唯一外键关联映射。
*/
