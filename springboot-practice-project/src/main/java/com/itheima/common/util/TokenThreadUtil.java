package com.itheima.common.util;

import com.itheima.common.enums.JwtTokenKey;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** token的local. */
public class TokenThreadUtil {

  private static final ThreadLocal<Map<String, Object>> TOKEN_LOCAL =
      ThreadLocal.withInitial(HashMap::new);

  private static Logger logger = LoggerFactory.getLogger(TokenThreadUtil.class);

  private TokenThreadUtil() {}

  /**
   * 获取token信息.
   *
   * @return
   */
  private static Map<String, Object> getTokenLocal() {
    return TOKEN_LOCAL.get();
  }

  /** 设置. */
  public static void setTokenLocal() {
    Map<String, Object> additions = JwtTokenUtil.getAdditionInfo();
    if (CheckEmptyUtil.isEmpty(additions)) {
      Map<String, Object> empty = new HashMap<>();
      TOKEN_LOCAL.set(empty);
      return;
    }
    TOKEN_LOCAL.set(JwtTokenUtil.getAdditionInfo());
  }

  /**
   * 设置用户编码.
   *
   * @param userCode userCode.
   */
  public static void setUserCode(String userCode) {
    Map<String, Object> map = getTokenLocal();
    map.put(JwtTokenKey.USER_CODE.getKey(), userCode);
  }

  /**
   * 设置用户名称.
   *
   * @param userName userName.
   */
  public static void setUserName(String userName) {
    Map<String, Object> map = getTokenLocal();
    map.put(JwtTokenKey.USER_NAME.getKey(), userName);
  }


  /**
   * 用户编码.
   *
   * @return 用户编码.
   */
  public static String getUserCode() {
    Map<String, Object> map = getTokenLocal();
    if (CheckEmptyUtil.isEmpty(map)) {
      return null;
    }
    String result = null;
    Object value = map.get(JwtTokenKey.USER_CODE.getKey());
    if (value != null) {
      result = value.toString();
    }
    return result;
  }

  /**
   * 用户名称.
   *
   * @return 用户id.
   */
  public static String getUserName() {
    Map<String, Object> map = getTokenLocal();
    if (CheckEmptyUtil.isEmpty(map)) {
      return null;
    }
    String result = null;
    Object value = map.get(JwtTokenKey.USER_NAME.getKey());
    if (value != null) {
      result = value.toString();
    }
    return result;
  }

  /** 清理. */
  public static void cleanTokenLocal() {
    TOKEN_LOCAL.remove();
  }
}
