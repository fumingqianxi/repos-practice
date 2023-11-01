package com.itheima.mybatis.dto;

import java.util.List;
import lombok.Data;

/**
 * 角色实体DTO.
 *
 * @author 胡磊
 * @since 2023/10/3 21:24
 */
@Data
public class RoleDto {
  private Integer roleId;
  private String roleName;
  private String roleDesc;
  private List<UserDto> users;
}
