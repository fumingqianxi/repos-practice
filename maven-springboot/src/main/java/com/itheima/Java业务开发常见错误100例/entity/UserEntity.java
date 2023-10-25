package com.itheima.Java业务开发常见错误100例.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 用户表.
 *
 * @author 胡磊
 * @since 2023/10/25 9:28
 */
@Data
@Entity
@Table(name = "user_entity")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
}
