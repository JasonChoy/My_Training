package com.jason.demo;

import com.jason.demo.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjs on 2017/3/23.
 */
public class UserDao {
    private static Map<Integer,User> userMap= new HashMap<Integer, User>();

    static{
        for (int i=0;i<3;i++){
            User user = new User();
            user.setId(i);
            user.setUserName("jasoncai"+i);
            user.setAge((int)Math.random()*100+1);
            userMap.put(user.getId(),user);
        }
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }
}
