package com.itheima.Java业务开发常见错误100例.a11空值处理.pojonull;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author {胡磊}
 * @since 2022/8/30 22:40
 */
@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;
  private String nickname;
  private Integer age;
  private Date createDate = new Date();
}
