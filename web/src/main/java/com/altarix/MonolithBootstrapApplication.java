package com.altarix;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@Slf4j
public class MonolithBootstrapApplication {

	@Value("${spring.datasource.driverClassName}")
	private String nameOfDbDriver;


	public static void main(String[] args) {
		SpringApplication.run(MonolithBootstrapApplication.class, args);
	}

	@Bean
	public Executor fileTaskExecutor() {
		return new ForkJoinPool();
	}

	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		return new JdbcTemplateLockProvider(dataSource, "LOСK4SHEDLOCK");
	}
}
