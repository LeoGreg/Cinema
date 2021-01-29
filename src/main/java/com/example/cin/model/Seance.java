package com.example.cin.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date offset;

    @OneToOne()
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "cinema_id",referencedColumnName = "id")
    private Cinema cinema;



}
