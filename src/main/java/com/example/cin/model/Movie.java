package com.example.cin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Table(name = "movies")
public class Movie  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String duration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id",referencedColumnName = "id")
    private Metadata metadata;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_cinema_id", nullable = false)
    private Cinema cinema;



    @OneToOne(mappedBy = "movie")
    private Seance seance;


}
