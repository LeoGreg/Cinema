package com.example.cin.repository;

import com.example.cin.model.Movie;
import com.example.cin.model.Seance;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeanceRep extends CrudRepository<Seance, Long> {

    Optional<Seance> findByIdAndCinemaIdAndMovieId(Long id, Long cine_id, Long movie_id);

    Seance getByMovie(Movie movie);
}
