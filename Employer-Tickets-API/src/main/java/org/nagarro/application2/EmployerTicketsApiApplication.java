package org.nagarro.application2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployerTicketsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployerTicketsApiApplication.class, args);
	}

}
