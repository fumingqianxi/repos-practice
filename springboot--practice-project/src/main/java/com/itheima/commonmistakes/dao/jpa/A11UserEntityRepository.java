package com.itheima.commonmistakes.dao.jpa;

import com.itheima.commonmistakes.entity.A11UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface A11UserEntityRepository extends JpaRepository<A11UserEntity, Long> {
}
