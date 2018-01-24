package com.altarix.repositories.files;

import com.altarix.entities.files.File;
import com.altarix.models.file.FileState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByDeletedBeforeAndFileState(Date date, FileState fileState);
}