package com.itheima.Java业务开发常见错误100例.a06事务.service;

import com.itheima.Java业务开发常见错误100例.dao.jpa.UserEntityRepository;
import com.itheima.Java业务开发常见错误100例.entity.UserEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 用户服务.
 *
 * @author 胡磊
 * @since 2023/11/4 22:59
 */
@Slf4j
@Service
public class TransactionRollbackFailedUserService {

  @Autowired private UserEntityRepository userRepository;

  /**
   * .
   *
   * @param name .
   */
  @Transactional
  public void createUserWrong1(String name) {
    try {
      userRepository.save(new UserEntity(name));
      throw new RuntimeException("error");
    } catch (Exception ex) {
      log.error("create user failed", ex);
    }
  }

  /**
   * .
   *
   * @param name .
   * @throws IOException .
   */
  @Transactional
  public void createUserWrong2(String name) throws IOException {
    userRepository.save(new UserEntity(name));
    otherTask();
  }

  private void otherTask() throws IOException {
    Files.readAllLines(Paths.get("file-that-not-exist"));
  }

  public int getUserCount(String name) {
    return userRepository.findByName(name).size();
  }

  /**
   * .
   *
   * @param name .
   */
  @Transactional
  public void createUserRight1(String name) {
    try {
      userRepository.save(new UserEntity(name));
      throw new RuntimeException("error");
    } catch (Exception ex) {
      log.error("create user failed", ex);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    log.info("result {} ", userRepository.findByName(name).size()); // 为什么这里是1你能想明白吗？
  }

  // DefaultTransactionAttribute
  @Transactional(rollbackFor = Exception.class)
  public void createUserRight2(String name) throws IOException {
    userRepository.save(new UserEntity(name));
    otherTask();
  }
}
