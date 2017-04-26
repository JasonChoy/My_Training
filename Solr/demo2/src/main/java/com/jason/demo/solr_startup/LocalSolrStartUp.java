package com.jason.demo.solr_startup;

import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;

/**
 * Created by cjs on 2017/4/25.
 * EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了,不需要tomcat运行Solr服务
 */
public class LocalSolrStartUp {
    private static CoreContainer coreContainer = null;
    private static EmbeddedSolrServer server = null;
    private final String SOLR_HOME = getClass().getResource("/").getFile().toString()+ "/com/jason/demo/solr_home";

    public EmbeddedSolrServer getLocalSolrService(String coreName) {
        System.setProperty("solr.solr.home", SOLR_HOME);
        CoreContainer coreContainer = new CoreContainer();
        coreContainer.load();
        EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, coreName);
        return server;
    }

}
