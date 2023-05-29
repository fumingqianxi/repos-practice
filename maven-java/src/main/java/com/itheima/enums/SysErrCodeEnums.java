package com.itheima.enums;

/**
 * 系统异常码枚举类.
 *
 * @author 胡磊
 * @since 2023/5/22 13:22
 */
public enum SysErrCodeEnums {
  NULL_POINTER_EXCEPTION("S-RJ0001", "NullPointerException", "空指针异常"),
  ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION("S-RJ0002", "ArrayIndexOutOfBoundsException", "数组越界异常"),
  ILLEGAL_STATE_EXCEPTION("S-RJ0003", "IllegalStateException", "非法状态异常"),
  CLASS_CAST_EXCEPTION("S-RJ0004", "ClassCastException", "类型转换异常"),
  ARITHMETIC_EXCEPTION("S-RJ0005", "ArithmeticException", "算术运算异常"),
  ILLEGAL_ARGUMENT_EXCEPTION("S-RJ0006", "IllegalArgumentException", "非法参数异常"),
  NUMBER_FORMAT_EXCEPTION("S-RJ0007", "NumberFormatException", "数字格式化异常"),
  DATA_INTEGRITY_VIOLATION_EXCEPTION("S-RS0001", "DataIntegrityViolationException", "数据完整性校验异常"),
  UNEXPECTED_ROLLBACK_EXCEPTION("S-RS0002", "UnexpectedRollbackException", "非预期的回滚异常"),
  HTTP_CLIENT_ERROR_EXCEPTION("S-RS0003", "HttpClientErrorException", "http客户端错误异常"),
  Internal_Authentication_Service_Exception("", "", ""),
  INVOCATION_TARGET_EXCEPTION("S-C00001", "InvocationTargetException", "调用目标异常"),
  INTERRUPTED_EXCEPTION("S-C00002", "InterruptedException", "中断异常"),
  UNKNOWN_HOST_EXCEPTION("S-C00003", "UnknownHostException", "未知主机异常"),
  JsonMappingException("S-C00004", "JsonMappingException", "Json序列化异常");

  private final String errCode;
  private final String name;
  private final String desc;

  SysErrCodeEnums(String errCode, String name, String desc) {
    this.errCode = errCode;
    this.name = name;
    this.desc = desc;
  }

  public String getErrCode() {
    return errCode;
  }

  public String getDesc() {
    return desc;
  }

  public String getName() {
    return name;
  }
}
