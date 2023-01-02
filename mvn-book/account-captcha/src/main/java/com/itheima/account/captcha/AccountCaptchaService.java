package com.itheima.account.captcha;

import java.util.List;

/**
 * @author 胡磊
 * @since 2022/12/27 19:27
 */
public interface AccountCaptchaService {
  String generateCaptchaKey()
      throws AccountCaptchaException;

  byte[] generateCaptchaImage( String captchaKey )
      throws AccountCaptchaException;

  boolean validateCaptcha( String captchaKey, String captchaValue )
      throws AccountCaptchaException;

  List<String> getPreDefinedTexts();

  void setPreDefinedTexts(List<String> preDefinedTexts);
}
