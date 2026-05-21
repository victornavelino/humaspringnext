package com.humanidades.phumanidades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.humanidades")
@EntityScan(basePackages = "com.humanidades.model")
@EnableJpaRepositories(basePackages = "com.humanidades.repository")
public class PhumanidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhumanidadesApplication.class, args);
	}

}
