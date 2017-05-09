package com.jason.demo.dao.Impl;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.domain.User;
import com.jason.demo.utils.MongoBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cjd on 2017/5/9.
 */
@Repository
public class UserDao implements IUserDao{
    private final static String COLLECTION_NAME = "user";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(User user){
/*        DBCollection collection = mongoTemplate.getCollection(COLLECTION_NAME);
        collection.insert(MongoBeanUtil.bean2DBObject(user));*/
        mongoTemplate.save(user,COLLECTION_NAME);
    }

    public void del(Integer id) {
/*        User user = findById(id);       //User要提供ObjectId _id字段 不然会提示id找不到(mongoDb自己生成的id)
        WriteResult writeResult = mongoTemplate.remove(user, COLLECTION_NAME);
        System.out.println(writeResult);
*/
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(id));
        User user = mongoTemplate.findAndRemove(query, User.class);
        System.out.println("已删除--" + user);
    }

    public void update(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user.getId()));
        try {
            Update update = Update.fromDBObject(MongoBeanUtil.bean2DBObject(user));
            mongoTemplate.updateFirst(query,update,User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    public User findById(Integer id) {
        //User user = mongoTemplate.findById(id, User.class);   这个是根据mongo自身的object_id来找的
        Query query = new Query(Criteria.where("user_id").is(id));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public List<User> findByPage(int pageSize, int pageNumber) {
        Query query = new Query();
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
