package com.itheima.commonmistakes.dao.jpa;

import com.itheima.commonmistakes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
