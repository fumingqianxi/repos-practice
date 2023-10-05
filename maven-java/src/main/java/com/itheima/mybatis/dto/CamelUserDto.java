package com.itheima.mybatis.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 用户实体DTO.
 *
 * @author 胡磊
 * @since 2023/9/22 15:28
 */
@Data
public class CamelUserDto {
  private Integer userId;
  private String userName;
  private Date userBirthday;
  private String userSex;
  private String userAddress;
  private List<AccountDto> accounts;
}
