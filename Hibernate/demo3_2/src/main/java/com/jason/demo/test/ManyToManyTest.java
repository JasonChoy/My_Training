package com.jason.demo.test;

import com.jason.demo.domain.People;
import com.jason.demo.domain.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cjs on 2016/10/27.
 */
public class ManyToManyTest {
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
    public void manyToOneTest(){
        session.beginTransaction();

        Role role1 = new Role();
        role1.setName("男人");
        Role role2 = new Role();
        role2.setName("老公");
        Role role3 = new Role();
        role3.setName("警察");
        session.save(role1);
        session.save(role2);
        session.save(role3);

        People people1 = new People();
        people1.setName("小明");
        Set set1 = new HashSet();
        set1.add(role1);
        set1.add(role2);
        people1.setRoles(set1);
        People people2 = new People();
        people2.setName("John");
        Set set2 = new HashSet();
        set2.add(role1);
        set2.add(role3);
        people2.setRoles(set2);
        session.save(people1);
        session.save(people2);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void selectTest(){                       //多对一：多端维护一端的关系，在加载多端时，可以将一端加载上来。
        String resource = "hibernate.cfg.xml";
        //读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure(resource);

        //建立SessionFactory
        SessionFactory factory =cfg.buildSessionFactory();

        //取得session
        Session session = null;

        //开启session
        session = factory.openSession();

        Criteria criteria = session.createCriteria(People.class);
        criteria.add(Restrictions.eq("id",1));
        People people = (People) criteria.uniqueResult();
        Set<Role> roles = people.getRoles();
        for (Role r : roles){
            System.out.println(r);
        }



        session.close();
    }
}
