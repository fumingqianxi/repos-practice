package com.itheima.practice.desensitization.enums;


import lombok.Getter;

/**
 * 隐私数据类型枚举
 *
 * @author shibinbin
 * @date 2024/1/24 16:58
 */
@Getter
public enum PrivacyTypeEnum {

  /**
   * 自定义（此项需设置脱敏的范围）
   */
  CUSTOMER,

  /**
   * 姓名
   */
  NAME,

  /**
   * 身份证号
   */
  ID_CARD,

  /**
   * 手机号
   */
  PHONE,

  /**
   * 邮箱
   */
  EMAIL,

  /**
   * 地址
   */
  ADDRESS,

  /**
   * 座机
   */
  LAND_LINE,

  /**
   * 全部
   */
  ALL,

}
