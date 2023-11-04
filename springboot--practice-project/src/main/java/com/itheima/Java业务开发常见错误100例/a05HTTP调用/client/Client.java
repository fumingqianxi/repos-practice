package com.itheima.Java业务开发常见错误100例.a05HTTP调用.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Feign客户端.
 *
 * @author 胡磊
 * @since 2023/11/1 10:22
 */
@FeignClient(name = "clientsdk")
public interface Client {

  @PostMapping("/feign-and-ribbon/server")
  void server();
}
