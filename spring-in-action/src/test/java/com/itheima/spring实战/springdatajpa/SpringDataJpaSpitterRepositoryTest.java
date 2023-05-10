package com.itheima.spring实战.springdatajpa;

import com.itheima.spring实战.springdatajpa.config.SpringDataJpaConfig;
import com.itheima.spring实战.springdatajpa.dao.SpringDataJpaSpitterRepository;
import com.itheima.spring实战.springdatajpa.domain.SpringDataJpaSpitter;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringDataJpaConfig.class)
public class SpringDataJpaSpitterRepositoryTest {

	@Autowired
	SpringDataJpaSpitterRepository springDataJpaSpitterRepository;
	
	@Test
	@Transactional
	public void count() {
		System.out.println(springDataJpaSpitterRepository.count());
	}
	
	@Test
	@Transactional
	public void findAll() {
		List<SpringDataJpaSpitter> spitters = springDataJpaSpitterRepository.findAll();
		System.out.println(spitters);
	}
	
	@Test
	@Transactional
	public void findByUsername() {
		System.out.println(springDataJpaSpitterRepository.findByUsername("testUpdate"));
	}

	@Test
	@Transactional
	public void testFindByUsernameOrFullNameLike() {
		System.out.println(springDataJpaSpitterRepository.findByUsernameOrFullNameLike("testNoDataSource", "te"));
	}

	@Test
	@Transactional
	public void testFindAllGmailSpitters() {
		System.out.println(springDataJpaSpitterRepository.findAllGmailSpitters());
	}
	
	@Test
	@Transactional
	public void findOne() {
		System.out.println(springDataJpaSpitterRepository.findOne(1L));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void save_newSpitter() {
		SpringDataJpaSpitter spitter = new SpringDataJpaSpitter(null, "newbee", "letmein", "New Bee", "newbee@habuma.com", true);
		springDataJpaSpitterRepository.save(spitter);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void save_existingSpitter() {
		SpringDataJpaSpitter spitter = new SpringDataJpaSpitter(6L, "testNoDataSource", "letmein", "Arthur Names", "arthur@habuma.com", false);
		springDataJpaSpitterRepository.save(spitter);
	}
}
