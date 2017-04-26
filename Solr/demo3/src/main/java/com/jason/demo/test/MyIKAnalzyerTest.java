package com.jason.demo.test;

import com.jason.demo.pojo.Message;
import com.jason.demo.solr_startup.LocalSolrStartUp;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjs on 2017/4/25.
 */
public class MyIKAnalzyerTest {
    //private final static String URL = "http://localhost:8080/solr/mail";
    //private HttpSolrServer server = null;
    private EmbeddedSolrServer server;
    private final String CORE_NAME="message-core";

    @Before
    public void init() {
        //server = new HttpSolrServer(URL);
        LocalSolrStartUp startUp = new LocalSolrStartUp();
        server = startUp.getLocalSolrService(CORE_NAME);
    }

    @Test
    public void clearAll() throws IOException, SolrServerException {
        UpdateResponse updateResponse = server.deleteByQuery("*:*");
        server.commit();
        System.out.println(updateResponse.getResponse());
    }

    @Test
    public void addOneOrDelete() {

    /*HttpSolrServer server=new HttpSolrServer(URL);*/
    /*ConcurrentUpdateSolrServer cs=new ConcurrentUpdateSolrServer(URL,1,1);
     *新版已被ConcurrentUpdateSolrClient取代
     * */
    /*CloudSolrServer css=new CloudSolrServer("192.168.0.1");//zookeeper地址
     *已被CloudSolrClient
     */

        //HttpSolrClient server= new HttpSolrClient(URL);//新版已经取代了HttpSolrServer

        SolrInputDocument doc = new SolrInputDocument();
        //id是唯一的主键，当多次添加的时候，最后添加的相同id的域会覆盖前面的域
        doc.addField("messageId","1");
        doc.addField("msg_title", "这是我的第一个solrj的程序");
        doc.addField("msg_content","我的solrj的程序究竟能不能跑得起来呢？");
        try {
            UpdateResponse ur=server.add(doc);
            System.out.println(ur);

            /*server.deleteByQuery("*:*");*/
            //server.commit(null,true,true,false);
            //server.close();
            server.commit();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //一下添加多个
    @Test
    public void addList() {
        try {
            List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("messageId", "2");
            doc.addField("msg_title", "很好！solr可以工作了");
            doc.addField("msg_content","slor总算可以正式工作了");
            docs.add(doc);
            doc = new SolrInputDocument();
            doc.addField("messageId", "3");
            doc.addField("msg_title", "测试一下solr的添加");
            doc.addField("msg_content","看看能不能添加一个列表信息");
            docs.add(doc);
            server.add(docs);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //基于java bean的添加
    @Test
    public void addBean() {
        try {
            List<Message> msgs = new ArrayList<Message>();
            msgs.add(new Message("4","基于java bean的添加",
                    new String[]{"通过java bean完成添加","java bean的添加附件"}));
            msgs.add(new Message("5","基于java bean的列表数据的添加",
                    new String[]{"测试如何通过一个对象完成添加","通过对象完成添加的附件"}));
            server.addBeans(msgs);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询
    @Test
    public void test04() {
        try {
            //定义查询字符串
            //SolrQuery query = new SolrQuery("*:*");
            SolrQuery query = new SolrQuery("msg_title:第一个");
            query.setStart(0);
            query.setRows(5);
            QueryResponse resp=null;
            try {
                resp = server.query(query);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //查询出来的结果都保存在SolrDocumentList中
            SolrDocumentList sdl = resp.getResults();
            System.out.println(sdl.getNumFound());
            for(SolrDocument sd:sdl) {
//                System.out.println(sd);
                System.out.println(sd.getFieldValue("msg_title")+","+sd.getFieldValue("msg_content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //基于javabean查询
    @Test
    public void test05() {
        try {
            SolrQuery query = new SolrQuery("msg_title:的第一个");      //扩展字典进行扩展，不生效的话，清空索引在重新添加
            query.setStart(0);
            query.setRows(5);
            QueryResponse resp=null;
            try {
                resp = server.query(query);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //可以直接查询相应的bean对象，但是不是很常用,无法得到查询总条数
            List<Message> list = resp.getBeans(Message.class);
            System.out.println(list.size());
            for(Message msg:list) {
                System.out.println(msg.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //高亮
    @Test
    public void test06() {
        try {
            SolrQuery query = new SolrQuery("msg_content:测试 OR msg_title:测试");
            query.setHighlight(true).setHighlightSimplePre("<span class='highligter'>")
                    .setHighlightSimplePost("</span>")
                    .setStart(0).setRows(5);
            query.setParam("hl.fl", "msg_title,msg_content");
            QueryResponse resp=null;
            try {
                resp = server.query(query);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //查询出来的结果都保存在SolrDocumentList中
            SolrDocumentList sdl = resp.getResults();
            System.out.println(sdl.getNumFound());
            for(SolrDocument sd:sdl) {
                String id = (String)sd.getFieldValue("messageId");
                System.out.println(resp.getHighlighting().get(id).get("msg_content"));
                //高亮结果以唯一索引为key
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
