package com.jason.demo;

import com.jason.demo.domain.User;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

/**
 * Created by cjs on 2016/11/28.
 * 消息的创建有两种方式，一种是直接通过QName创建要发送的消息（Qname就是一个带有命名空间的节点），第二种是基于负载（payload）的消息，直接以字符串的方式发送过去。
 */
public class SoapTest {

    //创建消息
    @Test
    public void test01() {
        try {
            //1、创建消息工厂
            MessageFactory factory = MessageFactory.newInstance();
            //2、根据消息工厂创建SoapMessage
            SOAPMessage message = factory.createMessage();
            //3、创建SOAPPart
            SOAPPart part = message.getSOAPPart();
            //4、获取SOAPENvelope
            SOAPEnvelope envelope = part.getEnvelope();
            //5、可以通过SoapEnvelope有效的获取相应的Body和Header等信息
            SOAPBody body = envelope.getBody();
            //6、根据Qname创建相应的节点(QName就是一个带有命名空间的)
            QName qname = new QName("http://java.zttc.edu.cn/webservice",
                    "add","ns");//<ns:add xmlns="http://java.zttc.edu.cn/webservice"/>
            //如果使用以下方式进行设置，会见<>转换为<和>
            //body.addBodyElement(qname).setValue("<a>1</a><b>2</b>");
            SOAPBodyElement ele = body.addBodyElement(qname);
            ele.addChildElement("a").setValue("22");
            ele.addChildElement("b").setValue("33");
            //打印消息信息
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送消息
    @Test
    public void test02() {
        String wsdlUrl = "http://java.zttc.edu.cn/webservice";
        String ns = "http://java.zttc.edu.cn/webservice";
        try {
            //1、创建服务(Service)
            URL url = new URL(wsdlUrl);
            QName sname = new QName(ns,"MyServiceImplService");
            Service service = Service.create(url,sname);

            //2、创建Dispatch
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"),
                    SOAPMessage.class, Service.Mode.MESSAGE);

            //3、创建SOAPMessage
            SOAPMessage msg = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
            SOAPBody body = envelope.getBody();//http://www.cnblogs.com/fsjohnhuang/articles/2340335.html

            //4、创建QName来指定消息中传递数据
            QName ename = new QName(ns,"add","nn");//<nn:add xmlns="xx"/>
            SOAPBodyElement ele = body.addBodyElement(ename);
            ele.addChildElement("a").setValue("22");
            ele.addChildElement("b").setValue("33");
            msg.writeTo(System.out);
            System.out.println("\n invoking.....");


            //5、通过Dispatch传递消息,会返回响应消息
            SOAPMessage response = dispatch.invoke(msg);
            response.writeTo(System.out);
            System.out.println();

            //将响应的消息转换为dom对象
            Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
            String str = doc.getElementsByTagName("addResult").item(0).getTextContent();
            System.out.println(str);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//基于负载（payload）的消息
    @Test
    public void test03() {
        String wsdlUrl = "http://java.zttc.edu.cn/webservice";
        String ns = "http://java.zttc.edu.cn/webservice";
        try {
            //1、创建服务(Service)
            URL url = new URL(wsdlUrl);
            QName sname = new QName(ns,"MyServiceImplService");
            Service service = Service.create(url,sname);

            //2、创建Dispatch(通过源数据的方式传递)
            Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"),
                    Source.class, Service.Mode.PAYLOAD);
            //3、根据用户对象创建相应的xml
            User user = new User(3,"zs","张三","11111");
            JAXBContext ctx = JAXBContext.newInstance(User.class);
            Marshaller mar = ctx.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter writer= new StringWriter();
            mar.marshal(user, writer);

            //4、封装相应的part addUser
            String payload = "<nn:addUser xmlns:nn=\""+ns+"\">"+writer.toString()+"</nn:addUser>";
            System.out.println(payload);
            StreamSource rs = new StreamSource(new StringReader(payload));

            //5、通过dispatch传递payload
            Source response = (Source)dispatch.invoke(rs);

            //6、将Source转化为DOM进行操作，使用Transform对象转换
            Transformer tran = TransformerFactory.newInstance().newTransformer();
            DOMResult result = new DOMResult();
            tran.transform(response, result);

            //7、处理相应信息(通过xpath处理)
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nl = (NodeList)xpath.evaluate("//user", result.getNode(), XPathConstants.NODESET);
            User ru = (User)ctx.createUnmarshaller().unmarshal(nl.item(0));
            System.out.println(ru.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}