package com.itheima.commonmistakes.a04connectionpool;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HttpClient连接池未复用问题.
 *
 * @author 胡磊
 * @since 2023/10/24 18:36
 */
@Slf4j
@RestController
@RequestMapping("/http-client-not-reuse")
public class HttpClientNotReuseController {

  /**
   * http://localhost:45678/http-client-not-reuse/wrong1.
   *
   * @return .
   */
  @GetMapping("/wrong1")
  public String wrong1() {
    CloseableHttpClient client =
        HttpClients.custom()
            .setConnectionManager(new PoolingHttpClientConnectionManager())
            .evictIdleConnections(60, TimeUnit.SECONDS)
            .build();
    try (CloseableHttpResponse response =
        client.execute(new HttpGet("http://localhost:45678/http-client-not-reuse/test"))) {
      return EntityUtils.toString(response.getEntity());
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
    return null;
  }

  /**
   * http://localhost:45678/http-client-not-reuse/test.
   *
   * @return .
   */
  @GetMapping("/test")
  public String test() {
    return "OK";
  }
}
