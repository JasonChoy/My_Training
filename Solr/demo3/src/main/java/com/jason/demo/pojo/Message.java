package com.jason.demo.pojo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by cjs on 2017/4/25.
 */
public class Message {
    private String messageId;
    private String title;
    private String[] content;


    public Message() {
        super();
    }

    public Message(String messageId, String title, String[] content) {
        super();
        this.messageId = messageId;
        this.title = title;
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    @Field
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getTitle() {
        return title;
    }

    @Field("msg_title")
    public void setTitle(String title) {
        this.title = title;
    }
    public String[] getContent() {
        return content;
    }

    @Field("msg_content")
    public void setContent(String[] content) {
        this.content = content;
    }
}
