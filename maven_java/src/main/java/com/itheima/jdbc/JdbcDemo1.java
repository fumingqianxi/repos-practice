package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "0510");
        String sql = "update account set balance = 10000 where id = 1";
        Statement statement = conn.createStatement();
        int count = statement.executeUpdate(sql);
        System.out.println(count);
        statement.close();
        conn.close();
    }
}
