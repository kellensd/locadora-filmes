package com.project.locadora.dto;

public class ResponseErrorDTO {

    private String message;

    public ResponseErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
