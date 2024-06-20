package com.itheima.common.enums;

/**
 * 定义oauth2的jwt token中的key.
 *
 * @author 蔡立杰
 * @date 08/17/2018 14:50
 */
public enum JwtTokenKey {
  USER_ID("user_id", "登录用户id"),
  USER_CODE("user_code", "登录用户工号"),
  USER_NAME("user_name", "用户名"),
  CLIENT_ID("client_id", "client-id信息"),
  IS_ADMIN("is_amdin", "是否超级管理员"),
  USER_SOURCE("user_source", "用户来源"),
  USER_DEPT_CODE_PATH("user_dept_code_path", "登录人部门编码路径"),
  USER_DEPT_NAME_PATH("user_dept_name_path", "登录人部门名称路径"),
  USER_DEPT_CODE("user_dept_code", "登录人部门编码"),
  USER_DEPT_NAME("user_dept_name", "登录人部门名称"),
  SUPPLIER_CODE("supplier_code", "供应商v码"),
  USER_ROLE("authorities", "用户角色"),
  ;

  private String key;
  private String desc;

  JwtTokenKey(String key, String desc) {
    this.key = key;
    this.desc = desc;
  }

  public String getKey() {
    return key;
  }

  public String getDesc() {
    return desc;
  }
}
