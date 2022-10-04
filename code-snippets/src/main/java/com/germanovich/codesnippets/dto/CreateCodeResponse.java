package com.germanovich.codesnippets.dto;

import lombok.Data;

@Data
public class CreateCodeResponse {
    private String id;

    public CreateCodeResponse(String id) {
        this.id = id;
    }
}
