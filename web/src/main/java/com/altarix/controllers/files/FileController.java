package com.altarix.controllers.files;

import com.altarix.entities.files.File;
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
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public File singleFileUpload(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException {
        return fileStorageService.singleFileUpload(file);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") long id) throws ExecutionException, InterruptedException, IOException {
        File file = fileStorageService.getFile(id);
        InputStreamResource inputStreamResource = new InputStreamResource(file.getData());

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\""+ file.getFileName() +"\"")
                .contentLength(file.getSize())
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .body(inputStreamResource);
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
