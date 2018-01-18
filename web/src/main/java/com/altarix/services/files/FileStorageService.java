package com.altarix.services.files;

import com.altarix.entities.files.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    File singleFileUpload(MultipartFile file);
}
