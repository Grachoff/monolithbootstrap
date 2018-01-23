package com.altarix.services.scheduling;

import com.altarix.services.files.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.altarix.ConstantsHolder.LOCK_PERIOD;
import static com.altarix.ConstantsHolder.getTimeFormatter;

@Slf4j
@Component
public class FilePersistenceStorageJanitor {

    @Autowired
    FileStorageService fileStorageService;

    @Scheduled(cron = "*/30 * * * * *")
    @SchedulerLock(name = "scheduledTaskName", lockAtLeastFor = LOCK_PERIOD, lockAtMostFor = LOCK_PERIOD)
    public void removeOldFiles() {
        fileStorageService.removeOldFiles();
    }
}
