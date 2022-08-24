package com.itheima.Java业务开发常见错误100例.a05HTTP调用.controller;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author {胡磊}
 * @since 2022/8/21 11:33
 */
@Slf4j
@RestController
@RequestMapping("ribbonretryissueserver")
public class RibbonRetryIssueServerController {

  @GetMapping("sms")
  public void sendSmsWrong(@RequestParam("mobile") String mobile, @RequestParam("message")
                           String message, HttpServletRequest request) throws InterruptedException{
    log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
    TimeUnit.SECONDS.sleep(2);
  }

  @PostMapping("sms")
  public void sendSmsRight(@RequestParam("mobile") String mobile, @RequestParam("message") String message, HttpServletRequest request) throws InterruptedException {
    log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
    TimeUnit.SECONDS.sleep(2);
  }
}
