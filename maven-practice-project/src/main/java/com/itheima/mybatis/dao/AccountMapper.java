package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.AccountDto;
import com.itheima.mybatis.dto.AccountUserDto;
import java.util.List;

/**
 * 账户Mapper.
 *
 * @author 胡磊
 * @since 2023/9/28 17:31
 */
public interface AccountMapper {

  /**
   * 查询所有账户.
   *
   * @return 账户列表
   */
  List<AccountDto> findAllAccount();

  /**
   * 根据用户ID查询所有账户.
   *
   * @return 账户列表
   */
  List<AccountDto> findAccountByUid(Integer uid);

  /**
   * 查询所有账户，包含用户信息.
   *
   * @return 账户列表
   */
  List<AccountUserDto> findAllAccountUser();

  /**
   * 查询所有账户，包含用户信息.
   *
   * @return 账户列表
   */
  List<AccountDto> findAllAccountIncludeUser();

  /**
   * 查询所有账户，包含用户信息，实现延迟加载.
   *
   * @return 账户列表
   */
  List<AccountDto> findAllAccountIncludeUserLazy();
}
