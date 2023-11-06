package com.itheima.commonmistakes.dao.jpa;

import com.itheima.commonmistakes.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户表JPA接口.
 *
 * @author 胡磊
 * @since 2023/10/25 9:46
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findByName(String name);
}
