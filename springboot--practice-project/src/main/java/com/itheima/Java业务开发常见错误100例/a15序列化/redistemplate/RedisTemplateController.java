package com.itheima.Java业务开发常见错误100例.a15序列化.redistemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡磊
 * @since 2022/11/11 15:19
 */
@RestController
@RequestMapping("redistemplate")
@Slf4j
public class RedisTemplateController {

  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private RedisTemplate<String, User> userRedisTemplate;
  @Autowired
  private RedisTemplate<String, Long> countRedisTemplate;

  @PostConstruct
  public void init() throws JsonProcessingException {
    redisTemplate.opsForValue().set("redisTemplate", new User("zhuye", 36));
    stringRedisTemplate.opsForValue().set("stringRedisTemplate", objectMapper.writeValueAsString(new User("zhuye", 36)));
  }

  @GetMapping("wrong")
  public void wrong() {
    log.info("redisTemplate get {}", redisTemplate.opsForValue().get("stringRedisTemplate"));
    log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get("redisTemplate"));
  }

  @GetMapping("right")
  public void right() throws JsonProcessingException {
    User userFromRedisTemplate = (User) redisTemplate.opsForValue().get("redisTemplate");
    log.info("redisTemplate get {}", userFromRedisTemplate);
    User userFromStringRedisTemplate = objectMapper.readValue(stringRedisTemplate.opsForValue().get("stringRedisTemplate"), User.class);
    log.info("stringRedisTemplate get {}", userFromStringRedisTemplate);
  }

  @GetMapping("right2")
  public void right2() {
    User user = new User("zhuye", 36);
    userRedisTemplate.opsForValue().set(user.getName(), user);
    Object userFromRedis = userRedisTemplate.opsForValue().get(user.getName());
    log.info("userRedisTemplate get {} {}", userFromRedis, userFromRedis.getClass());
    log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get(user.getName()));
  }
}
