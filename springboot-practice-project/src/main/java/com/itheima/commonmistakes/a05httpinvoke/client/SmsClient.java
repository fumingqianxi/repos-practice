package com.itheima.commonmistakes.a05httpinvoke.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SmsClient")
public interface SmsClient {

    @GetMapping("/ribbon-retry-issue-server/sms")
    void sendSmsWrong(@RequestParam("mobile") String mobile, @RequestParam("message") String message);

    @PostMapping("/ribbon-retry-issue-server/sms")
    void sendSmsRight(@RequestParam("mobile") String mobile, @RequestParam("message") String message);
}
