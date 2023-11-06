package com.itheima.commonmistakes.a05httpinvoke;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ribbon自动重试错误示例服务端.
 *
 * @author 胡磊
 * @since 2023/11/1 17:13
 */
@Slf4j
@RestController
@RequestMapping("/ribbon-retry-issue-server")
public class RibbonRetryIssueServerController {

  @GetMapping("/sms")
  public void sendSmsWrong(
      @RequestParam("mobile") String mobile,
      @RequestParam("message") String message,
      HttpServletRequest request)
      throws InterruptedException {
    log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
    TimeUnit.SECONDS.sleep(2);
  }

  @PostMapping("/sms")
  public void sendSmsRight(
      @RequestParam("mobile") String mobile,
      @RequestParam("message") String message,
      HttpServletRequest request)
      throws InterruptedException {
    log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
    TimeUnit.SECONDS.sleep(2);
  }
}
