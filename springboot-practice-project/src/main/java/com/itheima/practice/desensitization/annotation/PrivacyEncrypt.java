package com.itheima.practice.desensitization.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itheima.practice.desensitization.PrivacySerializer;
import com.itheima.practice.desensitization.enums.PrivacyTypeEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义数据脱敏注解
 *
 * @author shibinbin
 * @date 2024/1/24 16:59
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = PrivacySerializer.class)
public @interface PrivacyEncrypt {


  /**
   * 脱敏数据类型（没给默认值，所以使用时必须指定type）
   */
  PrivacyTypeEnum type();

  /**
   * 前置不需要打码的长度
   */
  int prefixNoMaskLen() default 1;

  /**
   * 后置不需要打码的长度
   */
  int suffixNoMaskLen() default 1;

  /**
   * 用什么打码
   */
  String symbol() default "*";
}

