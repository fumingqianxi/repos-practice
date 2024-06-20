package com.itheima.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shibinbin
 * @date 2024/1/24 16:59
 */
@Component
@Slf4j
public class PrivacyUtil {
  /** 中文名脱敏 */
  public static String hideChineseName(String chineseName) {
    if (CheckEmptyUtil.isEmpty(chineseName)) {
      return null;
    }
    if (chineseName.length() <= 2) {
      return desValue(chineseName, 1, 0, "*");
    }
    return desValue(chineseName, 1, 1, "*");
  }

  /** 电话号脱敏 */
  public static String hidePhone(String phone) {
    if (CheckEmptyUtil.isEmpty(phone)) {
      return null;
    }
    if (isMobileNumber(phone)) {
      return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    } else {
      return phone.replaceAll("(?<=.{0,}).{4}$", "****");
    }
  }

  public static boolean isMobileNumber(String number) {
    // 手机号正则表达式
    String regex = "^1[3-9]\\d{9}$";
    return number.matches(regex);
  }

  public static boolean isLandlineNumber(String number) {
    // 座机号正则表达式
    String regex = "^0\\d{2,3}-\\d{7,8}$";
    return number.matches(regex);
  }

  /** 地址脱敏 */
  public static String hideAddress(String address) {
    if (CheckEmptyUtil.isEmpty(address)) {
      return null;
    }
    if (address.length()<4){
      //地址位数小于四位
      return "****";
    }
    // 隐藏后四位
    return address.replaceAll("(?<=.{0,}).{4}$", "****");
  }

  /** 座机脱敏 */
  public static String hideLandline(String landline) {
    if (CheckEmptyUtil.isEmpty(landline)) {
      return null;
    }
    if (landline.length()<4){
      //小于四位
      return "****";
    }
    // 隐藏后四位
    return landline.replaceAll("(?<=.{0,}).{4}$", "****");
  }

  /** 全部脱敏 */
  public static String hideAll(String str) {
    if (CheckEmptyUtil.isEmpty(str)) {
      return null;
    }
    return desValue(str, 0, 0, "*");
  }

  /** 邮箱脱敏 */
  public static String hideEmail(String email) {
    if (CheckEmptyUtil.isEmpty(email)) {
      return null;
    }
    return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
  }

  /** 身份证号脱敏 */
  public static String hideIdCard(String idCard) {
    if (CheckEmptyUtil.isEmpty(idCard)) {
      return null;
    }
    return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
  }

  /**
   * 对字符串进行脱敏操作
   *
   * @param origin 原始字符串
   * @param prefixNoMaskLen 左侧需要保留几位明文字段
   * @param suffixNoMaskLen 右侧需要保留几位明文字段
   * @param maskStr 用于遮罩的字符串, 如'*'
   * @return 脱敏后结果
   */
  public static String desValue(
      String origin, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr) {
    if (CheckEmptyUtil.isEmpty(origin)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0, n = origin.length(); i < n; i++) {
      if (i < prefixNoMaskLen) {
        sb.append(origin.charAt(i));
        continue;
      }
      if (i > (n - suffixNoMaskLen - 1)) {
        sb.append(origin.charAt(i));
        continue;
      }
      sb.append(maskStr);
    }
    return sb.toString();
  }
}
