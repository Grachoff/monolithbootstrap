package com.altarix.repositories.files;

import com.altarix.entities.files.LinkToFileForDownload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LinkToFileForDownloadRepository extends JpaRepository<LinkToFileForDownload, String> {
    void deleteAllByUntilAliveIsBefore(Date date);
}
