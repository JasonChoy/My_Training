package com.jason.demo.test;

import com.jason.demo.domain.PessimismLockInventory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cjs on 2016/11/7.
 * 悲观锁
 * 悲观锁和乐观锁不同，它是通过数据库机制限制对数据库的操作的，通过使用方法的LockMode参数来配置对数据库的并发操作，在一次访问过程中将会把数据锁住（查询时），
 * 只要事务不提交那么任何用户都不能查看和修改，其它用户操作时将会被阻塞，不能同时操作，需要等待第一次访问完成后再进行其它的操作。具体方法如下示例。
 */
public class PessimismLockTest {
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
            PessimismLockInventory inven=(PessimismLockInventory)session.load(PessimismLockInventory.class, "1001",LockMode.UPGRADE);
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
            PessimismLockInventory inven=(PessimismLockInventory)session.load(PessimismLockInventory.class, "1001", LockMode.UPGRADE);

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
/*    演示并发操作时在testLoad1()方法的打印处设置相应的断点，然后启动Debug调试，运行到断点后停止，此时运行testLoad2()方法，实现了对数据库的并发操作，
        在运行testLoad2()方法时Hibernate发出了SQL语句，但是却一直不能查询和检索数据，因为数据库中加了悲观锁，数据库限制了并发性的操作，
        所以Hibernate只是发出了命令但是一直没有操作权限，也就是说testLoad2()方法在执行时遭到了阻塞，操作被压到堆栈中等待上次的完成。
        当testLoad1()方法执行完后，将会从堆栈中继续获取testLoad2()的操作命令，这种乐观锁不仅限制了对数据的操作而且还能保证数据的完整性。
*/
