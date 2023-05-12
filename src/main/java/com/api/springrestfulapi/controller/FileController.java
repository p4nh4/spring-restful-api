package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.response.FileResponse;
import com.api.springrestfulapi.service.FileUploadService;
import com.api.springrestfulapi.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RestController
public class FileController {
    @Autowired
    FileUploadService fileUploadService;
    //format and size validation
    private static final List<String> ALLOWED_EXTENSION = Arrays.asList("jpg","jpeg","png");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;//5MB

    @PostMapping("/upload")
    public Response<FileResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

            FileResponse response = uploadFileRsp(multipartFile);
            return Response.<FileResponse>ok().setPayload(response)
                    .setMessage("upload!");
    }

    @PostMapping("/multiupload")
    public Response<List<FileResponse>> uploadMulti (@RequestParam ("files") MultipartFile[] multipartFiles) throws IOException{


            List<FileResponse> responses = Arrays.stream(multipartFiles)
                    .map(file -> {
                        try {
                            return uploadFileRsp(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
            return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("successfully uploaded!");


//            System.out.println("exception occurred!" + ex.getMessage());
//            return Response.<List<FileResponse>>exception().setMessage("failed to upload!");
//
    }

    @DeleteMapping("/deletefile/{filename}")
    public String deleteFile(@PathVariable String filename)
    {
        String result = fileUploadService.deleteFileByName(filename);
        return result;
    }
    @DeleteMapping("/deleteALLfile")
    public String deleteALLFile()
    {
        return fileUploadService.deleteAllFile();
    }

    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> downloadFile (@PathVariable String filename, HttpServletRequest request) throws Exception {
        Resource resource = fileUploadService.loadFileAsResource(filename);
        // Try to determine file content
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException exception){
//            log.info("Couldn't determine the file type ");
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);

    }


    private FileResponse uploadFileRsp (MultipartFile multipartFile) throws IOException {

        //file empty
        if(multipartFile.isEmpty())
            throw new IllegalArgumentException("empty isn't allowed!!");
        //file size
        if(multipartFile.getSize()> MAX_FILE_SIZE)
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        //file extension
        //old way : using split & regex
        //new way
        String fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        if(!ALLOWED_EXTENSION.contains(fileExtension.toLowerCase())){
            // throw exception for not support exception
            throw new IllegalArgumentException("Invalid file type. Only JPG, JPEG, and PNG files are allowed.");
        }

        String filename = fileUploadService.uploadFile(multipartFile);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(filename)
                .toUriString();
        return new FileResponse().setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(multipartFile.getContentType())
                .setSize(multipartFile.getSize());
    }
}
