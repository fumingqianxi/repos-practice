package com.itheima.Java业务开发常见错误100例.a05HTTP调用.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SmsClient")
public interface SmsClient {

    @GetMapping("/ribbonretryissueserver/sms")
    void sendSmsWrong(@RequestParam("mobile") String mobile, @RequestParam("message") String message);

    @PostMapping("/ribbonretryissueserver/sms")
    void sendSmsRight(@RequestParam("mobile") String mobile, @RequestParam("message") String message);
}
