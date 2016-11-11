package com.jason.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cjs on 2016/11/7.
 * @Resetcontrol 用于返回对象，会自动格式化为JSON
 * @Control     会返回jsp页面
 */

@Controller
@RequestMapping("/test")
public class TestController{
    @RequestMapping("")
    public String index() {
        return "index page";
    }

    @RequestMapping("/hello")
    public String greeting(Model model) {
        model.addAttribute("name","cai");
        return "hello";
    }

}
/*
运行应用：mvn spring-boot:run或在IDE中运行main()方法，在浏览器中访问http://localhost:8080，Hello World!就出现在了页面中。
            只用了区区十几行Java代码，一个Hello World应用就可以正确运行了，那么这段代码究竟做了什么呢？
            我们从程序的入口SpringApplication.run(Application.class, args);开始分析：

1,SpringApplication是Spring Boot框架中描述Spring应用的类，它的run()方法会创建一个Spring应用上下文（Application Context）。
另一方面它会扫描当前应用类路径上的依赖，例如本例中发现spring-webmvc（由 spring-boot-starter-web传递引入）在类路径中，
那么Spring Boot会判断这是一个Web应用，并启动一个内嵌的Servlet容器（默认是Tomcat）用于处理HTTP请求。

2,Spring WebMvc框架会将Servlet容器里收到的HTTP请求根据路径分发给对应的@Controller类进行处理，@RestController是一类特殊的@Controller，
它的返回值直接作为HTTP Response的Body部分返回给浏览器。

3,@RequestMapping注解表明该方法处理那些URL对应的HTTP请求，也就是我们常说的URL路由（routing)，请求的分发工作是有Spring完成的。
例如上面的代码中http://localhost:8080/根路径就被路由至greeting()方法进行处理。如果访问http://localhost:8080/hello，
则会出现404 Not Found错误，因为我们并没有编写任何方法来处理/hello请求。
        */
