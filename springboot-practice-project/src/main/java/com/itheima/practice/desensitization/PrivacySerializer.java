package com.itheima.practice.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.itheima.practice.desensitization.annotation.PrivacyEncrypt;
import com.itheima.practice.desensitization.enums.PrivacyTypeEnum;
import com.itheima.common.util.CheckEmptyUtil;
import com.itheima.common.util.PrivacyUtil;
import java.io.IOException;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 数据脱敏注解拦截器
 *
 * @author shibinbin
 * @date 2024/1/24 16:59
 */
@NoArgsConstructor
@AllArgsConstructor
public class PrivacySerializer extends JsonSerializer<String> implements ContextualSerializer {

  // 脱敏类型
  private PrivacyTypeEnum privacyTypeEnum;
  // 前几位不脱敏
  private Integer prefixNoMaskLen;
  // 最后几位不脱敏
  private Integer suffixNoMaskLen;
  // 用什么打码
  private String symbol;

  @Override
  public void serialize(
      String origin, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider)
      throws IOException {
    if (CheckEmptyUtil.isEmpty(origin)) {
      origin = null;
    }
    if (null != privacyTypeEnum) {
      switch (privacyTypeEnum) {
        case CUSTOMER:
          jsonGenerator.writeString(
              PrivacyUtil.desValue(origin, prefixNoMaskLen, suffixNoMaskLen, symbol));
          break;
        case NAME:
          jsonGenerator.writeString(PrivacyUtil.hideChineseName(origin));
          break;
        case ID_CARD:
          jsonGenerator.writeString(PrivacyUtil.hideIdCard(origin));
          break;
        case PHONE:
          jsonGenerator.writeString(PrivacyUtil.hidePhone(origin));
          break;
        case EMAIL:
          jsonGenerator.writeString(PrivacyUtil.hideEmail(origin));
          break;
        case ADDRESS:
          jsonGenerator.writeString(PrivacyUtil.hideAddress(origin));
          break;
        case LAND_LINE:
          jsonGenerator.writeString(PrivacyUtil.hideLandline(origin));
          break;
        case ALL:
          jsonGenerator.writeString(PrivacyUtil.hideAll(origin));
          break;
        default:
          throw new IllegalArgumentException("unknown privacy type enum " + privacyTypeEnum);
      }
    }
  }

  @Override
  public JsonSerializer<?> createContextual(
      final SerializerProvider serializerProvider, final BeanProperty beanProperty)
      throws JsonMappingException {
    if (null != beanProperty) {
      if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
        PrivacyEncrypt privacyEncrypt = beanProperty.getAnnotation(PrivacyEncrypt.class);
        if (null == privacyEncrypt) {
          privacyEncrypt = beanProperty.getContextAnnotation(PrivacyEncrypt.class);
        }
        if (null != privacyEncrypt) {
          return new PrivacySerializer(
              privacyEncrypt.type(), privacyEncrypt.prefixNoMaskLen(),
              privacyEncrypt.suffixNoMaskLen(), privacyEncrypt.symbol());
        }
      }
      return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
    return serializerProvider.findNullValueSerializer(null);
  }
}
