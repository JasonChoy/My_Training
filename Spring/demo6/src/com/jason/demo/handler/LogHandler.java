package com.jason.demo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cjs on 2016/11/15.
 */
public class LogHandler implements InvocationHandler {
    //保留一份targetObject目标类对象
    private Object targetObject;

    //Proxy类动态创建一份目标代理类
    public Object newProxyInstance(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始执行！");

        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        Object ret = null;

        try{
            //调用目标方法
            ret = method.invoke(targetObject, args);
            System.out.println("执行成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("执行失败！");
            throw e;
        }
        return ret;
    }
}

/*
Proxy类所创建的目标类必须实现至少一个接口，在调用newProxyInstance方法时必须与目标类的类加载器和接口一致；
invoke方法非常类似Filter中的doFilter方法，它将调用目标类的所有方法在未到达UserManagerImpl之前截获，根据我们自己的需求进行预处理后，继续调用UserManagerImpl。
为了保持invoke方法的通用性，目标方法中的参数以数组args形式传递，如果方法中有返回值，则返回，没有返回值，则返回null。
如此一来，程序员不必为每个目标类设计一个代理类，所有需要打印日志的类都可以共用这个LogHandler，
如果不想使用日志功能就可以直接删除LogHandler类，对原功能没有丝毫影响，如同揭去显示器上的保护膜，不会影响显示器的使用一般。*/
