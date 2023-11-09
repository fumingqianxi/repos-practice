package com.itheima.commonmistakes.a11nullvalue;

import com.itheima.commonmistakes.dao.jpa.UserEntityRepository;
import com.itheima.commonmistakes.entity.UserEntity;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库空问题示例.
 *
 * @author 胡磊
 * @since 2023/11/7 13:20
 */
@Slf4j
@RestController
@RequestMapping("/db-null")
public class DbNullController {

  @Autowired private UserEntityRepository userRepository;

  @PostConstruct
  public void init() {
    userRepository.save(new UserEntity());
  }

  /**
   * http://localhost:45678/db-null/wrong.
   */
  @GetMapping("/wrong")
  public void wrong() {
    log.info(
        "result: {} {} {} ",
        userRepository.wrong1(),
        userRepository.wrong2(),
        userRepository.wrong3());
  }

  /**
   * http://localhost:45678/db-null/right.
   */
  @GetMapping("/right")
  public void right() {
    log.info(
        "result: {} {} {} ",
        userRepository.right1(),
        userRepository.right2(),
        userRepository.right3());
  }
}
