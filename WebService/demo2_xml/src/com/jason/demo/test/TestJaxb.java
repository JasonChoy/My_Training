package com.jason.demo.test;

import com.jason.demo.domain.Classroom;
import com.jason.demo.domain.Student;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by cjs on 2016/11/17.
 * Jaxb的用法很简单，把Java对象转换为xml叫编排，xml转换为Java对象叫反编排
 */
public class TestJaxb {
    @Test
    public void test01(){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = ctx.createMarshaller();
            Student stu = new Student(1,"jasoncai","32",new Classroom(1,"计算机","2012"));
            marshaller.marshal(stu, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>32</age><classroom><grade>2012</grade><id>1</id><name>计算机</name></classroom><id>1</id><name>这是</name></student>";
            JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Student stu = (Student)um.unmarshal(new StringReader(xml));
            System.out.println(stu.getName() + "," + stu.getClassroom().getName());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
