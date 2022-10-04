package com.germanovich.codesnippets.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CodeDto {
    private String code;
    private String date;

    private int time;
    private int views;
    @JsonIgnore
    private boolean viewsRestrictionApplied;

    public CodeDto() {
    }

    public CodeDto(String code, String date) {
        this.code = code;
        this.date = date;
    }
}
