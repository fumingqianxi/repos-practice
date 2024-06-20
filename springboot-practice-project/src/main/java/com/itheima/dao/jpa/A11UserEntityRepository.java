package com.itheima.dao.jpa;

import com.itheima.entity.A11UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface A11UserEntityRepository extends JpaRepository<A11UserEntity, Long> {
}
