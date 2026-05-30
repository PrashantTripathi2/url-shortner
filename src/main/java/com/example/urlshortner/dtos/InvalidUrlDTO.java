package com.example.urlshortner.dtos;

public class InvalidUrlDTO {
    private String error;

    public InvalidUrlDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
