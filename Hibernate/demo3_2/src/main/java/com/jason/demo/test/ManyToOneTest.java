package com.jason.demo.test;

import com.jason.demo.domain.Group;
import com.jason.demo.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Created by cjs on 2016/10/27.
 */
public class ManyToOneTest {
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
    public void manyToOneTest(){
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
            user.setName("张卫健");
            User user2 = new User();
            user2.setName("吴彦祖");
            Group group = new Group();
            group.setName("第一组");

            user.setGroup(group);
            user2.setGroup(group);
            //保存User对象
            session.save(group);
            session.save(user);
            session.save(user2);

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

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id",4));
        User user = (User) criteria.uniqueResult();
        Group group = user.getGroup();
        System.out.println(group);

        session.close();
    }
}
