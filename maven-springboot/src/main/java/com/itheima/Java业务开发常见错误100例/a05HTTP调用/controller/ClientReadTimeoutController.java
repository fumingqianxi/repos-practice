package com.itheima.Java业务开发常见错误100例.a05HTTP调用.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.client.fluent.Request;

/**
 * @author 胡磊
 * @since 2022/8/20 23:24
 */
@Slf4j
@RestController
@RequestMapping("client-read-timeout")
public class ClientReadTimeoutController {
  private String getResponse(String url, int connectTimeout, int readTimeout) throws IOException {
    return Request.Get("http://localhost:45678/client-read-timeout" + url)
        .connectTimeout(connectTimeout)
        .socketTimeout(readTimeout)
        .execute()
        .returnContent()
        .asString();
  }

  @GetMapping("client")
  public String client() throws IOException {
    log.info("client called");
    return getResponse("/server?timeout=5000", 1000, 2000);
  }

  @GetMapping("server")
  public void server(@RequestParam("timeout") int timeout) throws InterruptedException {
    log.info("server called");
    TimeUnit.MILLISECONDS.sleep(timeout);
    log.info("Done");
  }
}
