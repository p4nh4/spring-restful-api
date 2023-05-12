package com.api.springrestfulapi.exception;

import com.api.springrestfulapi.util.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.security.SignedObject;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
    {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return new ResponseEntity<>(
                Response.<Object>badRequest().setMessage(errors).setSuccess(false), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleMaxFileExceed(MaxUploadSizeExceededException ex)
    {
        return new ResponseEntity<>(
                Response.<Object>badRequest().setMessage(ex.getMessage()).setSuccess(false), HttpStatus.BAD_REQUEST);

    }
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<String> handleMaxFileSizeExceeded(MaxUploadSizeExceededException ex){
//        long maxFileSize = ex.getMaxUploadSize();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("File size has exceeded the allow limit  "+ maxFileSize);
//
//    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex ){
        return new ResponseEntity<>(Response.<Object>badRequest().setMessage(ex.getMessage()).setSuccess(false),HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleIOException(IOException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file.");
//    }

}
