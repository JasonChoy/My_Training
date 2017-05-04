package com.jason.demo.dao.Impl;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.JacksonHashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by cjs on 2017/5/2.
 * 采用BoundHashOperations来封装一个map   然后hmset(key,map)  这样写很废   应该直接Json
 */
public class UserDao implements IUserDao{
   // 假设user保存在sys_user表中，主键名称为uid
    private static final String USER_CONTENT_KEY="table:sys_user;uid:";

    @Autowired
    RedisTemplate<Serializable,Serializable> redisTemplate;

    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param user 传入参数，需要final标识，禁止方法内修改。
     */
    public void saveOrUpdateUser(final User user) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
/*                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] keyByte  = serializer.serialize(USER_CONTENT_KEY + user.getUid()+"");
                String key = USER_CONTENT_KEY + user.getUid();
                byte[] userName = serializer.serialize(user.getUserName());
                byte[] passWord = serializer.serialize(user.getPassword());
                String birthDays = null;
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                birthDays = sdf.format(user.getBirthDay());
                byte[] birthDay = serializer.serialize(birthDays);

                BoundHashOperations<Serializable, byte[], byte[]> boundHashOperations = redisTemplate.boundHashOps(key);
                boundHashOperations.put(serializer.serialize("userName"),userName);
                boundHashOperations.put(serializer.serialize("passWord"),passWord);
                boundHashOperations.put(serializer.serialize("birthDay"),birthDay);
                connection.hMSet( keyByte , boundHashOperations.entries());
                return null;*/

                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key  = serializer.serialize(USER_CONTENT_KEY + user.getUid()+"");
                Map<Serializable, Serializable> map = new JacksonHashMapper(User.class).toHash(user);
                redisTemplate.boundHashOps(USER_CONTENT_KEY + user.getUid()).putAll(map);
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
                /*RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize(USER_CONTENT_KEY + id);
                //判断该key值存不存在
                if(!connection.exists(key)) return null;
                List<byte[]> resultList = connection.hMGet(
                        key,
                        serializer.serialize("userName"),
                        serializer.serialize("passWord"),
                        serializer.serialize("birthDay"));
                String userName = serializer.deserialize(resultList.get(0));
                String passWord = serializer.deserialize(resultList.get(1));
                String birthDayStr = serializer.deserialize(resultList.get(2));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date birthDay = null;
                try {
                    birthDay= sdf.parse(birthDayStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new User(id ,userName , passWord ,birthDay);*/

                Map<Object, Object> entries = redisTemplate.boundHashOps(USER_CONTENT_KEY + id).entries();
                return (User) new JacksonHashMapper(User.class).fromHash(entries);
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
