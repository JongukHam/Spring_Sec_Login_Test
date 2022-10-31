package com.example.Spring_Sec_Login_Test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecLoginTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecLoginTestApplication.class, args);
	}

}
