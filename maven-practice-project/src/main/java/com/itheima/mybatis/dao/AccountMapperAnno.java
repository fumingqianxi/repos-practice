package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.AccountDto;
import java.util.List;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

/**
 * 基于注解的账户Mapper.
 *
 * @author 胡磊
 * @since 2023/10/5 17:31
 */
public interface AccountMapperAnno {

  /**
   * 查询所有账户.
   *
   * @return 账户列表
   */
  @Select("select * from account")
  List<AccountDto> findAllAccount();

  /**
   * 根据用户ID查询所有账户.
   *
   * @return 账户列表
   */
  @Select("select * from account where uid=#{id}")
  List<AccountDto> findAccountByUid(Integer uid);

  /**
   * 查询所有账户，包含用户信息.
   *
   * @return 账户列表
   */
  @Select("select * from account")
  @Results(id = "accountMap", value = {
      @Result(id = true, column = "id", property = "id"),
      @Result(column = "uid", property = "uid"),
      @Result(column = "money", property = "money"),
      @Result(property = "userDto", column = "uid",
          one = @One(select = "com.itheima.mybatis.dao.UserMapperAnno.findById",
              fetchType = FetchType.EAGER))
  })
  List<AccountDto> findAllAccountIncludeUser();
}
