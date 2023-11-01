package com.itheima.Java业务开发常见错误100例.dao.jpa;

import com.itheima.Java业务开发常见错误100例.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户表JPA接口.
 *
 * @author 胡磊
 * @since 2023/10/25 9:46
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
