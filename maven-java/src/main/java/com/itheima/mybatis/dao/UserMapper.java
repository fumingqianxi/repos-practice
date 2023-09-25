package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.UserDto;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper.
 *
 * @author 胡磊
 * @since 2023/9/22 15:26
 */
public interface UserMapper {

  /**
   * 查询所有用户.
   *
   * @return 用户列表
   */
  List<UserDto> findAll();

  /**
   * 使用注解查询所有用户
   *
   * @return 用户列表
   */
  @Select("select * from user")
  List<UserDto> findAllByAnno();
}
