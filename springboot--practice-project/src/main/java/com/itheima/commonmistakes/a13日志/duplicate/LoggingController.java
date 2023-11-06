package com.itheima.commonmistakes.a13日志.duplicate;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("logging")
@RestController
public class LoggingController {

    /**
     * lombok注解方式使用SLF4J接口.
     */
    @GetMapping("log")
    public void log() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

    /**
     * 原始方式使用SLF4J接口.
     */
    @GetMapping("log2")
    public void log2() {
        Logger logger = LoggerFactory.getLogger(LoggingController.class);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
