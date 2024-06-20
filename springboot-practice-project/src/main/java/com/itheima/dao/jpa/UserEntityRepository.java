package com.itheima.dao.jpa;

import com.itheima.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户表JPA接口.
 *
 * @author 胡磊
 * @since 2023/10/25 9:46
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  List<UserEntity> findByName(String name);

  @Query(nativeQuery = true, value = "SELECT SUM(score) FROM `user_entity`")
  Long wrong1();

  @Query(nativeQuery = true, value = "SELECT COUNT(score) FROM user_entity")
  Long wrong2();

  @Query(nativeQuery = true, value = "SELECT * FROM `user_entity` WHERE score=null")
  List<UserEntity> wrong3();

  @Query(nativeQuery = true, value = "SELECT IFNULL(SUM(score),0) FROM `user_entity`")
  Long right1();

  @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM `user_entity`")
  Long right2();

  @Query(nativeQuery = true, value = "SELECT * FROM `user_entity` WHERE score IS NULL")
  List<UserEntity> right3();
}
