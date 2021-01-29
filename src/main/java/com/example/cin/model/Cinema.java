package com.example.cin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "cinemas")
public class Cinema  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Hole> holes;



    @OneToMany( mappedBy = "cinema",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Movie> movies;




    @OneToOne(mappedBy = "cinema")
    private Seance seance;




}
