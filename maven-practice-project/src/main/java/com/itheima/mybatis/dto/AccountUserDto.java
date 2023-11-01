package com.itheima.mybatis.dto;

import lombok.Data;

/**
 * 账户实体DTO.
 *
 * @author 胡磊
 * @since 2023/9/28 17:14
 */
@Data
public class AccountUserDto extends AccountDto {

  private String username;
  private String address;

  @Override
  public String toString() {
    return super.toString() + "AccountUserDto{" +
        "username='" + username + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
