package com.itheima.commonmistakes.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * 用户表.
 *
 * @author 胡磊
 * @since 2023/11/6 22:40
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
