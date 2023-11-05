package com.itheima.Java业务开发常见错误100例.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表.
 *
 * @author 胡磊
 * @since 2023/10/25 9:28
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public UserEntity(String name) {
    this.name = name;
  }
}
