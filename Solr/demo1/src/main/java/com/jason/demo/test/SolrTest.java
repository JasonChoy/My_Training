package com.jason.demo.test;

import com.jason.demo.pojo.User;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjs on 2017/4/21.
 */
public class SolrTest {
    private HttpSolrServer server;

    @Before
    public void connectText() throws Exception {
/*
        idea的URL:http://127.0.0.1:8080/millery
        eclipse的URL:http://127.0.0.1:8080/solr/millery
*/
        String url = "http://127.0.0.1:8080/demo-spring";
        server = new HttpSolrServer( url );
        server.setSoTimeout(1000);  // socket read timeout
        server.setConnectionTimeout(100);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
        server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
    }

    //清空索引
    @Test
    public void delAllDocTest() throws Exception{
        server.deleteByQuery( "*:*" );// delete everything!
        server.commit();
    }

    //添加doc
    @Test
    public void addDocTest() throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",22L);
        document.addField("userName","zhangguorong");
        document.addField("loginPwd","66666");
        document.addField("email","123@qq.com");
        server.add(document);
        server.commit();
    }

    //添加之前要修改managed-schema(就是schema.xml)添加<field/>
    @Test
    public void addBeanTest() throws Exception {
        User user1 = new User(1L,"jasoncai","123456","897330838@qq.com");
        User user2 = new User(2L,"xiaoming","23333","laotie666@qq.com");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        UpdateResponse updateResponse = server.addBeans(list);
        server.commit();
        System.out.println(updateResponse);

    }

    @Test
    public void selectTest() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        //query.setQuery("id:" + 2L);
        QueryResponse query1 = server.query(query);
        System.out.println(query1.getResults().toString());
    }

}
