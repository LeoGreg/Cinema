package com.example.cin.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Data
@Entity
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String path;


    @OneToOne(mappedBy = "metadata")
    private Movie movie;
}
