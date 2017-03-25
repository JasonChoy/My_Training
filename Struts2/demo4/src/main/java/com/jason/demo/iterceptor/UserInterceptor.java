package com.jason.demo.iterceptor;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by Administrator on 2017/3/25.
 */
public class UserInterceptor implements Interceptor {
    public void destroy() {
        System.out.println("拦截器销毁");
    }

    public void init() {
        System.out.println("拦截器初始化");
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("拦截器放行");
        return actionInvocation.invoke();
    }
}
