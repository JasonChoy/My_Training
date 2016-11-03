package com.jason.demo.test;

import com.jason.demo.domain.Student;
import com.jason.demo.domain.StudentDouble;
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
/*      1、内连接(自然连接): 只有两个表相匹配的行才能在结果集中出现

        2、外连接: 包括

        （1）左外连接(左边的表不加限制)
            SELECT c.id,c.name,s.id studentId,s.name FROM `one_to_many_double_classes` c LEFT JOIN `one_to_many_double_student` s ON c.id=s.classesId
            不管班级有没有学生都会显示所有的班级
        （2）右外连接(右边的表不加限制)

        （3）全外连接(左右两表都不加限制)

        3、自连接(连接发生在一张基表内)*/
public class ConnectQueryTest {
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
    /*内连接*/
    @Test
    public void innerJoinQuery(){
        session.beginTransaction();
        List<StudentDouble> students = session.createQuery("from StudentDouble s join s.classes c").list();
        for(Iterator ite = students.iterator(); ite.hasNext();){
            Object[] obj=(Object[])ite.next();
            System.out.println(obj[0]+","+obj[1]);
        }
        session.getTransaction().commit();
        session.close();
    }
    /*左连接*/
    @Test
    public void leftJoinQuery(){
        session.beginTransaction();
        List<StudentDouble> students = session.createQuery("from ClassesDouble c left join c.students s").list();
        for(Iterator ite = students.iterator(); ite.hasNext();){
            Object[] obj=(Object[])ite.next();
            System.out.println(obj[0]+","+obj[1]);
        }
        session.getTransaction().commit();
        session.close();
    }

    /*右连接*/
    @Test
    public void rightJoinQuery(){
        session.beginTransaction();
        List<StudentDouble> students = session.createQuery("from ClassesDouble c right join c.students s").list();
        for(Iterator ite = students.iterator(); ite.hasNext();){
            Object[] obj=(Object[])ite.next();
            System.out.println(obj[0]+","+obj[1]);
        }
        session.getTransaction().commit();
        session.close();
    }
}
