package com.itheima.Java业务开发常见错误100例.a15序列化.redistemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User implements Serializable {
    private String name;
    private int age;
}
