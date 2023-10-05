package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.CamelUserDto;
import com.itheima.mybatis.dto.QueryVo;
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
   * 使用注解查询所有用户.
   *
   * @return 用户列表
   */
  @Select("select * from user")
  List<UserDto> findAllByAnno();

  /**
   * 保存用户.
   *
   * @param userDto 用户实体.
   */
  void saveUser(UserDto userDto);

  /**
   * 更新用户.
   *
   * @param userDto 用户实体.
   */
  void updateUser(UserDto userDto);

  /**
   * 删除用户.
   *
   * @param userId 用户ID
   */
  void deleteUser(Integer userId);

  /**
   * 根据ID查询用户.
   *
   * @param userId 用户ID
   * @return 用户实体
   */
  UserDto findById(Integer userId);

  /**
   * 根据名称模糊查询用户.
   *
   * @param name 用户名
   * @return 用户列表
   */
  List<UserDto> findByNameLike(String name);

  /**
   * 查询总记录数.
   *
   * @return 总记录数
   */
  int findTotal();

  /**
   * 根据queryVo条件模糊查询用户.
   *
   * @param vo 查询条件
   * @return 用户列表
   */
  List<UserDto> findUserByVo(QueryVo vo);

  /**
   * 查询全部用户，用户DTO与数据库字段不匹配.
   *
   * @return 用户列表
   */
  List<CamelUserDto> findAllCamelUser();

  /**
   * 按条件查询用户.
   *
   * @param camelUserDto 查询条件，属性可能为空
   * @return 用户列表
   */
  List<UserDto> findUserByCondition(CamelUserDto camelUserDto);

  /**
   * 查询ID在指定集合中的用户.
   *
   * @param vo 查询条件
   * @return 用户列表
   */
  List<UserDto> findUserInIds(QueryVo vo);

  /**
   * 查询所有用户，包含账户信息.
   *
   * @return 用户列表
   */
  List<UserDto> findAllUserIncludeAccounts();

  /**
   * 查询所有用户，包含角色信息.
   *
   * @return 用户列表
   */
  List<UserDto> findAllUserIncludeRoles();

  /**
   * 查询所有用户，包含账户信息，实现延迟加载.
   *
   * @return 用户列表
   */
  List<UserDto> findAllUserIncludeAccountsLazy();
}
