package com.itheima.practice.serialization;

import java.io.Serializable;
import lombok.Data;

/**
 * 序列化学生类.
 *
 * @author 胡磊
 * @since 2024/5/4 14:56
 */
@Data
public class SerializationStudent implements Serializable {

  private static final long serialVersionUID = -2843103365228962194L;
  private String name;
  private Integer age;
  private Integer score;
  private Integer studentID;

  @Override
  public String toString() {
    return "SerializationStudent{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", score=" + score +
        '}';
  }
}
