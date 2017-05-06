package com.jason.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cjs on 2017/5/5.
 * http://jackielieu.blog.51cto.com/5586910/1180910
 *  在不使用任何框架的情况下，如何在Java的普通类中获取Session以及request对象
 *  创建好项目后就创建一个类RequestFilter。
    创建RequestFilter类是需要继承一接口，必须是javax.servlet包下的Filter接口。
    创建好后在RequestFilter中创建线程：
    创建线程threadLocalRequest、threadLocalResponse
    在doFilter()方法中写把request、response传入
 */
public class RequestFilter implements Filter {
    //创建线程
    public static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();
    public static ThreadLocal<HttpServletResponse> threadLocalResponse = new ThreadLocal<HttpServletResponse>();
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        threadLocalRequest.set((HttpServletRequest) arg0);
        threadLocalResponse.set((HttpServletResponse) arg1);
        arg2.doFilter(arg0, arg1);
    }
    public void destroy() {
    }
    public void init(FilterConfig arg0) throws ServletException {
    }
}

/*        注册好后就可以在Java类中轻松获取自己在Action或页面上保存在Session中值，具体调用

        HttpServletRequest request = RequestFilter.threadLocal.get();

        request.getSession().getAttribute("所保存的名称");

        HttpServletRequest request = RequestFilter.threadLocal.get();这句话一定要放在方法里面，不能放在方法外面。*/
