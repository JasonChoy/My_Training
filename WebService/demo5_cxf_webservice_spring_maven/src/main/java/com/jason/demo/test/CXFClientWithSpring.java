package com.jason.demo.test;

import com.jason.demo.CXFClientWSDL.IMyService;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cjs on 2017/4/13.
 */
public class CXFClientWithSpring {
    @Test
    public void test() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/wsserver/jasonws?wsdl");
        QName qname = new QName("http://Impl.service.demo.jason.com/","MyServiceImplService");
        Service ser = Service.create(url,qname);
        IMyService port = ser.getPort(IMyService.class);
        System.out.println(port.hello());
    }
}
