package com.example.urlshortner.services;

import com.example.urlshortner.entities.URLEntity;
import com.example.urlshortner.exceptions.URLNotFoundException;
import com.example.urlshortner.repositories.URLTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class URLService {

    public static final Logger logger = LoggerFactory.getLogger(URLService.class);
    @Autowired
    URLTable urlTable;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public String encode(String url){
        logger.info("Start encoding logn url: {}",url);
        URLEntity urlEntity = new URLEntity();

        urlEntity.setUrl(url);

        urlEntity = urlTable.save(urlEntity);
        logger.info("Saved the long url in db url: {}",url);
        long id = urlEntity.getId();

        StringBuilder shortUrl = new StringBuilder();

        do{
             int r = (int)id%62;
             id/=62;
             shortUrl.append(CHARACTERS.charAt(r));
        }while(id != 0);
        String shortURL = shortUrl.reverse().toString();
        logger.info("Short url genrated url: {}",shortURL);
        return shortURL;
    }

    public String decode(String shortURL) throws URLNotFoundException{
        long id = 0;
        String longurl;
        logger.info("Start decoidng the short url: {}",shortURL);
        for(char c : shortURL.toCharArray()){
            int index = CHARACTERS.indexOf(c);
            if(index == -1){
                return "";
            }
            id = id*62+index;
        }

        logger.info("DB id genreated from short url id: {}",id);
        Optional<URLEntity> urlEntity = urlTable.findById(id);

         if(!urlEntity.isPresent()){
             logger.error("URL Not Found for short url: {}",shortURL);
             throw new URLNotFoundException(shortURL);
         }

        longurl = urlEntity.get().getUrl();
        logger.info("Long url found in DB url: {}",longurl);

        return  longurl;
    }
}
