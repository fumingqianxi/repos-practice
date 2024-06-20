package com.itheima.commonmistakes.a11nullvalue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.itheima.dao.jpa.A11UserEntityRepository;
import com.itheima.dao.jpa.UserRepository;
import com.itheima.entity.A11UserEntity;
import com.itheima.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * POJO空问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RequestMapping("/pojo-null")
@RestController
public class PojoNullController {

  @Autowired private A11UserEntityRepository a11UserEntityRepository;

  @Autowired private UserRepository userRepository;

  /**
   * 测试Optional属性是否生效.
   * http://localhost:45678/pojo-null.
   *
   * @throws JsonProcessingException .
   */
  @GetMapping
  public void test() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());
    A11UserDto result =
        objectMapper.readValue("{\"id\":\"1\", \"age\":30, \"name\":null}", A11UserDto.class);
    log.info("field name with null value dto:{} name:{}", result, result.getName().orElse("N/A"));
    // field name with null value dto:UserDto(id=1, name=Optional.empty, age=Optional[30]) name:N/A
    log.info(
        "missing field name dto:{}",
        objectMapper.readValue("{\"id\":\"1\", \"age\":30}", A11UserDto.class));
    // missing field name dto:UserDto(id=1, name=null, age=Optional[30])
  }

  /**
   * http://localhost:45678/pojo-null/wrong.
   *
   * @param user .
   * @return .
   */
  @PostMapping("wrong")
  public User wrong(@RequestBody User user) {
    user.setNickname(String.format("guest%s", user.getName()));
    return userRepository.save(user);
  }

  /**
   * http://localhost:45678/pojo-null/right.
   *
   * @param user .
   * @return .
   */
  @PostMapping("right")
  public A11UserEntity right(@RequestBody A11UserDto user) {
    if (user == null || user.getId() == null) {
      throw new IllegalArgumentException("用户Id不能为空");
    }

    A11UserEntity userEntity =
        a11UserEntityRepository
            .findById(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

    if (user.getName() != null) {
      userEntity.setName(user.getName().orElse(""));
    }
    userEntity.setNickname("guest" + userEntity.getName());
    if (user.getAge() != null) {
      userEntity.setAge(user.getAge().orElseThrow(() -> new IllegalArgumentException("年龄不能为空")));
    }
    return a11UserEntityRepository.save(userEntity);
  }
}
