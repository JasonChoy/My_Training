package com.jason.demo.test;

import com.jason.demo.pojo.User;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by cjs on 2017/4/20.
 */
@Component
public class SpringSolr {

    @Autowired
    private HttpSolrServer httpSolrServer;

    public List<User> getUser(Long id) throws SolrServerException {

        //创建查询条件
        SolrQuery query = new SolrQuery();
        query.setQuery("id:" + id);

        //查询并返回结果
        QueryResponse queryResponse = this.httpSolrServer.query(query);
        return queryResponse.getBeans(User.class);
    }

    public void saveUser(User user) throws IOException, SolrServerException {
        UpdateResponse updateResponse = this.httpSolrServer.addBean(user);
        this.httpSolrServer.commit();
        System.out.println(updateResponse.getResponse());
    }
}
