package com.itheima.mybatis.dto;

import java.util.Date;
import lombok.Data;

/**
 * 用户实体DTO.
 *
 * @author 胡磊
 * @since 2023/9/22 15:28
 */
@Data
public class UserDto {
  private Integer id;
  private String username;
  private Date birthday;
  private String sex;
  private String address;
}
