package com.itheima.commonmistakes.a06transaction.service;

import com.itheima.dao.jpa.UserEntityRepository;
import com.itheima.entity.UserEntity;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务.
 *
 * @author 胡磊
 * @since 2023/11/4 22:59
 */
@Slf4j
@Service
public class TransactionProxyFailedUserService {

  @Autowired private UserEntityRepository userRepository;

  @Autowired private TransactionProxyFailedUserService self;

  @PostConstruct
  public void init() {
    log.info("this {} self {}", this.getClass().getName(), self.getClass().getName());
  }

  /**
   * .
   *
   * @param name .
   * @return .
   */
  public int createUserWrong1(String name) {
    try {
      this.createUserPrivate(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  /**
   * 自调用.
   *
   * @param name .
   * @return .
   */
  public int createUserWrong2(String name) {
    try {
      this.createUserPublic(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  /**
   * 重新注入自己.
   *
   * @param name .
   * @return .
   */
  public int createUserRight(String name) {
    try {
      self.createUserPublic(new UserEntity(name));
      log.info("查询得到的数据长度为{}", userRepository.findByName(name).size());
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userRepository.findByName(name).size();
  }

  @Transactional
  private void createUserPrivate(UserEntity entity) {
    userRepository.save(entity);
    if (entity.getName().contains("test")) {
      throw new RuntimeException("invalid username!");
    }
  }

  /**
   * 可以传播出异常.
   *
   * @param entity .
   */
  @Transactional
  public void createUserPublic(UserEntity entity) {
    userRepository.save(entity);
    log.info("查询得到的数据长度为{}", userRepository.findByName(entity.getName()).size());
    if (entity.getName().contains("test")) {
      throw new RuntimeException("invalid username!");
    }
  }

  public int getUserCount(String name) {
    return userRepository.findByName(name).size();
  }
}
