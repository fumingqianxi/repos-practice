package com.itheima.account.email;

/**
 * @author 胡磊
 * @since 2022/12/15 17:30
 */
public interface AccountEmailService {

  void sendMail(String to, String subject, String htmlText);
}
