package test.SpringMVC.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import test.SpringMVC.domain.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cjs on 2016/9/20.
 * @Controller 负责注册一个bean 到spring 上下文中
   @RequestMapping 处理器映射器 注解为控制器指定可以处理哪些 URL 请求
 */
@Controller
@RequestMapping("/springmvc")
public class HelloDemo {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    //自动匹配参数  根据名字自动进行匹配
    @RequestMapping("/person")
    public String toPerson(String name,double age){
        System.out.println(name+" "+age);
        return "hello";
    }

    //boxing automatically 自动装箱  支持对象匹配
    @RequestMapping("/person1")
    public String toPerson(Person p){
        System.out.println(p.getName()+" "+p.getAge());
        return "hello";
    }

 /*   使用InitBinder来处理Date类型的参数*/
    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }

    //the parameter was converted in initBinder
    @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }


}
