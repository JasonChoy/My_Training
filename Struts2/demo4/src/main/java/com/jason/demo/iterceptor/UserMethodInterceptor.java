package com.jason.demo.iterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by Administrator on 2017/3/25.
 */
public class UserMethodInterceptor extends MethodFilterInterceptor{
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        String username = (String) ActionContext.getContext().getSession().get("username");
        if(username == null || username.isEmpty()){
            System.out.println("UserMethodInterceptor拦截器 不通过");
            return "index";  //"login"
        } else{
            System.out.println("UserMethodInterceptor拦截器 通过");
            return actionInvocation.invoke();
        }
    }
}
