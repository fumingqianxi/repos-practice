package com.itheima.mybatis;

import com.itheima.mybatis.custom.io.ResourcesCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionFactoryBuilderCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionFactoryCustom;
import com.itheima.mybatis.dao.AccountMapper;
import com.itheima.mybatis.dao.AccountMapperAnno;
import com.itheima.mybatis.dao.RoleMapper;
import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.dao.UserMapper;
import com.itheima.mybatis.dao.UserMapperAnno;
import com.itheima.mybatis.dao.impl.UserDaoImpl;
import com.itheima.mybatis.dto.AccountDto;
import com.itheima.mybatis.dto.AccountUserDto;
import com.itheima.mybatis.dto.CamelUserDto;
import com.itheima.mybatis.dto.QueryVo;
import com.itheima.mybatis.dto.RoleDto;
import com.itheima.mybatis.dto.UserDto;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Mybatis测试.
 *
 * @author 胡磊
 * @since 2023/9/22 17:55
 */
@Slf4j
public class MybatisTest {

  private InputStream in;
  private SqlSession sqlSession;
  private SqlSession sqlSession2; // 测试缓存用，默认的session在destroy方法中提交和关闭
  private SqlSession sqlSession3; // 测试缓存用，默认的session在destroy方法中提交和关闭
  private UserMapper mapper;
  private UserMapperAnno userMapperAnno;
  private AccountMapperAnno accountMapperAnno;
  private AccountMapper accountMapper;
  private RoleMapper roleMapper;

  @Before
  public void init() throws Exception {
    in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    sqlSession = sqlSessionFactory.openSession();
    sqlSession2 = sqlSessionFactory.openSession();
    sqlSession3 = sqlSessionFactory.openSession();
    mapper = sqlSession.getMapper(UserMapper.class);
    userMapperAnno = sqlSession.getMapper(UserMapperAnno.class);
    accountMapperAnno = sqlSession.getMapper(AccountMapperAnno.class);
    accountMapper = sqlSession.getMapper(AccountMapper.class);
    roleMapper = sqlSession.getMapper(RoleMapper.class);
  }

  @After
  public void destroy() throws Exception {
    // 提交事务
    sqlSession.commit();
    sqlSession.close();
    in.close();
  }

  @Test
  public void testFindAll() throws Exception {
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<UserDto> list = mapper.findAll();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    sqlSession.close();
    in.close();
  }

  @Test
  public void testFindAllByAnno() {
    List<UserDto> list = mapper.findAllByAnno();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  /**
   * 自定义mapper实现类测试.
   *
   * @throws Exception
   */
  @Test
  public void testFindAllCustomImpl() throws Exception {
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    UserDao userDao = new UserDaoImpl(sqlSessionFactory);
    List<UserDto> list = userDao.findAll();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    in.close();
  }

  /**
   * 自定义Mybatis框架测试.
   *
   * @throws Exception
   */
  @Test
  public void testFindAllCustomMybatis() throws Exception {
    InputStream in = ResourcesCustom.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilderCustom builderCustom = new SqlSessionFactoryBuilderCustom();
    SqlSessionFactoryCustom sqlSessionFactoryCustom = builderCustom.build(in);
    SqlSessionCustom sqlSessionCustom = sqlSessionFactoryCustom.openSession();
    UserMapper mapper = sqlSessionCustom.getMapper(UserMapper.class);
    // 测试xml配置
    List<UserDto> list = mapper.findAll();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    log.info("===============分割线==================");
    // 测试注解配置
    List<UserDto> list2 = mapper.findAllByAnno();
    Assert.assertNotNull(list2);
    log.info("用户总数为：{}", list2.size());
    for (UserDto dto : list2) {
      log.info("用户信息：{}", dto);
    }
    sqlSessionCustom.close();
    in.close();
  }

  @Test
  public void testSaveUser() {
    UserDto userDto = new UserDto();
    userDto.setUsername("User" + new Random().nextInt(1000));
    userDto.setAddress("北京市顺义区");
    userDto.setSex("男");
    userDto.setBirthday(new Date());
    log.info("保存数据前：{}", userDto);
    mapper.saveUser(userDto);
    log.info("保存数据后：{}", userDto);
  }

  @Test
  public void testUpdateUser() {
    UserDto userDto = new UserDto();
    userDto.setId(50);
    userDto.setUsername("更新名字");
    userDto.setAddress("北京市顺义区");
    userDto.setSex("女");
    userDto.setBirthday(new Date());
    mapper.updateUser(userDto);
  }

  @Test
  public void testDeleteUser() {
    mapper.deleteUser(12);
  }

  @Test
  public void testFindById() {
    UserDto userDto = mapper.findById(46);
    log.info("用户信息为：{}", userDto);
  }

  @Test
  public void testFindByNameLike() {
    List<UserDto> list = mapper.findByNameLike("%王%");
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindTotal() {
    int total = mapper.findTotal();
    log.info("总记录数为：{}", total);
  }

  @Test
  public void testFindUserByVo() {
    QueryVo vo = new QueryVo();
    UserDto userDto = new UserDto();
    userDto.setUsername("%王%");
    vo.setUserDto(userDto);
    List<UserDto> list = mapper.findUserByVo(vo);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllCamelUser() {
    List<CamelUserDto> list = mapper.findAllCamelUser();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (CamelUserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindUserByCondition() {
    CamelUserDto camelUserDto = new CamelUserDto();
    camelUserDto.setUserName("老王");
    List<UserDto> list = mapper.findUserByCondition(camelUserDto);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindUserInIds() {
    QueryVo vo = new QueryVo();
    List<Integer> ids = new ArrayList<>();
    ids.add(41);
    ids.add(45);
    ids.add(53);
    ids.add(57);
    vo.setIds(ids);
    List<UserDto> list = mapper.findUserInIds(vo);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllAccount() {
    List<AccountDto> list = accountMapper.findAllAccount();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountDto dto : list) {
      log.info("账户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllAccountUser() {
    List<AccountUserDto> list = accountMapper.findAllAccountUser();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountUserDto dto : list) {
      log.info("账户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllAccountIncludeUser() {
    List<AccountDto> list = accountMapper.findAllAccountIncludeUser();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountDto dto : list) {
      log.info("账户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllUserIncludeAccounts() {
    List<UserDto> list = mapper.findAllUserIncludeAccounts();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testFindAllRole() {
    List<RoleDto> list = roleMapper.findAllRole();
    Assert.assertNotNull(list);
    log.info("角色总数为：{}", list.size());
    for (RoleDto dto : list) {
      log.info("角色信息：{}", dto);
    }
  }

  @Test
  public void testFindAllRoleIncludeUsers() {
    List<RoleDto> list = roleMapper.findAllRoleIncludeUsers();
    Assert.assertNotNull(list);
    log.info("角色总数为：{}", list.size());
    for (RoleDto dto : list) {
      log.info("角色信息：{}", dto);
    }
  }

  @Test
  public void testFindAllUserIncludeRoles() {
    List<UserDto> list = mapper.findAllUserIncludeRoles();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  /**
   * 测试查询账户，其中用户信息延迟加载.
   */
  @Test
  public void testFindAllAccountIncludeUserLazy() {
    List<AccountDto> list = accountMapper.findAllAccountIncludeUserLazy();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountDto dto : list) {
      log.info("用户信息：{}", dto.getUserDto());
    }
  }

  /**
   * 测试查询用户，其中账户信息延迟加载.
   */
  @Test
  public void testFindAllUserIncludeAccountsLazy() {
    List<UserDto> list = mapper.findAllUserIncludeAccountsLazy();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (UserDto dto : list) {
      log.info("账号信息：{}", dto.getAccountDtos());
    }
  }

  @Test
  public void testFirstLevelCache() {
    UserMapper mapper = sqlSession2.getMapper(UserMapper.class);
    UserDto userDto = mapper.findById(46);
    UserDto userDto1 = mapper.findById(46);
    log.info("是否相等：{}", userDto == userDto1);
    UserMapper mapper2 = sqlSession3.getMapper(UserMapper.class);
    UserDto userDto2 = mapper2.findById(46);
    log.info("是否相等：{}", userDto == userDto2);
  }

  /**
   * 测试缓存失效.
   */
  @Test
  public void testLoseCache() {
    UserDto userDto = mapper.findById(46);
    userDto.setUsername("老王update");
    mapper.updateUser(userDto);
    UserDto userDto1 = mapper.findById(46);
    log.info("是否相等：{}", userDto == userDto1);
  }

  @Test
  public void testSecondLevelCache() {
    UserMapper mapper = sqlSession2.getMapper(UserMapper.class);
    UserDto userDto = mapper.findById(46);
    sqlSession2.close();
    UserMapper mapper2 = sqlSession3.getMapper(UserMapper.class);
    UserDto userDto2 = mapper2.findById(46);
    log.info("是否相等：{}", userDto == userDto2);
  }

  @Test
  public void testAnnoFindAll() {
    List<CamelUserDto> list = userMapperAnno.findAll();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (CamelUserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testAnnoSaveUser() {
    CamelUserDto userDto = new CamelUserDto();
    userDto.setUserName("注解Mybatis");
    userDto.setUserAddress("北京");
    userMapperAnno.saveUser(userDto);
  }

  @Test
  public void testAnnoUpdateUser() {
    CamelUserDto userDto = new CamelUserDto();
    userDto.setUserId(56);
    userDto.setUserName("注解Mybatis");
    userDto.setUserBirthday(new Date());
    userDto.setUserSex("男");
    userMapperAnno.updateUser(userDto);
  }

  @Test
  public void testAnnoDeleteUser() {
    userMapperAnno.deleteUser(56);
  }

  @Test
  public void testAnnoFindById() {
    UserDto userDto = userMapperAnno.findById(55);
    Assert.assertNotNull(userDto);
    log.info("用户信息：{}", userDto);
  }

  @Test
  public void testAnnoFindByNameLike() {
    List<CamelUserDto> list = userMapperAnno.findByNameLike("%王%");
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (CamelUserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testAnnoFindTotal() {
    int total = userMapperAnno.findTotal();
    log.info("总数为：{}", total);
  }

  @Test
  public void testAnnoFindAllUserIncludeAccounts() {
    List<CamelUserDto> list = userMapperAnno.findAllUserIncludeAccounts();
    Assert.assertNotNull(list);
    log.info("用户总数为：{}", list.size());
    for (CamelUserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
  }

  @Test
  public void testAnnoFindAllAccount() {
    List<AccountDto> list = accountMapperAnno.findAllAccount();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountDto dto : list) {
      log.info("账户信息：{}", dto);
    }
  }

  @Test
  public void testAnnoFindAllAccountIncludeUser() {
    List<AccountDto> list = accountMapperAnno.findAllAccountIncludeUser();
    Assert.assertNotNull(list);
    log.info("账户总数为：{}", list.size());
    for (AccountDto dto : list) {
      log.info("账户信息：{}", dto);
    }
  }
}
