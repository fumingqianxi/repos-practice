package com.itheima.commonmistakes.a17自动挡OOM;

import javax.persistence.Table;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "user_entity")
@Data
public class a17UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    public a17UserEntity() {
    }

    public a17UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
