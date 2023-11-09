package com.itheima.commonmistakes.a05httpinvoke;

import com.itheima.commonmistakes.a05httpinvoke.client.Client;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign和Ribbon常见错误示例.
 *
 * @author 胡磊
 * @since 2023/11/1 16:32
 */
@Slf4j
@RestController
@RequestMapping("/feign-and-ribbon")
public class FeignAndRibbonController {

  @Autowired private Client client;

  /**
   * http://localhost:45678/feign-and-ribbon/client.
   */
  @GetMapping("/client")
  public void timeout() {
    long begin = System.currentTimeMillis();
    try {
      client.server();
    } catch (Exception ex) {
      log.warn("执行耗时：{}ms 错误：{}", System.currentTimeMillis() - begin, ex.getMessage());
    }
  }

  @PostMapping("/server")
  public void server() throws InterruptedException {
    TimeUnit.MINUTES.sleep(10);
  }
}
