package com.example.cin.repository;


import com.example.cin.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRep extends CrudRepository<Movie, Long> {
    Movie getById(Long id);

    Movie save(Movie movie);

    Movie getByName(String name);

    Optional<Movie> findByIdAndCinemaId(Long id, Long cinema_id);

    Movie getByNameAndDescription(String name, String des);
}
