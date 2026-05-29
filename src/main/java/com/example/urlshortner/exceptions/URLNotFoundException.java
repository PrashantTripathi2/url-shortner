package com.example.urlshortner.exceptions;

public class URLNotFoundException extends Exception{
    String shortURL;

    public URLNotFoundException(String shortURL){
        this.shortURL = shortURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
}
