package com.example.urlshortner.controllers;

import com.example.urlshortner.dtos.ShortURLRequestDTO;
import com.example.urlshortner.dtos.ShortURLResponseDTO;
import com.example.urlshortner.services.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class URLController {

    @Autowired
    URLService urlService;

    @PostMapping("/short-url")
    public ShortURLResponseDTO shortURL(@RequestBody ShortURLRequestDTO urlRequest){
        ShortURLResponseDTO res = new ShortURLResponseDTO();

        String url = urlService.encode(urlRequest.getUrl());

        res.setShortURL(url);

        return res;

    }

    @GetMapping("/r/{shortURL}")
    public ResponseEntity<Void> redirect(@PathVariable String shortURL){
        String originalURL = urlService.decode(shortURL);
        if(!originalURL.equals("")){
            if(!originalURL.startsWith("http://") && !originalURL.startsWith("https://")){

                originalURL = "https://" + originalURL;

            }
            return ResponseEntity
                    .status(302)
                    .location(URI.create(originalURL))
                    .build();
        }
        return ResponseEntity
                .badRequest()
                .build();

    }
}
