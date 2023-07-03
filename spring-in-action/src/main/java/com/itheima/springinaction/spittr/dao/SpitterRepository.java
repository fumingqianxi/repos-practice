package com.itheima.springinaction.spittr.dao;

import com.itheima.springinaction.spittr.domain.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpitterRepository extends JpaRepository<Spitter, Long> {

  Spitter findByUsername(String username);
}
