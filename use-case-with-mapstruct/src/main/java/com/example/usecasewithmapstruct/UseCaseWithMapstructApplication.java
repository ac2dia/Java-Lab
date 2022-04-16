package com.example.usecasewithmapstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UseCaseWithMapstructApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseCaseWithMapstructApplication.class, args);
	}

}
