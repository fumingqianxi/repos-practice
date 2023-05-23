package com.itheima.spring实战.spittr.exception;

/**
 * @author 胡磊
 * @since 2023/5/17 14:40
 */
public class DuplicateSpittleException extends RuntimeException {

  private int code;
  private String msg;

  public DuplicateSpittleException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }
}
