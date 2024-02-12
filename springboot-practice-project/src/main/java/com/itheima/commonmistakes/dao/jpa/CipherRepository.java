package com.itheima.commonmistakes.dao.jpa;

import com.itheima.commonmistakes.entity.CipherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CipherRepository extends JpaRepository<CipherData, Long> {
}
