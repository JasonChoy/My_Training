package com.jason.demo.dao.impl;

import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by cjs on 2016/10/8.
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public User findUserById(int id) {
        //继承SqlSessionDaoSupport，通过this.getSqlSession()得到sqlSessoin
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("test.findUserById",id);
        return user;
    }

    @Override
    public List<User> findUserByUserName(User user) {
        SqlSession sqlSession = this.getSqlSession();
        List<User> list = sqlSession.selectList("test.findUserByUserName", user);
        return list;
    }

}
