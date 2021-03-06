package com.jason.demo.test;

import com.jason.demo.domain.Classes;
import com.jason.demo.domain.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjs on 2016/10/27.
 */
public class OneToManyTest {
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
    public void oneToManyTest(){
        try{
            //开启事务
            session.beginTransaction();

            Student student1 = new Student();
            student1.setName("student1");
            Student student2 = new Student();
            student2.setName("student2");
            session.save(student1);
            session.save(student2);

            Classes classes = new Classes();
            classes.setName("班级一");
            List<Student> list = new ArrayList<Student>();
            list.add(student1);
            list.add(student2);
            classes.setStudents(list);
            session.save(classes);

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
    public void selectTest(){                                       //一对多：一端维护多端的关系，在加载一端时，可以将多端加载上来。
        Criteria criteria = session.createCriteria(Classes.class);
        criteria.add(Restrictions.eq("id",1));
        Classes classes = (Classes) criteria.uniqueResult();
        List<Student> students = classes.getStudents();
        for(Student student : students){
            System.out.println(student);
        }
        session.close();
    }
}
