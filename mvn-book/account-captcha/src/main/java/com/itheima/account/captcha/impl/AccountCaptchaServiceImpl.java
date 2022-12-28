package com.itheima.account.captcha.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.itheima.account.captcha.AccountCaptchaService;
import com.itheima.account.captcha.RandomGenerator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 胡磊
 * @since 2022/12/27 14:32
 */
public class AccountCaptchaServiceImpl implements InitializingBean, AccountCaptchaService {

  private DefaultKaptcha producer;

  public void afterPropertiesSet() throws Exception {
    producer = new DefaultKaptcha();
    producer.setConfig(new Config(new Properties()));
  }

  private Map<String, String> captchMap = new HashMap();
  private List<String> preDefinedTexts;
  private int textCount = 0;

  public String generateCaptchaKey() {
    String key = RandomGenerator.getRandomString();
    String value = getCaptchaText();
    captchMap.put(key, value);
    return key;
  }

  private String getCaptchaText() {
    if (preDefinedTexts != null && !preDefinedTexts.isEmpty()) {
      String text = preDefinedTexts.get(textCount);
      textCount = (textCount + 1) % preDefinedTexts.size();
      return text;
    } else {
      return producer.createText();
    }
  }

  public byte[] generateCaptchaImage(String captchaKey) {
    String text = captchMap.get(captchaKey);
    if (text == null) {
      throw new RuntimeException(captchaKey + "not found!");
    }
    BufferedImage image = producer.createImage(text);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpg", out);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write captcha stream!", e);
    }
    return out.toByteArray();
  }

  public boolean validateCaptcha(String captchaKey, String captchaValue) {
    String text = captchMap.get(captchaKey);
    if (text == null) {
      throw new RuntimeException(captchaKey + "not found!");
    }
    if (text.equals(captchaValue)) {
      captchMap.remove(captchaKey);
      return true;
    } else {
      return false;
    }
  }

  public List<String> getPreDefinedTexts() {
    return preDefinedTexts;
  }

  public void setPreDefinedTexts(List<String> preDefinedTexts) {
    this.preDefinedTexts = preDefinedTexts;
  }
}
