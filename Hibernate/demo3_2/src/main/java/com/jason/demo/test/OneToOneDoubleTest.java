package com.jason.demo.test;

import com.jason.demo.domain.IdCardDouble;
import com.jason.demo.domain.PersonDouble;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjs on 2016/10/27.
 */

public class OneToOneDoubleTest {
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

            //开启会话事务
            session.beginTransaction();

            IdCardDouble idcard=new IdCardDouble();
            idcard.setCardNo("1111111111111");
            session.save(idcard);

            PersonDouble person=new PersonDouble();
            person.setName("zhangsan");
            person.setIdCardDouble(idcard);
            session.save(person);

            //提交事务，修改数据库
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
    public void selectTest(){       //双向向一对一 双向加载

        //获取person对象，并保存
        PersonDouble person=(PersonDouble)session.load(PersonDouble.class,1);
        System.out.println("IdCard.Id: "+person.getIdCardDouble().getId());
        System.out.println("IdCard.cardno: "+person.getIdCardDouble().getCardNo());

        //创建idCard对象，并保存
        IdCardDouble idcard=(IdCardDouble)session.load(IdCardDouble.class, 1);
        System.out.println("Person.Id: "+idcard.getPersonDouble().getId());
        System.out.println("Person.name: "+idcard.getPersonDouble().getName());

        session.close();
    }

}


