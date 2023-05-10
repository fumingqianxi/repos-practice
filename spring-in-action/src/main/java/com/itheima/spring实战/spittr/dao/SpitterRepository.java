package com.itheima.spring实战.spittr.dao;

import com.itheima.spring实战.spittr.domain.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpitterRepository extends JpaRepository<Spitter, Long> {

  Spitter findByUsername(String username);
}
