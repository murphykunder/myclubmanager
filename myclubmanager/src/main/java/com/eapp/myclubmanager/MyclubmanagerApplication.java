package com.eapp.myclubmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyclubmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyclubmanagerApplication.class, args);
	}

}

