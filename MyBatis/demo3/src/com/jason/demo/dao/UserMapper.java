package com.jason.demo.dao;

import com.jason.demo.domain.User;
import com.jason.demo.vo.UserQueryVo;

import java.util.List;

/**
 * Created by cjs on 2016/9/23.
 * mapper接口的方法 实现dao
 */
public interface UserMapper {
    //根据id查询用户信息，使用resultMap输出
    public UserQueryVo findUserByIdResultMap(int id) throws Exception;


    public List<User> findUserByCondition(User user) throws Exception;
    public List<User> findUserByForEach(UserQueryVo userQueryVo) throws Exception;
}
