package com.altarix.services.files;

import com.altarix.entities.files.File;
import com.altarix.models.file.FileState;
import com.altarix.repositories.files.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    FileRepository fileRepository;

    @Value("app.pathToStorage")
    String pathToStorage;

    @Override
    public File singleFileUpload(MultipartFile file) {
        Object obj = SecurityContextHolder.getContext().getAuthentication();
        File fileEnity = File.builder()
                .fileName(file.getName())
                .fileState(FileState.READY)
                .mimeType(file.getContentType())
                .uploaded(new Date())
                .user(null)
                .build();

        return fileEnity;
    }
}
