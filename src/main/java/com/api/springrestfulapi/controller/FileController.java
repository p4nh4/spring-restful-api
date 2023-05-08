package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.response.FileUploadResponse;
import com.api.springrestfulapi.service.FileUploadService;
import com.api.springrestfulapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileController {
    @Autowired
    FileUploadService fileUploadService;


    @PostMapping("/upload/")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
        return "file name "+"'"+file.getOriginalFilename()+"'"+" is successfully uploaded...!";
    }
//    @PostMapping("/uploadFile")
//    public ResponseEntity<FileUploadResponse> uploadFile(
//            @RequestParam("file") MultipartFile multipartFile)
//            throws IOException {
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        long size = multipartFile.getSize();
//
//        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
//
//        FileUploadResponse response = new FileUploadResponse();
//        response.setFileName(fileName);
//        response.setSize(size);
//        response.setDownloadUri("/downloadFile/" + filecode);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
