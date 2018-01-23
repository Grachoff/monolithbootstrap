package com.altarix.services.scheduling;

import com.altarix.repositories.common.LockRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SimpleLock;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

import static com.altarix.ConstantsHolder.LOCK_PERIOD;

@Component
@Slf4j
public class DbLockProvider implements LockProvider {
    private LockRepository lockRepository;

    public DbLockProvider(LockRepository lockRepository) {
        this.lockRepository = lockRepository;
    }

    @Override
    public Optional<SimpleLock> lock(LockConfiguration lockConfiguration) {
        log.info("Locking...");
                return Optional.of(new DbLock(lockConfiguration.getName()));
    }

    private class DbLock implements SimpleLock {
        private String lockName;

        public DbLock(String lockName) {
            this.lockName = lockName;
        }

        @Override
        public void unlock() {
            log.info("Unlocking...");
        }
    }

    @Bean
    public ScheduledLockConfiguration taskScheduler(LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(10)
                .withDefaultLockAtMostFor(Duration.ofMillis(LOCK_PERIOD))
                .build();
    }
}
