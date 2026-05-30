package com.example.urlshortner;


import com.example.urlshortner.entities.URLEntity;
import com.example.urlshortner.exceptions.URLNotFoundException;
import com.example.urlshortner.repositories.URLTable;
import com.example.urlshortner.services.URLService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

//@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class URLServiceTest {

//    @Mock
//    URLTable urlTable;

//    @InjectMocks
//    URLService urlService;

    @Autowired
    URLService urlService;

    @Test
    public void encodeDecodeServiceTest() throws Exception{
//        URLEntity urlEntity = new URLEntity();
//
//        urlEntity.setUrl("https://google.com");
//        urlEntity.setId(10L);
//
//        when(urlTable.save(any(URLEntity.class)))
//                .thenReturn(urlEntity);
        String originalUrl = "https://google.com";

        String shortURL = urlService.encode(originalUrl);

        String longURL = urlService.decode(shortURL);

        assertEquals(longURL,originalUrl);
    }

    @Test
    public void decodeServiceTest(){

        String shortUrlNotInDB = "abcd";

        assertThrows(URLNotFoundException.class,()->{
            urlService.decode(shortUrlNotInDB);
        });
    }
}
