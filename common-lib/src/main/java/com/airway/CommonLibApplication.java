package com.airway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.airway")
public class CommonLibApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonLibApplication.class, args);
	}

}
