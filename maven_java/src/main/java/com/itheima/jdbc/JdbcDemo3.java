package com.itheima.jdbc;

import java.sql.*;

public class JdbcDemo3 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "0510");
            String sql = "select * from account";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            System.out.println(id);
            String name = rs.getString("name");
            System.out.println(name);
            double balance = rs.getDouble(3);
            System.out.println(balance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
