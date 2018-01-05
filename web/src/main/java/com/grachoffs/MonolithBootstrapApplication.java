package com.grachoffs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MonolithBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithBootstrapApplication.class, args);
	}

	public static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties =  new HashMap<>();
		defProperties.put("spring.profiles.default", "dev");
		app.setDefaultProperties(defProperties);
	}
}
