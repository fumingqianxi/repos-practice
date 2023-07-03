package com.itheima.springinaction.springdatajpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.itheima.springinaction.springdatajpa.config.SpringDataJpaConfig;
import com.itheima.springinaction.springdatajpa.dao.SpringDataJpaSpittleRepository;
import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpitter;
import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.Date;
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
public class SpringDataJpaSpittleRepositoryTest {

	@Autowired
	SpringDataJpaSpittleRepository springDataJpaSpittleRepository;

	@Test
	@Transactional
	public void count() {
		System.out.println(springDataJpaSpittleRepository.count());
	}

	@Test
	@Transactional
	public void findRecent() {
		List<SpringDataJpaSpittle> recent = springDataJpaSpittleRepository.findRecent();
		System.out.println(recent);
	}

	@Test
	@Transactional
	public void findOne() {
		SpringDataJpaSpittle spittle = springDataJpaSpittleRepository.findOne(13L);
		System.out.println(spittle);
	}

	@Test
	@Transactional
	public void findBySpitter() {
		SpringDataJpaSpitter spitter = new SpringDataJpaSpitter();
		spitter.setId(14L);
		List<SpringDataJpaSpittle> spittles = springDataJpaSpittleRepository.findBySpitter(spitter);
		List<SpringDataJpaSpittle> spittleList = springDataJpaSpittleRepository.findBySpitterId(14);
		System.out.println(spittles.size());
		System.out.println(spittleList.size());
	}

	@Test
	@Transactional
	@Rollback(false)
	public void save() {
		SpringDataJpaSpitter spitter = new SpringDataJpaSpitter();
		spitter.setId(1L);
		SpringDataJpaSpittle spittle = new SpringDataJpaSpittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
		springDataJpaSpittleRepository.save(spittle);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void delete() {
//		springDataJpaSpittleRepository.delete(17L);
	}
}
