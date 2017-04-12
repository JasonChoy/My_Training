package com.jason.demo;

import com.jason.demo.service.impl.IMyService;
import com.jason.demo.service.impl.MyServiceImplService;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cjs on 2016/11/16.
 */
public class TestServiceDeploy {
    @Test
    public void client() throws MalformedURLException {
        //创建访问wsdl服务地址的url
        URL url = new URL("http://localhost:8888/ws?wsdl");
        //通过Qname指明服务的具体信息
        QName sname = new QName("http://impl.service.demo.jason.com/","MyServiceImplService");

        //-------------------------方式一-------------------------
        MyServiceImplService msis = new MyServiceImplService(url, sname);

        IMyService ms1 = msis.getMyServiceImplPort();
        System.out.println(ms1.login("张三", "123"));


        //------------------------方式二----------------------------------------
        //创建服务
        //Service service = Service.create(url,sname);
        //实现接口
        //IMyService ms = service.getPort(IMyService.class);
        //System.out.println(ms.login("李四", "123"));
    }
}
