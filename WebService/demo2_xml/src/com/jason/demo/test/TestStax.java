package com.jason.demo.test;

import org.junit.Test;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cjs on 2016/11/17.
 */
public class TestStax {
    /*基于光标的查找*/
    @Test
    public void test01(){
        XMLInputFactory factory = XMLInputFactory.newInstance();

        InputStream is = null;

        is = TestStax.class.getClassLoader().getResourceAsStream("books.xml");

        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(is);

            while(reader.hasNext()){
                int type = reader.next();

                if(type == XMLStreamConstants.START_ELEMENT){ //起始节点
                    System.out.println(reader.getName());
                }else if(type == XMLStreamConstants.CHARACTERS){ //文本节点
                    System.out.println(reader.getText());
                }else if(type == XMLStreamConstants.END_ELEMENT){ //结束节点
                    System.out.println("/" + reader.getName());
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test02(){

        XMLInputFactory factory = XMLInputFactory.newInstance();

        InputStream is = null;

        is = TestStax.class.getClassLoader().getResourceAsStream("books.xml");

        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(is);

            while(reader.hasNext()){
                int type = reader.next();

                if(type == XMLStreamConstants.START_ELEMENT){
                    String name = reader.getName().toString();
                    if("book".equals(name)){
                        //读取属性名和值
                        System.out.println(reader.getAttributeName(0) + ":" + reader.getAttributeValue(0));
                    }

                    //获取元素内容
                    if("price".equals(name)){
                        System.out.println(reader.getElementText() + "\n");
                    }
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*基于迭代模型查找：*/
    @Test
    public void test03(){

        XMLInputFactory factory = XMLInputFactory.newInstance();

        InputStream is = null;

        is = TestStax.class.getClassLoader().getResourceAsStream("books.xml");

        try {
            //基于迭代模型的操作方式
            XMLEventReader reader = factory.createXMLEventReader(is);
            int num = 0;
            while(reader.hasNext()){
                //通过XMLEvent来获取是否是某种节点类型
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    //通过event.asxxx转换节点
                    String name = event.asStartElement().getName().toString();
                    if("title".equals(name)){
                        System.out.println(reader.getElementText() + ":");
                    }
                    if("price".equals(name)){
                        System.out.println(reader.getElementText() + "\n");
                    }
                }
                num++;
            }
            System.out.println(num);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
