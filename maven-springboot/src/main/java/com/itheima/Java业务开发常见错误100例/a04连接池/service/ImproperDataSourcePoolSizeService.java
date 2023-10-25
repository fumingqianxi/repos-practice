package com.itheima.Java业务开发常见错误100例.a04连接池.service;

import com.itheima.Java业务开发常见错误100例.dao.jpa.UserEntityRepository;
import com.itheima.Java业务开发常见错误100例.entity.UserEntity;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 *
 * @author 胡磊
 * @since 2023/10/25 9:53
 */
@Slf4j
@Service
public class ImproperDataSourcePoolSizeService {

  @Autowired private UserEntityRepository userEntityRepository;

  /**
   * 注册用户.
   *
   * @return .
   */
  @Transactional
  public UserEntity register() {
    UserEntity user = new UserEntity();
    user.setName("new-user-" + System.currentTimeMillis());
    userEntityRepository.save(user);
    try {
      // 没使用压测工具，所以把时间设置成了3秒，再设置最大连接数为1和连接超时1秒，方便手动测试
      TimeUnit.MILLISECONDS.sleep(3000);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
    return user;
  }
}
