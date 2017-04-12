package com.jason.demo.test;

import com.jason.demo.clientWSDL.JasonCaiHello;
import com.jason.demo.service.IHelloService;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cjs on 2017/4/12.
 */
public class ClientTest {
    @Test
    public void test() throws MalformedURLException {
        //创建访问wsdl服务地址的url
        URL url = new URL("http://localhost:8080/jason?wsdl");
        //通过Qname指明服务的具体信息
        //第一个参数填wsdl中的targetNamespace
        //第二个参数填wsdl中的serviceName
        QName sname = new QName("http://impl.service.demo.jason.com/","JasonCaiHello");

        Service service = Service.create(url,sname);
        IHelloService port = service.getPort(IHelloService.class);
        String reponseMess = port.hello();
        System.out.println(reponseMess);

    }

    @Test
    public void test2() throws MalformedURLException {
        //创建访问wsdl服务地址的url
        URL url = new URL("http://localhost:8080/jason?wsdl");
        QName sname = new QName("http://impl.service.demo.jason.com/","JasonCaiHello");

        JasonCaiHello service = new JasonCaiHello(url,sname);
        com.jason.demo.clientWSDL.IHelloService port = service.getHelloServiceImplPort();
        port.login("jasoncai","123456");
    }

}
