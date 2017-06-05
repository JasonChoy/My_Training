package com.jason.framework.test;

import com.jason.framework.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext-*.xml"})

public class TestSpringMVC {

    @Autowired
    private UserController userController;

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    //模拟request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    /*
    * 测试开始之前进行初始化
    */
    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testUser() {
        try {
            request.setParameter("username", "admin");
            request.setParameter("password", "123456");
            try {
                //判断控制器执行后是否返回字符串"/index"用于渲染
                //assertEquals("/index", userController.users(request, response));
                //assertEquals("userList", userController.getAll());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
