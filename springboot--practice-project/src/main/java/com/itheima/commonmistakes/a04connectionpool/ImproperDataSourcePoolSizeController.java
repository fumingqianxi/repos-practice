package com.itheima.commonmistakes.a04connectionpool;

import com.itheima.commonmistakes.a04connectionpool.service.ImproperDataSourcePoolSizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库连接池大小设置不当示例.
 *
 * @author 胡磊
 * @since 2023/10/25 9:59
 */
@Slf4j
@RestController
@RequestMapping("/improper-data-source-pool-size")
public class ImproperDataSourcePoolSizeController {

  @Autowired
  private ImproperDataSourcePoolSizeService improperDataSourcePoolSizeService;

  /**
   * http://localhost:45678/improper-data-source-pool-size/test.
   *
   * @return .
   */
  @GetMapping("/test")
  public Object test() {
    return improperDataSourcePoolSizeService.register();
  }
}
