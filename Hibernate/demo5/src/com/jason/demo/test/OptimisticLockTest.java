package com.jason.demo.test;

import com.jason.demo.domain.OptimisticLockInventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjs on 2016/11/7.
 * 乐观锁
 * 乐观锁是通过在数据库中添加一个名为version的字段来实现每次对数据的校验，在每次操作数据库的时候会自动更新version的值，这样每次操作的version值是不一样的，
 * 所以如果有并发操作时将会首先校验version值是否小于数据库的version值，如果小于的话不会更新数据库，它的具体使用方法如下示例：
 */
public class OptimisticLockTest {
    Session session = null;
    @Before
    public void init(){
        String resource="hibernate.cfg.xml";
        Configuration configure = new Configuration().configure(resource);
        SessionFactory sessionFactory = configure.buildSessionFactory();
        session = sessionFactory.openSession();
    }
/*    测试上面的示例，在程序中添加了两个方法来加载并修改数据，其中的testLoad1()方法首先加载数据库中的数据，并修改字段Quantity的值，修改后保存数据，这时要设置断点，
    在提交事务时设置断点，停止对数据库的提交操作，此时执行testLoad2()方法，将会发出错误信息，不能更新数据库操作。*/
    @Test
    public void testLoad1(){
        try{
            //开启事务
            session.beginTransaction();
            OptimisticLockInventory inven=(OptimisticLockInventory)session.load(OptimisticLockInventory.class, "1001");
            System.out.println("p1-->name:"+inven.getItemName());
            inven.setQuantity(inven.getQuantity()-200);
            session.save(inven);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
    }
    @Test
    public void testLoad2(){
        try{
            //开启事务
            session.beginTransaction();
            OptimisticLockInventory inven=(OptimisticLockInventory)session.load(OptimisticLockInventory.class, "1001");

            System.out.println("p2-->name:"+inven.getItemName());
            inven.setQuantity(inven.getQuantity()-200);
            session.save(inven);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
    }
}
/*    testLoad2()提交更改后，接下来运行testLoad1()的断点，将会发出：Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) 的错误，
    说明两个同时在修改同一条数据，导致了并发操作，发出了错误提示。
*/
