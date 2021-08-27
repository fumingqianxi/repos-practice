package com.itheima.jdbc;

import com.itheima.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo5 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";
            preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement1.setDouble(1, 500);
            preparedStatement1.setInt(2, 1);
            preparedStatement2.setDouble(1, 500);
            preparedStatement2.setInt(2, 2);
            preparedStatement1.executeUpdate();
            int i = 3 / 0;
            preparedStatement2.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(preparedStatement1, conn);
            JdbcUtil.close(preparedStatement2, null);
        }
    }
}
