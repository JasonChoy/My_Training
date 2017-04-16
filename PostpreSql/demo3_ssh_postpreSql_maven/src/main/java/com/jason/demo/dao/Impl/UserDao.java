package com.jason.demo.dao.Impl;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by cjs on 2017/4/14.
 */
@Repository
public class UserDao extends HibernateDaoSupport implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    //openSession() 不会提交 要自己session.flush();
//    private Session openSession(){
//        Session session = sessionFactory.openSession();
//        return session;
//    }

    private Session currentSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public Integer addUser(User user){
        Session session = currentSession();
        session.save(user);
        System.out.println("dao保存用户");
        return user.getId();
    }

    public ArrayList<User> getList() {
        return (ArrayList<User>)currentSession().createCriteria(User.class).list();
    }
}
