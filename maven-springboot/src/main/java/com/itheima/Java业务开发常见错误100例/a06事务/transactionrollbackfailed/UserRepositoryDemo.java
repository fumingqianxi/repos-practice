package com.itheima.Java业务开发常见错误100例.a06事务.transactionrollbackfailed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepositoryDemo extends JpaRepository<UserEntityA06, Long> {

    List<UserEntityA06> findByName(String name);
}
