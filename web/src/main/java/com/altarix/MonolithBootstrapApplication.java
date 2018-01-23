package com.altarix;

import com.altarix.repositories.common.LockRepository;
import com.altarix.services.scheduling.DbLockProvider;
import net.javacrumbs.shedlock.core.LockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MonolithBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithBootstrapApplication.class, args);
	}

	@Bean
	public Executor fileTaskExecutor() {
		return new ForkJoinPool();
	}

	@Bean
	@Autowired
	public LockProvider lockProvider(LockRepository lockRepository) {
		return new DbLockProvider(lockRepository);
	}
}
