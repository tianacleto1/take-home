package com.tiagoanacleto.booksapi.resource;

import com.tiagoanacleto.booksapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/files/upload_files")
public class FilesUploadResource {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] files) {
        fileService.processAndSaveFiles(files);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
