package com.itheima.commonmistakes.a06transaction.service;

import com.itheima.dao.jpa.UserEntityRepository;
import com.itheima.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务.
 *
 * @author 胡磊
 * @since 2023/11/5 10:59
 */
@Service
@Slf4j
public class TransactionPropagationUserService {

  @Autowired private UserEntityRepository userRepository;

  @Autowired private SubUserService subUserService;

  @Transactional
  public void createUserWrong(UserEntity entity) {
    createMainUser(entity);
    subUserService.createSubUserWithExceptionWrong(entity);
  }

  public int getUserCount(String name) {
    return userRepository.findByName(name).size();
  }

  /**
   * .
   *
   * @param entity .
   */
  @Transactional
  public void createUserWrong2(UserEntity entity) {
    createMainUser(entity);
    try {
      subUserService.createSubUserWithExceptionWrong(entity);
    } catch (Exception ex) {
      // 虽然捕获了异常，但是因为没有开启新事务，而当前事务因为异常已经被标记为rollback了，所以最终还是会回滚。
      log.error("create sub user error:{}", ex.getMessage());
    }
  }

  /**
   * .
   *
   * @param entity .
   */
  @Transactional
  public void createUserRight(UserEntity entity) {
    createMainUser(entity);
    try {
      subUserService.createSubUserWithExceptionRight(entity);
    } catch (Exception ex) {
      // 捕获异常，防止主方法回滚
      log.error("create sub user error:{}", ex.getMessage());
    }
  }

  private void createMainUser(UserEntity entity) {
    userRepository.save(entity);
    log.info("createMainUser finish");
  }
}
