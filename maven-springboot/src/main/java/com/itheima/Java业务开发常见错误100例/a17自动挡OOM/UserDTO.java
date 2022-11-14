package com.itheima.Java业务开发常见错误100例.a17自动挡OOM;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class UserDTO {
    private String name;
    @EqualsAndHashCode.Exclude
    private String payload;

    public UserDTO(String name) {
        this.name = name;
        this.payload = IntStream.rangeClosed(1, 10_000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining(""));
    }
}
