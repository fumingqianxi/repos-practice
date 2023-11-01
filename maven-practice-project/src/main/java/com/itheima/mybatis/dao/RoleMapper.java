package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.RoleDto;
import java.util.List;

/**
 * 角色Mapper.
 *
 * @author 胡磊
 * @since 2023/10/3 17:31
 */
public interface RoleMapper {

  /**
   * 查询所有角色.
   *
   * @return 角色列表
   */
  List<RoleDto> findAllRole();

  /**
   * 查询所有角色，包含用户信息.
   *
   * @return 角色列表
   */
  List<RoleDto> findAllRoleIncludeUsers();
}
