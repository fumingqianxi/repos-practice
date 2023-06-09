package com.itheima.log;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;

/**
 * @author 胡磊
 * @since 2023/6/5 13:53
 */
public class LogTest {

  public static void main(String[] args) {
    method01();
    method02();
    method03();
    method04();
  }

  /**
   * Java标准库内置日志包java.util.logging.
   */
  public static void method01() {
    Logger logger = Logger.getGlobal();
    logger.info("start process...");
    logger.warning("memory is running out...");
    logger.fine("ignored.");
    logger.severe("process will be terminated...");
    logger.info("日志分隔==========================");
  }

  /**
   * Java标准库内置日志包java.util.logging.
   * 测试获取logger的另外一种方式.
   */
  public static void method02() {
    // 跟上一种获取logger的方式打印的日志没区别
    Logger logger = Logger.getLogger(LogTest.class.getName());
    logger.info("Start process...");
    try {
      "".getBytes("invalidCharsetName");
    } catch (UnsupportedEncodingException e) {
      logger.severe(e.getMessage());
    }
    logger.info("Process end.");
    logger.info("日志分隔===================");
  }

  /**
   * 使用Apache Commons Logging.
   * 当有log4j的相关依赖和配置文件时，使用的是log4j实现；没有时，使用的是JDK实现
   */
  public static void method03() {
    Log log = LogFactory.getLog(LogTest.class);
    log.info("start...");
    log.warn("end.");
    try {
      int i = 1 / 0;
    } catch (Exception e) {
      // 注意到JDK Logging没有这样的重载方法
      log.error(e.getMessage(), e);
    }
    log.info("日志分隔=======================");
  }

  /**
   * 使用SLF4J和Logback.
   */
  public static void method04() {
    org.slf4j.Logger logger = LoggerFactory.getLogger(LogTest.class);
    logger.info("start...");
    logger.warn("end.");
    try {
      int i = 1 / 0;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
