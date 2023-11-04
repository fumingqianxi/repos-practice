package com.itheima.Java业务开发常见错误100例.a05HTTP调用;

import com.itheima.Java业务开发常见错误100例.a05HTTP调用.client.SmsClient;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ribbon自动重试错误示例客户端.
 *
 * @author 胡磊
 * @since 2023/11/1 17:16
 */
@Slf4j
@RestController
@RequestMapping("ribbon-retry-issue-client")
public class RibbonRetryIssueClientController {

  @Autowired private SmsClient smsClient;

  /**
   * http://localhost:45678/ribbon-retry-issue-client/wrong.
   *
   * @return .
   */
  @GetMapping("wrong")
  public String wrong() {
    log.info("client is called");
    try {
      smsClient.sendSmsWrong("13600000000", UUID.randomUUID().toString());
    } catch (Exception ex) {
      log.error("send sms failed : {}", ex.getMessage());
    }
    return "done";
  }

  /**
   * http://localhost:45678/ribbon-retry-issue-client/right.
   *
   * @return .
   */
  @GetMapping("right")
  public String right() {
    log.info("client is called");
    try {
      smsClient.sendSmsRight("13600000000", UUID.randomUUID().toString());
    } catch (Exception ex) {
      log.error("send sms failed : {}", ex.getMessage());
    }
    return "done";
  }
}
