package com.itheima.springinaction.jpa;

import static org.junit.Assert.assertEquals;

import com.itheima.springinaction.jpa.config.JpaConfig;
import com.itheima.springinaction.jpa.dao.JpaSpitterRepository;
import com.itheima.springinaction.jpa.dao.JpaSpittleRepository;
import com.itheima.springinaction.jpa.domain.JpaSpitter;
import com.itheima.springinaction.jpa.domain.JpaSpittle;
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
@ContextConfiguration(classes= JpaConfig.class)
public class JpaSpittleRepositoryTest {

	@Autowired
	JpaSpittleRepository jpaSpittleRepository;

	@Autowired
	JpaSpitterRepository jpaSpitterRepository;

	@Test
	@Transactional
	public void count() {
		System.out.println(jpaSpittleRepository.count());
	}

	@Test
	@Transactional
	public void findRecent() {
		// default case
		{
			List<JpaSpittle> recent = jpaSpittleRepository.findRecent();
			System.out.println(recent.size());
			assertEquals(recent.size(), 10);
		}

		// specific count case
		{
			List<JpaSpittle> recent = jpaSpittleRepository.findRecent(5);
			System.out.println(recent.size());
			assertEquals(recent.size(), 5);
		}
	}

	@Test
	@Transactional
	public void findOne() {
		System.out.println(jpaSpittleRepository.findOne(2));
	}

	@Test
	@Transactional
	public void findBySpitter() {
		List<JpaSpittle> spittles = jpaSpittleRepository.findBySpitterId(5);
		System.out.println(spittles);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void save() {
		JpaSpitter jpaSpitter = jpaSpitterRepository.findOne(14);
		JpaSpittle jpaSpittle = new JpaSpittle(null, jpaSpitter, "123asdasdfasdfasd", new Date());
		jpaSpittleRepository.save(jpaSpittle);
	}

	@Test
	@Transactional
	public void delete() {

	}
}
