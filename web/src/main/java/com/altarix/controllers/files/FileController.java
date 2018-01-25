package com.altarix.controllers.files;

import com.altarix.controllers.common.AbstractWebController;
import com.altarix.entities.files.File;
import com.altarix.entities.files.LinkToFileForDownload;
import com.altarix.services.files.FileStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class FileController extends AbstractWebController {
    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public File singleFileUpload(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return fileStorageService.singleFileUpload(file);
    }

    @RequestMapping(value = "/file/{id}/createLinkForDownload/}", method = RequestMethod.POST)
    public LinkToFileForDownload createLinkForDownload(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        return fileStorageService.createLinkForDownload(id);
    }

    @RequestMapping(value = "/file/download/{token}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable("token") String token) throws ExecutionException, InterruptedException, IOException {
        File file = fileStorageService.getFileByToken(token);
        return getResponseEntityWithFile(file);
    }

    @RequestMapping(value = "/file/{id}/info", method = RequestMethod.GET)
    public File getFileInfo(@PathVariable("id") long id) {
        return fileStorageService.getFileInfo(id);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("id") long id) {
        fileStorageService.deleteFile(id);
    }
}
