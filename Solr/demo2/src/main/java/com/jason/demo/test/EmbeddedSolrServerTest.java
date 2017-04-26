package com.jason.demo.test;

import com.jason.demo.pojo.User;
import com.jason.demo.solr_startup.LocalSolrStartUp;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by cjs on 2017/4/25.
 */
public class EmbeddedSolrServerTest {
    private final String CORE_NAME="user-core";
    private EmbeddedSolrServer localSolrService;
    @Before
    public void localStartUp(){
        LocalSolrStartUp startUp = new LocalSolrStartUp();
        localSolrService = startUp.getLocalSolrService(CORE_NAME);
    }

    @Test
    public void addUser(){
        try {
            User user = new User(11L,"jasoncai", "123qwe123", "123@123.com");
            localSolrService.addBean(user);
            localSolrService.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectTest(){
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("*:*");
            QueryResponse response = localSolrService.query(solrQuery);
            System.out.println(response.getResults());
        } catch (SolrServerException e) {
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
