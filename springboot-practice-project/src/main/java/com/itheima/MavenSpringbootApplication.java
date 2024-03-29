package com.itheima;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itheima.commonmistakes.a27clientdata.trustclientuserid.LoginRequiredArgumentResolver;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@EnableFeignClients(basePackages = "com.itheima")
public class MavenSpringbootApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/loggerwrong.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/loggerright1.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/loggerright2.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/filterwrong.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/filterright.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/async/performance_sync.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/async/performance_async.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/async/asyncwrong.xml");
    //		System.setProperty("logging.config",
    //		"classpath:com/itheima/commonmistakes/a13logging/duplicate/multiplelevelsfilter.xml");
    SpringApplication.run(MavenSpringbootApplication.class, args);
  }

  @RequestMapping("/hello")
  public String hello() {
    return "Hello Spring";
  }

  @Bean
  public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
    //把类型信息作为属性写入Value
    objectMapper.activateDefaultTyping(
        objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer(RedisSerializer.string());
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  //  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginRequiredArgumentResolver());
  }
}
