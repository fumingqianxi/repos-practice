package com.itheima.Java业务开发常见错误100例.a11空值处理.pojonull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntityA11, Long> {
}
