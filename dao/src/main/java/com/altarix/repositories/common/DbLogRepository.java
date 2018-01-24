package com.altarix.repositories.common;

import com.altarix.entities.common.DbLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbLogRepository extends JpaRepository<DbLog, Long> {

}
