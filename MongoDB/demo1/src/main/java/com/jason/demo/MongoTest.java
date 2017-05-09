package com.jason.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjs on 2017/5/8.
 */
public class MongoTest {
    private static final String HOST_ADDRESS = "localhost";
    private static final String DATA_BASE_NAME = "myjavadata";
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
    public void createConnectionTest(){
        //mongoClient.dropDatabase(DATA_BASE_NAME);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);
        database.createCollection("user");
    }

    @Test
    public void documentTest(){
        //mongoClient.dropDatabase(DATA_BASE_NAME);
        MongoDatabase database = mongoClient.getDatabase(DATA_BASE_NAME);
        MongoCollection<Document> user = database.getCollection("user");
        //清空集合数据
        //user.drop();

        //插入文档
/*        Document document = new Document("id","1");
        document.append("name","jasoncai");
        document.append("sex","man");
        user.insertOne(document);*/

/*        Document document1 = new Document("id","2");
        document1.append("name","xiaoming");
        document1.append("sex","man");
        Document document2 = new Document("id","3");
        document2.append("name","xiaohong");
        document2.append("sex","woman");
        List list = new ArrayList();
        list.add(document1);
        list.add(document2);
        user.insertMany(list);*/

        //更新
        //user.updateOne(Filters.eq("sex","man"),new Document("$set",new Document("age",33)));

        //查询文档
        FindIterable<Document> documents = user.find(Filters.eq("sex","man"));
        for (Document document : documents){
            System.out.println(document.toJson());
        }

        //删除
        //user.deleteMany(Filters.eq("sex","woman"));
    }
}
