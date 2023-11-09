package com.itheima.commonmistakes.a13日志.async;

import ch.qos.logback.core.ConsoleAppender;

import java.util.concurrent.TimeUnit;

public class MySlowAppender extends ConsoleAppender {
    @Override
    protected void subAppend(Object event) {
        try {
            // 模拟慢日志，不加无法暴露问题，虽然只有1毫秒
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.subAppend(event);
    }
}
