package com.altarix.services.files;

import com.altarix.entities.files.File;
import com.altarix.models.file.FileState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FilePersistenceStorageImpl implements FilePersistenceStorage {

    @Value("${app.pathToStorage}")
    String pathToStorage;

    @Override
    @Async("fileTaskExecutor")
    public CompletableFuture<File> saveFile(File file, byte[] filedata) {
        Path path = Paths.get(pathToStorage + String.valueOf(file.getId()));
        try {
            Files.write(path, filedata);
        } catch (IOException e) {
            log.error("File saving error: " + String.valueOf(file.getId()), e);
            file.setFileState(FileState.PROCESSING_ERROR);
            return CompletableFuture.completedFuture(file);
        }
        file.setFileState(FileState.READY);
        return CompletableFuture.completedFuture(file);
    }

    @Override
    public InputStream readFile(String filename) throws IOException {
        return FileUtils.openInputStream(new java.io.File(pathToStorage + filename));
    }

    @Override
    @Async("fileTaskExecutor")
    public void deleteFile(String filename) throws IOException {
        FileUtils.forceDelete(new java.io.File(pathToStorage + filename));
    }
}
