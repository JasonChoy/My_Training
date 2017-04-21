package com.jason.demo.test;

import com.jason.demo.pojo.User;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by cjs on 2017/4/20.
 */
public class SpringSolrTest {

    private SpringSolr springSolr;

    @Before
    public void setUp() throws Exception {
        // 初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "spring-application.xml", "spring-solr.xml");

        //获取对象
        this.springSolr = applicationContext.getBean(SpringSolr.class);
    }

    @Test
    public void saveUserTest() throws IOException, SolrServerException {
        User user = new User(1L,"heiheihei","123456","897330838@qq.com");
        springSolr.saveUser(user);
    }

    @Test
    public void getUserTest() throws SolrServerException {
        // 测试方法，输出结果
        List<User> user = springSolr.getUser((long) 1);
        System.out.println(user.toString());
    }

}