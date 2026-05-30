package com.example.urlshortner.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ShortURLRequestDTO {

    @NotBlank(message ="Url must not empty")
    @URL(message = "Invalid url format")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
