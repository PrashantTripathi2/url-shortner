package com.example.urlshortner.services;

import com.example.urlshortner.entities.URLEntity;
import com.example.urlshortner.repositories.URLTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLService {

    @Autowired
    URLTable urlTable;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public String encode(String url){
        URLEntity urlEntity = new URLEntity();

        urlEntity.setUrl(url);

        urlEntity = urlTable.save(urlEntity);
        long id = urlEntity.getId();

        StringBuilder shortUrl = new StringBuilder();

        do{
             int r = (int)id%62;
             id/=62;
             shortUrl.append(CHARACTERS.charAt(r));
        }while(id != 0);
        return shortUrl.reverse().toString();
    }

    public String decode(String shortURL){
        long id = 0;

        for(char c : shortURL.toCharArray()){
            int index = CHARACTERS.indexOf(c);
            if(index == -1){
                return "";
            }
            id = id*62+index;
        }
        Optional<URLEntity> urlEntity = urlTable.findById(id);
        return  urlEntity.isPresent()?urlEntity.get().getUrl()
                :"";
    }
}
