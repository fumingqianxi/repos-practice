package com.itheima.spring实战.jdbc.dao.jdbc;

import com.itheima.spring实战.jdbc.dao.JdbcTemplateSpitterRepository;
import com.itheima.spring实战.jdbc.domain.JdbcSpitter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author 胡磊
 * @since 2023/5/7 21:27
 */
public class JdbcTemplateSpitterRepositoryImpl implements JdbcTemplateSpitterRepository {

  private JdbcTemplate jdbcTemplate;

  private static final String INSERT_SPITTER =
      "insert into Spitter (username, password, full_name, email, update_by_email) values (?, ?, ?,"
          + " ?, ?)";
  private static final String SELECT_SPITTER =
      "select id, username, password, full_name, email, update_by_email from Spitter";


  public JdbcTemplateSpitterRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public long count() {
    return jdbcTemplate.queryForLong("select count(id) from Spitter");
  }

  @Override
  public void save(JdbcSpitter jdbcSpitter) {
    jdbcTemplate.update(
        INSERT_SPITTER,
        jdbcSpitter.getUsername(),
        jdbcSpitter.getPassword(),
        jdbcSpitter.getFullName(),
        jdbcSpitter.getEmail(),
        jdbcSpitter.isUpdateByEmail());
  }

  @Override
  public JdbcSpitter findOne(long id) {
    return jdbcTemplate.queryForObject(
        SELECT_SPITTER + " where id=?", new SpitterRowMapper(), id);
  }

  @Override
  public JdbcSpitter findByUsername(String username) {
    return null;
  }

  @Override
  public List<JdbcSpitter> findAll() {
    return null;
  }

  private static final class SpitterRowMapper implements RowMapper<JdbcSpitter> {
    public JdbcSpitter mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String username = rs.getString("username");
      String password = rs.getString("password");
      String fullName = rs.getString("full_name");
      String email = rs.getString("email");
      boolean updateByEmail = rs.getBoolean("update_by_email");
      return new JdbcSpitter(id, username, password, fullName, email, updateByEmail);
    }
  }
}
