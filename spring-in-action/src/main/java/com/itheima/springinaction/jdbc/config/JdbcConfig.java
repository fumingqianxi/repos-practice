package com.itheima.springinaction.jdbc.config;

import com.itheima.springinaction.jdbc.dao.JdbcTemplateSpitterRepository;
import com.itheima.springinaction.jdbc.dao.impl.JdbcSpitterRepositoryImpl;
import com.itheima.springinaction.jdbc.dao.impl.JdbcTemplateSpitterRepositoryImpl;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author 胡磊
 * @since 2023/5/6 17:42
 */
@Configuration
public class JdbcConfig {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://polardb-hl.mysql.polardb.rds.aliyuncs.com:3306/dev");
    ds.setUsername("hl_dev");
    ds.setPassword("hl&12345");
    return ds;
  }

  @Bean
  public JdbcSpitterRepositoryImpl jdbcSpitterRepository(DataSource dataSource) {
    JdbcSpitterRepositoryImpl jdbcSpitterRepository = new JdbcSpitterRepositoryImpl();
    jdbcSpitterRepository.setDataSource(dataSource);
    return jdbcSpitterRepository;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public JdbcTemplateSpitterRepository jdbcTemplateSpitterRepository(JdbcTemplate jdbcTemplate) {
    JdbcTemplateSpitterRepositoryImpl jdbcTemplateSpitterRepository = new JdbcTemplateSpitterRepositoryImpl(jdbcTemplate);
    return jdbcTemplateSpitterRepository;
  }
}
