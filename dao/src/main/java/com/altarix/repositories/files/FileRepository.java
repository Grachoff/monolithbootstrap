package com.altarix.repositories.files;

import com.altarix.entities.files.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}