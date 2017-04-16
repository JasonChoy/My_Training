package com.jason.demo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by cjs on 2017/4/14.
 */
public class PostpreSqlTest {
    @Test
    public void testConnect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/frist_database",
                            "postgres", "root");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    @Test
    public void testCreateTable() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/frist_database",
                    "postgres", "root");
            String sql = "create table \"students\"(" +
                    "student_id serial PRIMARY KEY, " +
                    "student_num VARCHAR (5)," +
                    "student_name VARCHAR (10))";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.execute();
            //c.setAutoCommit(false); 默认是自动提交的
            //c.commit();
            preparedStatement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
