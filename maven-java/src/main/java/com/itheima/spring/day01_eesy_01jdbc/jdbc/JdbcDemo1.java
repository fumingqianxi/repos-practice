package com.itheima.spring.day01_eesy_01jdbc.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "0510");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        rs.close();
        preparedStatement.close();
        connection.close();
    }
}
