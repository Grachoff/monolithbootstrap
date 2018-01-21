package com.altarix.controllers.files;

import com.altarix.entities.files.File;
import com.altarix.services.files.FileStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
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
    public void downloadFile(@PathVariable("id") long id, HttpServletResponse response) throws ExecutionException, InterruptedException, IOException {
        File file = fileStorageService.getFile(id);
        IOUtils.copy(file.getData(), response.getOutputStream());
        response.setContentType(file.getMimeType());
        response.setContentLengthLong(file.getSize());
        response.setHeader("Content-Disposition", "attachment; filename=\""+ file.getFileName() +"\"");
        response.flushBuffer();
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
