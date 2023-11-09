package com.itheima.commonmistakes.a17自动挡OOM;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("a17UserRepository")
public interface UserRepository extends JpaRepository<a17UserEntity, Long> {
}
