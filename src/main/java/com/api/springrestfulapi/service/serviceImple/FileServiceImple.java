package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.service.FileUploadService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImple implements FileUploadService {

    Path fileLocate;
    private final String getServerSource ="src/main/resources/file/";
    FileServiceImple()
    {
        fileLocate = Paths.get(getServerSource);
        try {
            if (!Files.exists(fileLocate)) {
                Files.createDirectories(fileLocate);
                System.out.println("Server Path Created ! " + fileLocate);
            } else {
                System.out.println("Server path is already exist  ! ");
            }
        } catch (Exception ex) {
            System.out.println("Error Creating the Server path : " + ex.getMessage());
        }

    }



    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        //format filename
        //1.get og filename
        String filename = file.getOriginalFilename();
        if (filename != null) {
            if (filename.contains("..")) {
                System.out.println("Filename is incorrect !! ");
                return "Filename is incorrect !! ";
            }
            //2.spilt filename to 2 parts
            //(1) one is og filename (index0)
            //(2) one is extension format (index1)
            String[] fileParts = filename.split("\\.");
            //3.get mf back tgt by change og filename to UUID and keep the same extension format
            filename = UUID.randomUUID() +"." + fileParts[1];
            //4. make a path that have a location(make way back to fileLocate) so we can upload the god damn file
            Path resolvedPath = fileLocate.resolve(filename);
            //*getInputStream (idk the heck is that but it required?!?!)
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("file name is :"+filename);
            return  filename;
        }else return "no file!" ;

    }

    @Override
    public String deleteFileByName(String filename) {

        try {
            Path imagePath = Paths.get(getServerSource);
            List<File> files = List.of(Objects.requireNonNull(imagePath.toFile().listFiles()));

            System.out.println("Checking the filename : ");
            //filter file that will be delete using filter method
            File deleteFile = files.stream().filter(e -> e.getName().equals(filename)).findFirst()
                    .orElse(null);
            assert deleteFile != null;
            //delete method
            Files.delete(deleteFile.toPath());
            return filename+ " has been successfully removed !";
        } catch (Exception ex) {
            System.out.println("Error delete the image by name : " + ex.getMessage());
            return filename + " doesn't exist  ! ";
        }

    }

    @Override
    public String deleteAllFile() {

        Path imagePath = Paths.get(getServerSource);
        List<File> files = List.of(Objects.requireNonNull(imagePath.toFile().listFiles()));
        try {
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        Files.delete(file.toPath());
                    }
                }
                return "deleted all files successfully! ";
            }
            return  " no file here?!?!?!";
        } catch (Exception ex) {
            System.out.println("can't delete cuz?!?! : " + ex.getMessage());
            return  " exception occurred ! ";
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws Exception {
        Path resourcePath = this.fileLocate.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(resourcePath.toUri());
            if(resource.exists())
            {
                return  resource;
            }else {
                throw new Exception("not existed!!!");
            }
        }catch (Exception e)
        {
            throw  new Exception("sth occurred!");
        }
    }

}
