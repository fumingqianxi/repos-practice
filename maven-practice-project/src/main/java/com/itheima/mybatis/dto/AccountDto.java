package com.itheima.mybatis.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 账户实体DTO.
 *
 * @author 胡磊
 * @since 2023/9/28 17:14
 */
@Data
public class AccountDto implements Serializable {

  private Integer id;
  private Integer uid;
  private Double money;
  private UserDto userDto;
}
