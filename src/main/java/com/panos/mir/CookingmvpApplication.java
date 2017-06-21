package com.panos.mir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CookingmvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookingmvpApplication.class, args);
	}

}
