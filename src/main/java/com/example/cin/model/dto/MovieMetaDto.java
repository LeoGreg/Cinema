package com.example.cin.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieMetaDto {

    private String name;

    private String description;

    private MultipartFile file;

}
