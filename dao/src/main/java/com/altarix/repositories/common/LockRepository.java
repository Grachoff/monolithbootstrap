package com.altarix.repositories.common;

import com.altarix.entities.common.LockForShadlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockRepository extends JpaRepository<LockForShadlock, String> {
}
