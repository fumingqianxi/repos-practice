package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.UserDto;
import java.util.List;

/**
 * 用户Dao.
 *
 * @author 胡磊
 * @since 2023/9/25 15:26
 */
public interface UserDao {

  /**
   * 查询所有用户.
   *
   * @return 用户列表
   */
  List<UserDto> findAll();
}
