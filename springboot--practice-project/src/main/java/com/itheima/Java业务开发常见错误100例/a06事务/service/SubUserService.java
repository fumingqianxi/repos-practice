package com.itheima.Java业务开发常见错误100例.a06事务.service;

import com.itheima.Java业务开发常见错误100例.dao.jpa.UserEntityRepository;
import com.itheima.Java业务开发常见错误100例.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 子用户服务.
 *
 * @author 胡磊
 * @since 2023/11/5 10:13
 */
@Slf4j
@Service
public class SubUserService {

  @Autowired private UserEntityRepository userRepository;

  /**
   * .
   *
   * @param entity .
   */
  @Transactional
  public void createSubUserWithExceptionWrong(UserEntity entity) {
    log.info("createSubUserWithExceptionWrong start");
    userRepository.save(entity);
    throw new RuntimeException("invalid status");
  }

  /**
   * .
   *
   * @param entity .
   */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void createSubUserWithExceptionRight(UserEntity entity) {
    log.info("createSubUserWithExceptionRight start");
    userRepository.save(entity);
    throw new RuntimeException("invalid status");
  }
}
