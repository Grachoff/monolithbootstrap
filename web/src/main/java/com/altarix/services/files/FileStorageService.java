package com.altarix.services.files;

import com.altarix.entities.files.File;
import com.altarix.entities.files.LinkToFileForDownload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface FileStorageService {
    File singleFileUpload(MultipartFile file) throws ExecutionException, InterruptedException;

    File getFileInfo(long id);

    File getFile(long id) throws ExecutionException, InterruptedException, IOException;

    void deleteFile(long id);

    void removeOldFiles();

    LinkToFileForDownload createLinkForDownload(Long id);

    File getFileByToken(String token) throws InterruptedException, ExecutionException, IOException;

    void removeOldLinks();
}
