package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.service.FileUploadService;
import com.api.springrestfulapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileController {
    @Autowired
    FileUploadService fileUploadService;


    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
        return "file name "+"'"+file.getOriginalFilename()+"'"+" is successfully uploaded...!";

    }
}
