package com.jason.demo.test;

import com.jason.demo.domain.ClassesDouble;
import com.jason.demo.domain.StudentDouble;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by cjs on 2016/10/27.
 */
public class OneToManyDoubleTest {
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
    public void oneToManyDoubleTest(){
        try{
            //开启事务
            session.beginTransaction();
            ClassesDouble classes = new ClassesDouble();
            classes.setName("班级一");
            session.save(classes);

            StudentDouble student1 = new StudentDouble();
            student1.setName("student1");
            student1.setClasses(classes);
            StudentDouble student2 = new StudentDouble();
            student2.setName("student2");
            student2.setClasses(classes);
            session.save(student1);
            session.save(student2);


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
        Criteria criteria = session.createCriteria(ClassesDouble.class);
        criteria.add(Restrictions.eq("id",1));
        ClassesDouble classes = (ClassesDouble) criteria.uniqueResult();
        List<StudentDouble> students = classes.getStudents();
        for(StudentDouble student : students){
            System.out.println(student);
        }
        session.close();
    }
}
