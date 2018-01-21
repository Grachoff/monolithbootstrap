package com.altarix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@EnableAsync
public class MonolithBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithBootstrapApplication.class, args);
	}

	@Bean
	public Executor fileTaskExecutor() {
		return new ForkJoinPool();
	}
}
