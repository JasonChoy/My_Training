package com.jason.demo.test;

import com.jason.demo.pojo.User;
import com.jason.demo.solr_startup.LocalSolrStartUp;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjs on 2017/4/25.
 */
public class WithoutIKAnalzyerTest {
    private final String CORE_NAME="user-core";
    private EmbeddedSolrServer localSolrService;
    @Before
    public void localStartUp(){
        LocalSolrStartUp startUp = new LocalSolrStartUp();
        localSolrService = startUp.getLocalSolrService(CORE_NAME);
    }

    @Test
    public void clearAll() throws IOException, SolrServerException {
        UpdateResponse updateResponse = localSolrService.deleteByQuery("*:*");
        localSolrService.commit();
        System.out.println(updateResponse.getResponse());
    }

    @Test
    public void addUsers(){
        try {
            User user1 = new User(13L,"jasoncai", "123qwe123", "123@123.com");
            User user2 = new User(15L,"jasonchen", "132456", "123@163.com");
            User user3 = new User(17L,"xiaohong", "132456", "123@163.com");
            User user4 = new User(19L,"xiaochen", "admin", "123@jason.com");
            List<User> list = new ArrayList<User>();
            list.add(user1);
            list.add(user2);
            list.add(user3);
            list.add(user4);
            localSolrService.addBeans(list);
            localSolrService.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void selectALLTest(){
        try {
            SolrQuery query = new SolrQuery();
            query.setQuery("*:*");
            QueryResponse response=localSolrService.query(query);
            System.out.println(response.getResults());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //不添加分词器的情况下是搜索不出来的
    @Test
    public void selectTest(){
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("userName:jason");
            QueryResponse response = localSolrService.query(solrQuery);
            System.out.println(response.getResults());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
/*
    //取得根目录路径
    String rootPath=getClass().getResource("/").getFile().toString();
    //当前目录路径
    String currentPath1=getClass().getResource(".").getFile().toString();
    String currentPath2=getClass().getResource("").getFile().toString();
    //当前目录的上级目录路径
    String parentPath=getClass().getResource("../").getFile().toString();  */
