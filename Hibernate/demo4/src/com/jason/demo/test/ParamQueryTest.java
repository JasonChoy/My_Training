package com.jason.demo.test;

import com.jason.demo.domain.User;
import org.hibernate.Query;
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
public class ParamQueryTest {
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
    /*参数查询*/
    @Test
    public void paramQueryTest(){
        session.beginTransaction();
        Query query = session.createQuery("from User where name like ?");
        query.setParameter(0,"张%");
        List<User> list = query.list();
        System.out.println(list.size());

        session.close();
    }
    /*参数集合查询*/
    @Test
    public void paramQueryTest2(){
        session.beginTransaction();
        //可以采用 ：参数名 的方式传递参数
        Query query = session.createQuery("from User where id in(:ids)");
        query.setParameterList("ids",new Integer[]{3,4});
        List<User> list = query.list();
        System.out.println(list.size());

        session.close();
    }

    /*聚合查询*/
    @Test
    public void paramQueryTest3(){
        session.beginTransaction();
        //可以采用 ：参数名 的方式传递参数
        Query query = session.createQuery("select c.name,count(s) from ClassesDouble c left join c.students s group by c.name order by c.name");
        List list = query.list();
        for(Iterator ite = list.iterator(); ite.hasNext();){
            Object[] obj=(Object[])ite.next();
            System.out.println(obj[0]+","+obj[1]);
        }
        session.getTransaction().commit();
        session.close();
    }
}
