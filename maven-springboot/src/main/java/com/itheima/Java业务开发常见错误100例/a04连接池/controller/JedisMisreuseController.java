package com.itheima.Java业务开发常见错误100例.a04连接池.controller;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 跟着仿写了一遍
 */
@RestController
@RequestMapping("jedismisreuse")
@Slf4j
public class JedisMisreuseController {

    private static JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    @PostConstruct
    public void init() {
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)){
            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
            log.info("初始化完成");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jedisPool.close();
        }));
    }

    @GetMapping("wrong")
    public void wrong() throws InterruptedException{
        Jedis jedis = new Jedis("127.0.0.1");
        new Thread(() -> {
            IntStream.rangeClosed(1, 1000).forEach(i -> {
                String result = jedis.get("a");
                if (!result.equals("1")) {
                    log.warn("Except a to be 1 but found {}", result);
                    return;
                }
            });
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("b");
                if (!"2".equals(result)) {
                    log.warn("Expect b to be 2 but found {}", result);
                    return;
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
    }

    @GetMapping("right")
    public void right() throws InterruptedException{
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            IntStream.rangeClosed(1, 1000).forEach(i -> {
                String result = jedis.get("a");
                if (!result.equals("1")) {
                    log.warn("Except a to be 1 but found {}", result);
                    return;
                }
            });
            jedis.close();
        }).start();
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("b");
                if (!"2".equals(result)) {
                    log.warn("Expect b to be 2 but found {}", result);
                    return;
                }
            }
            jedis.close();
        }).start();
        TimeUnit.SECONDS.sleep(5);
    }
}
