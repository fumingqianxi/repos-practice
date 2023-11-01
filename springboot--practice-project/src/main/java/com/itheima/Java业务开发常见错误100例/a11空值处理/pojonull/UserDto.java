package com.itheima.Java业务开发常见错误100例.a11空值处理.pojonull;

import lombok.Data;

import java.util.Optional;

@Data
public class UserDto {
    private Long id;
    private Optional<String> name;
    private Optional<Integer> age;
}
