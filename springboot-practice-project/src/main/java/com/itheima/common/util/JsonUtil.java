package com.itheima.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
  private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
  private static ObjectMapper mapper = new ObjectMapper();
  /** 下划线和驼峰互转. */
  private static ObjectMapper snakeMapper = new ObjectMapper();

  private static final String ERROR_FORMAT = "deserializeObject error, string:{}";

  static {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // 允许使用未带引号的字段名
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    // 允许使用单引号
    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

    snakeMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // 允许操作localtime对象
    mapper.registerModule(new JavaTimeModule());
    // 允许使用未带引号的字段名
    snakeMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    // 允许使用单引号
    snakeMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    snakeMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  private JsonUtil() {}

  /**
   * .
   *
   * @param o .
   * @return
   */
  public static String serializeObject(Object o) {
    try {
      return mapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      logger.error("serializeObject error", e);
      return null;
    }
  }

  /**
   * .
   *
   * @param s .
   * @param clazz .
   * @return
   */
  public static Object deserializeObject(String s, Class<?> clazz) {
    try {
      return mapper.readValue(s, clazz);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, s, e);
      return null;
    }
  }

  /**
   * .
   *
   * @param s .
   * @param typeReference .
   * @param <T> .
   * @return
   */
  public static <T> T deserializeObject(String s, TypeReference<T> typeReference) {
    try {
      return mapper.readValue(s, typeReference);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, s, e);
      return null;
    }
  }

  /**
   * .
   *
   * @param bytes .
   * @param typeReference .
   * @param <T> .
   * @return
   */
  public static <T> T deserializeObject(byte[] bytes, TypeReference<T> typeReference) {
    try {
      return mapper.readValue(bytes, typeReference);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, new String(bytes), e);
      return null;
    }
  }

  /**
   * 驼峰转下划线.
   *
   * @param o .
   * @return
   */
  public static String serializeObjectWithSnake(Object o) {
    try {
      return snakeMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      logger.error("serializeObject error", e);
      return null;
    }
  }

  /**
   * 下划线转驼峰.
   *
   * @param s .
   * @param clazz .
   * @return
   */
  public static Object deserializeObjectWithSnake(String s, Class<?> clazz) {
    try {
      return snakeMapper.readValue(s, clazz);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, s, e);
      return null;
    }
  }

  /**
   * 下划线转驼峰.
   *
   * @param s .
   * @param typeReference .
   * @param <T> .
   * @return
   */
  public static <T> T deserializeObjectWithSnake(String s, TypeReference<T> typeReference) {
    try {
      return snakeMapper.readValue(s, typeReference);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, s, e);
      return null;
    }
  }

  /**
   * 下划线转驼峰.
   *
   * @param bytes .
   * @param typeReference .
   * @param <T> .
   * @return
   */
  public static <T> T deserializeObjectWithSnake(byte[] bytes, TypeReference<T> typeReference) {
    try {
      return snakeMapper.readValue(bytes, typeReference);
    } catch (IOException e) {
      logger.error(ERROR_FORMAT, new String(bytes), e);
      return null;
    }
  }
}
