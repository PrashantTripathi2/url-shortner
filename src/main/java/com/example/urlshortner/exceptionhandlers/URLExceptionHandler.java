package com.example.urlshortner.exceptionhandlers;

import com.example.urlshortner.dtos.InvalidUrlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.urlshortner.exceptions.URLNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class URLExceptionHandler {
    private  static final Logger logger = LoggerFactory.getLogger(URLExceptionHandler.class);

    @ExceptionHandler(URLNotFoundException.class)
    public ResponseEntity<Void> handleURLNotFound(URLNotFoundException ex){
        logger.info("URL Not found for short url: {}",ex.getShortURL());

        return ResponseEntity
                .status(302)
                .header("location","/error.html")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidUrlDTO> invalidUrlHandler(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        ex.getBindingResult()
                        .getFieldErrors()
                                .stream()
                                        .forEach((error)->{
                                            logger.info(
                                                    "Validation Error for feild: {} and Defult Message: {} ",error.getField(),error.getDefaultMessage()
                                            );
                                            errors.add("Field:"+error.getField()+" message:"+error.getDefaultMessage());
                                        });

        InvalidUrlDTO invalidUrlDTO = new InvalidUrlDTO(errors.toString());

        return ResponseEntity
                .badRequest()
                .body(invalidUrlDTO);
    }


}
