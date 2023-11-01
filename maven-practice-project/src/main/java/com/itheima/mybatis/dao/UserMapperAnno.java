package com.itheima.mybatis.dao;

import com.itheima.mybatis.dto.CamelUserDto;
import com.itheima.mybatis.dto.UserDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

/**
 * 基于注解的用户Mapper.
 *
 * @author 胡磊
 * @since 2023/10/5 15:26
 */
public interface UserMapperAnno {

  /**
   * 查询所有用户.
   *
   * @return 用户列表
   */
  @Select("select * from user")
  @Results(id = "userMap", value = {
      @Result(id = true, column = "id", property = "userId"),
      @Result(column = "username", property = "userName"),
      @Result(column = "sex", property = "userSex"),
      @Result(column = "birthday", property = "userBirthday"),
      @Result(column = "address", property = "userAddress")
  })
  List<CamelUserDto> findAll();

  /**
   * 保存用户.
   *
   * @param userDto 用户实体.
   */
  @Insert(
      "insert into user(username,sex,address,birthday)values(#{userName},#{userSex},"
          + "#{userAddress},#{userBirthday})")
  void saveUser(CamelUserDto userDto);

  /**
   * 更新用户.
   *
   * @param userDto 用户实体.
   */
  @Update(
      "update user set username=#{userName},sex=#{userSex},birthday=#{userBirthday},"
          + "address=#{userAddress} where id=#{userId}")
  void updateUser(CamelUserDto userDto);

  /**
   * 删除用户.
   *
   * @param userId 用户ID
   */
  @Delete("delete from user where id=#{id}")
  void deleteUser(Integer userId);

  /**
   * 根据ID查询用户.
   *
   * @param userId 用户ID
   * @return 用户实体
   */
  @Select("select * from user where id=#{id}")
  UserDto findById(Integer userId);

  /**
   * 根据名称模糊查询用户.
   *
   * @param name 用户名
   * @return 用户列表
   */
  @Select("select * from user where username like #{username} ")
  @ResultMap("userMap")
  List<CamelUserDto> findByNameLike(String name);

  /**
   * 查询总记录数.
   *
   * @return 总记录数
   */
  @Select("select count(*) from user ")
  int findTotal();

  /**
   * 查询所有用户，包含账户信息.
   *
   * @return 用户列表
   */
  @Select("select * from user")
  @Results(value = {
      @Result(id = true, column = "id", property = "userId"),
      @Result(column = "username", property = "userName"),
      @Result(column = "sex", property = "userSex"),
      @Result(column = "birthday", property = "userBirthday"),
      @Result(column = "address", property = "userAddress"),
      @Result(column = "id", property = "accounts",
          many = @Many(select = "com.itheima.mybatis.dao.AccountMapperAnno.findAccountByUid",
              fetchType = FetchType.LAZY))
  })
  List<CamelUserDto> findAllUserIncludeAccounts();
}
