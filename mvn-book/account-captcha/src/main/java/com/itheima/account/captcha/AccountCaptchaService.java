package com.itheima.account.captcha;

import java.util.List;

/**
 * @author 胡磊
 * @since 2022/12/27 19:27
 */
public interface AccountCaptchaService {

  String generateCaptchaKey();

  byte[] generateCaptchaImage(String captchaKey);

  boolean validateCaptcha(String captchaKey, String captchaValue);

  List<String> getPreDefinedTexts();

  void setPreDefinedTexts(List<String> preDefinedTexts);
}
