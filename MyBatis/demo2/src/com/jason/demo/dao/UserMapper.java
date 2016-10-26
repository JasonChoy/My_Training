package com.jason.demo.dao;

import com.jason.demo.domain.User;
import com.jason.demo.vo.UserQueryVo;

import java.util.Map;

/**
 * Created by cjs on 2016/9/23.
 * mapper接口的方法 实现dao
 */
public interface UserMapper {
    User findUserById(Integer id);

    User findUserByUserQueryVo(UserQueryVo userQueryVo);

    User findUserByUserQueryVo(Map map);
}
