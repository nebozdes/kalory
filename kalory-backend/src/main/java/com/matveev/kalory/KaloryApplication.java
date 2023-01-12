package com.matveev.kalory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.matveev.kalory.domain")
public class KaloryApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaloryApplication.class, args);
	}

}
