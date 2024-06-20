package com.itheima.common.util;

import com.itheima.common.config.bean.SpringBeanUtil;
import com.itheima.common.enums.JwtTokenKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

/**
 * 主要是从jwt token中提取对应的信息.
 *
 * @author 蔡立杰
 * @date 2018/8/17 15:22
 */
@Slf4j
@Component
public class JwtTokenUtil {

  private static Logger logger = LoggerFactory.getLogger(
      JwtTokenUtil.class);

  /**
   * 从jwt token中获取附加信息.
   *
   * @return 获取map形式的附加信息，如果没有token信息，返回空的map
   */
  public static Map<String, Object> getAdditionInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (CheckEmptyUtil.isEmpty(authentication)) {
      if (logger.isDebugEnabled()) {
        logger.debug("the current authentication is null");
      }
      return Collections.emptyMap();
    }
    if (logger.isDebugEnabled()) {
      logger.debug("the current authentication: {}", authentication);
    }
    Object authDetails = authentication.getDetails();
    Map<String, Object> additionalInfo = Collections.emptyMap();

    if (authDetails instanceof OAuth2AuthenticationDetails) {
      if (logger.isDebugEnabled()) {
        logger.debug("the current authentication is instance of OAuth2AuthenticationDetails");
      }
      OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authDetails;
      if (details.getDecodedDetails() != null) {
        additionalInfo =  (Map<String, Object>) details.getDecodedDetails();
      } else {
        TokenStore tokenStore = SpringBeanUtil.getBean(TokenStore.class);
        OAuth2Authentication auth2Authentication =
            tokenStore.readAuthentication(details.getTokenValue());
        additionalInfo = (Map<String, Object>) auth2Authentication.getDetails();
      }
    } else if (authDetails instanceof HashMap) {
      if (logger.isDebugEnabled()) {
        logger.debug("the current authentication is instance of HashMap");
      }
      additionalInfo = (Map<String, Object>) authDetails;
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the additional info in token: {}", additionalInfo);
    }
    return additionalInfo;
  }

  /**
   * 获取用户ID.
   *
   */
  public static String getUserId() {
    String userId = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_ID.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userId = value.toString();
    }
    return userId;
  }

  /**
   * 获取用户角色.
   *
   * @return
   */
  public static List<String> getUserRole() {
    List<String> userRole = new ArrayList();
    Object value = getAdditionInfo().get(JwtTokenKey.USER_ROLE.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userRole = (List<String>) value;
    }
    return userRole;
  }

  /**
   * 获取用户部门编码.
   *
   * @return
   */
  public static String getUserDeptCode() {
    String userDeptCode = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_DEPT_CODE.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userDeptCode = value.toString();
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the userDeptCode from token: {}", userDeptCode);
    }
    return userDeptCode;
  }

  /**
   * 获取用户部门名称.
   *
   * @return
   */
  public static String getUserDeptName() {
    String userDeptName = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_DEPT_NAME.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userDeptName = value.toString();
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the userDeptName from token: {}", userDeptName);
    }
    return userDeptName;
  }

  /**
   * 获取用户部门编码路径.
   *
   * @return
   */
  public static String getUserDeptCodePath() {
    String userDeptCodePath = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_DEPT_CODE_PATH.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userDeptCodePath = value.toString();
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the userDeptCodePath from token: {}", userDeptCodePath);
    }
    return userDeptCodePath;
  }

  /**
   * 获取用户部门名称路径.
   *
   * @return
   */
  public static String getUserDeptNamePath() {
    String userDeptNamePath = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_DEPT_NAME_PATH.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userDeptNamePath = value.toString();
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the userDeptNamePath from token: {}", userDeptNamePath);
    }
    return userDeptNamePath;
  }

  /**
   * 获取用户Code.
   *
   * @return user表中的id。适用于普通用户。其他登录或没有查询到则返回空
   */
  public static String getUserCode() {
    String userCode = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_CODE.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      userCode = value.toString();
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the user code from token: {}", userCode);
    }
    return userCode;
  }


  /**
   * 从token中获取clientId.
   *
   * @return 客户端id.
   */
  public static String getClientId() {
    String result = null;
    Object value = getAdditionInfo().get(JwtTokenKey.CLIENT_ID.getKey());
    if (CheckEmptyUtil.isNotEmpty(value)) {
      result = (String) value;
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the user client id from token: {}", result);
    }
    return result;
  }

  /**
   * 从token中获取user name.
   *
   * @return 用户登录时的唯一标识.
   */
  public static String getUserName() {
    String userName = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_NAME.getKey());
    if (value != null) {
      userName = (String) value;
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the user name from token: {}", userName);
    }
    return userName;
  }

  /**
   * 从token中获取user source.
   *
   * @return 用户来源（hwork，供应商）.
   */
  public static String getUserSource() {
    String userSource = null;
    Object value = getAdditionInfo().get(JwtTokenKey.USER_SOURCE.getKey());
    if (value != null) {
      userSource = (String) value;
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the user source from token: {}", userSource);
    }
    return userSource;
  }

  /**
   * 从token中获取供应商编码.
   *
   * @return 供应商编码.
   */
  public static String getSupplierCode() {
    String supplierCode = null;
    Object value = getAdditionInfo().get(JwtTokenKey.SUPPLIER_CODE.getKey());
    if (value != null) {
      supplierCode = (String) value;
    }
    if (logger.isTraceEnabled()) {
      logger.trace("the supplier code from token: {}", supplierCode);
    }
    return supplierCode;
  }

}
