
package com.jason.demo.clientWSDL;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jason.demo.clientWSDL package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloResponse_QNAME = new QName("http://service.demo.jason.com/", "helloResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://service.demo.jason.com/", "loginResponse");
    private final static QName _ByeResponse_QNAME = new QName("http://service.demo.jason.com/", "byeResponse");
    private final static QName _Hello_QNAME = new QName("http://service.demo.jason.com/", "hello");
    private final static QName _Bye_QNAME = new QName("http://service.demo.jason.com/", "bye");
    private final static QName _Login_QNAME = new QName("http://service.demo.jason.com/", "login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jason.demo.clientWSDL
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link ByeResponse }
     * 
     */
    public ByeResponse createByeResponse() {
        return new ByeResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link Bye }
     * 
     */
    public Bye createBye() {
        return new Bye();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ByeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "byeResponse")
    public JAXBElement<ByeResponse> createByeResponse(ByeResponse value) {
        return new JAXBElement<ByeResponse>(_ByeResponse_QNAME, ByeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bye }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "bye")
    public JAXBElement<Bye> createBye(Bye value) {
        return new JAXBElement<Bye>(_Bye_QNAME, Bye.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.demo.jason.com/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

}
