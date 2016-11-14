package com.jason.demo;

import com.jason.demo.dao.TestDao;
import com.jason.demo.dao.impl.Dao4MySqlImpl;
import com.jason.demo.dao.impl.Dao4OracleImpl;
import com.jason.demo.service.TestService;
import com.jason.demo.service.impl.TestImplService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjs on 2016/11/14.
 */
public class Container {
    //定义一个map结构的对象
    private static Map<String, Object> components;

    private Container() {
    }

    /**
     * 初始化容器
     */
    public static synchronized void init() {
        if (components == null) {
            components = new HashMap<String, Object>();
            //写一个读配置文件的类，根据读取的配置文件，反射对应的类
            //反射好类后进行 依赖管理，往对应的属性上注入相应的类
            //客户端创建新类的时候把容器创建好的类付给新类

            TestDao dao4Mysql = new Dao4MySqlImpl();
            components.put("dao4Mysql", dao4Mysql);

            TestDao dao4OracleImpl = new Dao4OracleImpl();
            components.put("dao4OracleImpl", dao4OracleImpl);

            TestService service = new TestImplService();
            components.put("service", service);

            //容器维护依赖关系
            service.setDao(dao4Mysql);
        }
    }

    /**
     * 查找组件
     *
     * @param id
     * @return
     */
    public static Object getComponent(String id) {
        return components.get(id);
    }
}
