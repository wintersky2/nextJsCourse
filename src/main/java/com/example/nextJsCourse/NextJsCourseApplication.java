package com.example.nextJsCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NextJsCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextJsCourseApplication.class, args);
	}

}
