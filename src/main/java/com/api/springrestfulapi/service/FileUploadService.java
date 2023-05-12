package com.api.springrestfulapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileUploadService {
    //upload
    //delete by name
    //delete all files

    String uploadFile(MultipartFile file) throws IOException;
    String deleteFileByName(String filename);
    String deleteAllFile();

    //load resource
    Resource loadFileAsResource(String filename) throws Exception;

}
