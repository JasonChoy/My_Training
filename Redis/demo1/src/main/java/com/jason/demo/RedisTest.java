package com.jason.demo;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by cjs on 2017/5/1.
 */
public class RedisTest {
    private Jedis jedis = null;

    @Before
    public void redisConnectionTest(){
        // 连接本地的 Redis 服务
        jedis = new Jedis("localhost");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
    }

    @Test
    public void addStrings(){
        System.out.println(jedis.get("baiduurl"));
        jedis.set("googleurl","www.google.com");
        System.out.println(jedis.get("googleurl"));
    }

    @Test
    public void addLists(){
        jedis.lpush("mylist","math");
        jedis.lpush("mylist","chinese");
        jedis.lpush("mylist","english");
        System.out.println(jedis.lrange("mylist",0,-1));
/*        jedis.rpush("mylist","histroy");*/
        System.out.println(jedis.lrange("mylist",0,-1));
        //jedis.lpop("mylist");   //删除头部第一个
        //ltrim [ltrim key  range_l range_r]:保留区域类的元素，其他的删除
        //jedis.ltrim("mylist",1,3);
        //lrem [lrem key count value] :移除等于value的元素，当count>0时，从表头开始查找，移除count个；当count=0时，从表头开始查找，移除所有等于value的；当count<0时，从表尾开始查找，移除|count| 个。
        jedis.lrem("mylist",0,"chinese");
        System.out.println(jedis.lrange("mylist",0,-1));
    }

    @Test
    public void addSets(){
/*        jedis.sadd("myset","1990");
        jedis.sadd("myset","1983");
        jedis.sadd("myset","2017");
        jedis.sadd("myset","2000");
        jedis.sadd("myset","1993");*/
        System.out.println(jedis.smembers("myset"));          //为什么排了序？
    }

    @Test
    public void addSordedSets(){
/*        jedis.zadd("myzset",1,"baidu.com");
        jedis.zadd("myzset",3,"360.com");
        jedis.zadd("myzset",2,"google.com");*/
        System.out.println(jedis.zrange("myzset",0,-1));    //两位数字代表index的值
        System.out.println(jedis.zrangeByScore("myzset",1,2)); //两位数字代表score的值
    }

    @Test
    public void addHashs(){
        Map<String,String> user1 = new HashMap<String, String>();
        user1.put("userid","1");
        user1.put("username","jasoncai");
        user1.put("age","24");
        user1.put("sex","0");
        jedis.hmset("user:001:information:",user1);
        Map<String, String> result = jedis.hgetAll("user:001:information:");
        for(String key:result.keySet()){
            System.out.println(key + "--" + result.get(key));
        }
    }

    //获取所有的key值
    @Test
    public void keysTest(){
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
