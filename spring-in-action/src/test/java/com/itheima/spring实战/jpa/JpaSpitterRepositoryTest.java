package com.itheima.spring实战.jpa;

import com.itheima.spring实战.jpa.config.JpaConfig;
import com.itheima.spring实战.jpa.dao.JpaSpitterRepository;
import com.itheima.spring实战.jpa.domain.JpaSpitter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class JpaSpitterRepositoryTest {

	@Autowired
	JpaSpitterRepository jpaSpitterRepository;
	
	@Test
	public void count() {
		System.out.println(jpaSpitterRepository.count());
	}
	
	@Test
	public void findAll() {
		System.out.println(jpaSpitterRepository.findAll());
	}
	
	@Test
	@Transactional
	public void findByUsername() {
	}
	
	@Test
	@Transactional
	public void findOne() {
		System.out.println(jpaSpitterRepository.findOne(1));
	}
	
	@Test
	@Transactional
	// 不加该注释默认回滚
	@Rollback(false)
	public void save_newSpitter() {
		JpaSpitter spitter = new JpaSpitter(null, "newbee", "letmein", "New Bee", "newbee@habuma.com", true);
		jpaSpitterRepository.save(spitter);
		System.out.println(spitter);
	}

	@Test
	@Transactional
	@Ignore
	public void save_existingSpitter() {

	}
}
