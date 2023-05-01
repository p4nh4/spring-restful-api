package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImple implements FileUploadService {

    Path fileLocate;
    FileServiceImple()
    {
        fileLocate = Paths.get("src/main/resources/file");
    }
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename != null) {
            if (filename.contains("..")) {
                System.out.println("Filename is incorrect !! ");
                return null;
            }
            String[] fileParts = filename.split("\\.");
            filename = UUID.randomUUID() +"." + fileParts[1];
            Path resolvedPath = fileLocate.resolve(filename);
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("file name is :"+filename);
            return "file uploaded successfully : " + filename;

        }else return null ;

    }

}
