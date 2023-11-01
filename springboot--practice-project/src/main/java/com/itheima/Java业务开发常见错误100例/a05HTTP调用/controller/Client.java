package com.itheima.Java业务开发常见错误100例.a05HTTP调用.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 胡磊
 * @since 2022/8/21 10:22
 */
@FeignClient(name="clientsdk")
public interface Client {
  @PostMapping("/feignandribbon/server")
  void server();
}
