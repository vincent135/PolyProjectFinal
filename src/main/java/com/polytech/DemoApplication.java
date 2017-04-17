package com.polytech;

import com.polytech.social.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "DEV");
		SpringApplication.run(DemoApplication.class, args);
	}
}
