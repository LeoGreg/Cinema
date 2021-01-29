package com.example.cin.service.abst;


import com.example.cin.model.Movie;
import com.example.cin.model.dto.MovieMetaDto;
import com.example.cin.model.exception.DuplicateException;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

public interface MovieSer {
    @Transactional
    String buildName(String extension);

    @Transactional
    Movie add_movie(MovieMetaDto m_dto, Long cinema_id) throws com.example.cin.model.exception.NotFoundException, DuplicateException, IOException;

    @Transactional
    Movie update_movie(Long movie_id, Long cinema_id, Movie up_movie) throws com.example.cin.model.exception.NotFoundException;

    @Transactional
    Map<String, Boolean> deleteMovie(Long movieId, Long cinema_id) throws  com.example.cin.model.exception.NotFoundException;
}

