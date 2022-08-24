package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients(basePackages = "com.itheima")
public class MavenSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenSpringbootApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring";
	}
}
