package com.jason.demo;

import com.jason.demo.domain.User;
import com.jason.demo.utils.BsonUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by cjs on 2017/5/8.
 */
public class MongoTest {
    private static final String HOST_ADDRESS = "localhost";
    private static final String DATA_BASE_NAME = "myjavadata2";
    private MongoClient mongoClient = null;
    @Before
    public void connectTest(){
        //new ServerAddress("localhost",27017); 默认端口可以不配
        mongoClient = new MongoClient(HOST_ADDRESS);
    }

    @Test
    public void createDatabaseTest(){
        //mongoClient.dropDatabase(DATA_BASE_NAME);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);   //插入新的connection才会显示
        System.out.println(database);
    }

    @Test
    public void createCollectionTest(){
        //mongoClient.dropDatabase(DATA_BASE_NAME);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);
        database.createCollection("user");
    }

    @Test
    public void documentTest() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //mongoClient.dropDatabase(DATA_BASE_NAME);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);
        MongoCollection<Document> collection = database.getCollection("user");
        //清空集合数据
        //user.drop();

        //插入文档
/*        User user = new User(1,"jasoncai","man",new Date());
        collection.insertOne(BsonUtil.toBson(user));*/

        //更新
        //user.updateOne(Filters.eq("sex","man"),new Document("$set",new Document("age",33)));

        //查询文档
        FindIterable<Document> documents = collection.find();
        for (Document document : documents){
            System.out.println(BsonUtil.toBean(document,User.class));
        }

        //删除
        //user.deleteMany(Filters.eq("sex","woman"));
    }
}
