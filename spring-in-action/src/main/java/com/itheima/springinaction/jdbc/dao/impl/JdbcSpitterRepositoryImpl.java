package com.itheima.springinaction.jdbc.dao.impl;

import com.itheima.springinaction.jdbc.domain.JdbcSpitter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author 胡磊
 * @since 2023/5/7 11:59
 */
public class JdbcSpitterRepositoryImpl implements com.itheima.springinaction.jdbc.dao.JdbcSpitterRepository {

  private static final String SQL_INSERT_SPITTER =
      "insert into spitter (username, password, full_name, update_by_email) values (?, ?, ?, ?)";

  private static final String SQL_UPDATE_SPITTER =
      "update spitter set username = ?, password = ?, full_name = ?, update_by_email = ? where id = ?";

  private static final String SQL_SELECT_SPITTER =
      "select id, username, password, full_name from spitter where id = ?";

  private DataSource dataSource;

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void addSpitter(JdbcSpitter spitter) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = dataSource.getConnection();
      stmt = conn.prepareStatement(SQL_INSERT_SPITTER);
      stmt.setString(1, spitter.getUsername());
      stmt.setString(2, spitter.getPassword());
      stmt.setString(3, spitter.getFullName());
      stmt.setLong(4, spitter.isUpdateByEmail() ? 1 : 0);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      }catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void saveSpitter(JdbcSpitter spitter) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev", "root", "0510");
      stmt = conn.prepareStatement(SQL_INSERT_SPITTER);
      stmt.setString(1, spitter.getUsername());
      stmt.setString(2, spitter.getPassword());
      stmt.setString(3, spitter.getFullName());
      stmt.setLong(4, spitter.isUpdateByEmail() ? 1 : 0);
      stmt.execute();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      }catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void updateSpitter(JdbcSpitter spitter) {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = dataSource.getConnection();
      stmt = conn.prepareStatement(SQL_UPDATE_SPITTER);
      stmt.setString(1, spitter.getUsername());
      stmt.setString(2, spitter.getPassword());
      stmt.setString(3, spitter.getFullName());
      stmt.setLong(4, spitter.isUpdateByEmail() ? 1 : 0);
      stmt.setLong(5, spitter.getId());
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      }catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public JdbcSpitter findOne(long id) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = dataSource.getConnection();
      stmt = conn.prepareStatement(SQL_SELECT_SPITTER);
      stmt.setLong(1, id);
      rs = stmt.executeQuery();
      JdbcSpitter spitter = null;
      if (rs.next()) {
        spitter = new JdbcSpitter();
        spitter.setId(rs.getLong("id"));
        spitter.setUsername(rs.getString("username"));
        spitter.setPassword(rs.getString("password"));
        spitter.setFullName(rs.getString("full_name"));
      }
      return spitter;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      }catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
