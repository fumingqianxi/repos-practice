package com.itheima.Java业务开发常见错误100例.a15序列化.jsonignoreproperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRight {
    private String name;
}
