package com.itheima.Java业务开发常见错误100例.a06事务.transactionrollbackfailed;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 胡磊
 * @since 2022/8/22 22:59
 */
@Service
@Slf4j
public class UserService {

  @Autowired
  private UserRepositoryDemo userRepository;

  @Autowired
  private UserService self;

  @PostConstruct
  public void init() {
    log.info("this {} self {}", this.getClass().getName(), self.getClass().getName());
  }

  public int createUserWrong1(String name) {
    try{
      this.createUserPrivate(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  //自调用
  public int createUserWrong2(String name) {
    try {
      this.createUserPublic(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  //重新注入自己
  public int createUserRight(String name) {
    try {
      self.createUserPublic(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  @Transactional
  private void createUserPrivate(UserEntity entity) {
    userRepository.save(entity);
    if (entity.getName().contains("test"))
      throw new RuntimeException("invalid username!");
  }

  //可以传播出异常
  @Transactional
  public void createUserPublic(UserEntity entity) {
    userRepository.save(entity);
    if (entity.getName().contains("test"))
      throw new RuntimeException("invalid username!");
  }

  public int getUserCount(String name) {
    return userRepository.findByName(name).size();
  }
}
