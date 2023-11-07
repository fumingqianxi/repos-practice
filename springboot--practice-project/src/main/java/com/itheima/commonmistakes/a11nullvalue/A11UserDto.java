package com.itheima.commonmistakes.a11nullvalue;

import lombok.Data;

import java.util.Optional;

@Data
public class A11UserDto {
    private Long id;
    private Optional<String> name;
    private Optional<Integer> age;
}
