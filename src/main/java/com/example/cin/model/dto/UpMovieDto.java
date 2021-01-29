package com.example.cin.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpMovieDto {
    private String name;


    private String description;


    private String duration;


}
