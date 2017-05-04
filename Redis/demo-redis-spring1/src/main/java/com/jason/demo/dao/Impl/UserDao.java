package com.jason.demo.dao.Impl;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/**
 * Created by cjs on 2017/5/2.
 */
public class UserDao implements IUserDao{
   // 假设user保存在sys_user表中，主键名称为uid
    private static final String USER_CONTENT_KEY="table:sys_user;uid:";

    @Autowired
    RedisTemplate<String,User> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param user 传入参数，需要final标识，禁止方法内修改。
     */
    public void saveOrUpdateUser(final User user) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                //序列化操作，最好使用RedisTemplate提供的Serializer来完成。
                //不管是Key，还是Value都需要进行Serialize。
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key  = serializer.serialize(USER_CONTENT_KEY + user.getUid()+"");
                byte[] name = serializer.serialize(user.getUserName());
                connection.set(key, name);
                return null;
            }
        });
    }

    public List<User> getAll() {

        return null;
    }

    public User getById(final Integer id) {
        return redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize(USER_CONTENT_KEY + id);
                //判断该key值存不存在
                if(!connection.exists(key)) return null;
                byte[] value = connection.get(key);
                System.out.println("反序列化之前:"+value.toString());
                String username = serializer.deserialize(value);
                System.out.println("反序列化之后:"+username);
                return  new User(id,username,null);
            }
        });

    }

    public void delById(final Integer id) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize(USER_CONTENT_KEY + id);
                //判断该key值存不存在
                if(!connection.exists(key)) return null;
                Long result = connection.del(key);
                System.out.println(result);
                return null;
            }
        });
        //另一种方式
        //redisTemplate.delete(USER_CONTENT_KEY + id);
    }
}
