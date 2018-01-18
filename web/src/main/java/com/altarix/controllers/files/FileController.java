package com.altarix.controllers.files;

import com.altarix.entities.files.File;
import com.altarix.services.files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        File fileEntity = fileStorageService.singleFileUpload(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        redirectAttributes.addAttribute("fileInfo", fileEntity);
        return "redirect:/";
    }
}
