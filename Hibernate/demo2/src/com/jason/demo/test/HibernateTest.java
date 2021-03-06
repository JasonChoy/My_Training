package com.jason.demo.test;

import com.jason.demo.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.Date;

/**
 * Created by cjs on 2016/10/27.
 */
public class HibernateTest {
    @Test
    public void createTableTest(){
        //默认读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure();
        ////生成并输出sql到文件（当前目录）和数据库
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);

        //方式二: 在hibernate.cfg.xml中，加入<property name="hibernate.hbm2ddl.auto">create</property>
/*        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        sessionFactory.openSession();
        sessionFactory.close();*/
    }

    @Test
    public void addDataTest(){
        String resource = "hibernate.cfg.xml";
        //读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure(resource);

        //建立SessionFactory
        SessionFactory factory =cfg.buildSessionFactory();

        //取得session
        Session session = null;

        try{
            //开启session
            session = factory.openSession();
            //开启事务
            session.beginTransaction();

            User user = new User();
            user.setName("jasoncai");
            user.setPassword("123456");
            user.setCreateTime(new Date());
            user.setExpireTime(new Date());
            //保存User对象
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
}
