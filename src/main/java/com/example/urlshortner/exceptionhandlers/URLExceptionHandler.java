package com.example.urlshortner.exceptionhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.urlshortner.exceptions.URLNotFoundException;

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


}
