package com.itheima.Java业务开发常见错误100例.a06事务.transactionrollbackfailed;

import javax.persistence.GenerationType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserEntityA06 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public UserEntityA06() {
    }

    public UserEntityA06(String name) {
        this.name = name;
    }
}
