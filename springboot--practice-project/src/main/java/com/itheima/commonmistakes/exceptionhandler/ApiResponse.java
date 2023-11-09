package com.itheima.commonmistakes.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * .
 *
 * @param <T> .
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {
  private boolean success;
  private T data;
  private int code;
  private String message;
}
