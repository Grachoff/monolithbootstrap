package com.altarix.services.files;

import com.altarix.dtos.security.JwtUser;
import com.altarix.entities.files.File;
import com.altarix.entities.files.LinkToFileForDownload;
import com.altarix.entities.security.User;
import com.altarix.models.file.FileState;
import com.altarix.repositories.files.FileRepository;
import com.altarix.repositories.files.LinkToFileForDownloadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.altarix.ConstantsHolder.getTimeFormatter;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    FilePersistenceStorage filePersistenseStorage;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    LinkToFileForDownloadRepository linkToFileForDownloadRepository;

    @Override
    public File singleFileUpload(MultipartFile file) throws ExecutionException, InterruptedException {
        CompletableFuture<File> future;
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = User.builder().id(jwtUser.getId()).username(jwtUser.getUsername()).build();

        File fileEnity = File.builder()
                .fileName(file.getOriginalFilename())
                .fileState(FileState.PROCESSING)
                .mimeType(file.getContentType())
                .uploaded(new Date())
                .size(file.getSize())
                .user(user)
                .build();

        saveFileToRepo(fileEnity);
        try {
            future = filePersistenseStorage.saveFile(fileEnity, file.getBytes());
            future.thenAccept(f -> saveFileToRepo(f));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileEnity;
    }

    @Override
    public File getFileInfo(long id) {
        return fileRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public File getFile(long id) throws IOException, ExecutionException, InterruptedException {
        File file = fileRepository.findOne(id);
        if (file.getDeleted() != null || !FileState.READY.equals(file.getFileState())) throw new FileNotFoundException("File not found: " + String.valueOf(id));
        file.setData(filePersistenseStorage.readFile(String.valueOf(file.getId())));
        return file;
    }

    @Override
    public void deleteFile(long id) {
        File file = fileRepository.findOne(id);
        file.setFileState(FileState.MARKED_FOR_DELETE);
        file.setDeleted(new Date());
        fileRepository.save(file);
    }

    @Override
    @Transactional
    public void removeOldFiles() {
        log.info("Removing old files...");
        List<File> files = fileRepository.findAllByDeletedBeforeAndFileState(new Date(), FileState.MARKED_FOR_DELETE);
        if (CollectionUtils.isEmpty(files)) return;

        files.stream().forEach(file -> {
            try {
                filePersistenseStorage.deleteFile(file.getFileName());
            } catch (IOException e) {
                log.warn("Can't delete file: " + file.getFileName());
            }
            file.setFileState(FileState.PERM_DELETED);
        });
        fileRepository.save(files);
    }

    @Override
    @Transactional
    public LinkToFileForDownload createLinkForDownload(Long id) {
        LinkToFileForDownload linkToFileForDownload = LinkToFileForDownload.createLink(id);
        linkToFileForDownloadRepository.save(linkToFileForDownload);
        return linkToFileForDownload;
    }

    @Override
    @Transactional(readOnly = true)
    public File getFileByToken(String token) throws InterruptedException, ExecutionException, IOException {
        LinkToFileForDownload linkToFileForDownload = linkToFileForDownloadRepository.findOne(token);
        if (linkToFileForDownload.getUntilAlive().before(new Date())) throw new FileNotFoundException("File not found!");
        return getFile(linkToFileForDownload.getFile().getId());
    }

    @Override
    @Transactional
    public void removeOldLinks() {
        linkToFileForDownloadRepository.deleteAllByUntilAliveIsBefore(new Date());
    }

    @Transactional
    private void saveFileToRepo(File fileEnity){
        fileRepository.save(fileEnity);
    }
}
