package com.example.cin.service.impl;

import com.example.cin.model.Cinema;
import com.example.cin.model.Metadata;
import com.example.cin.model.Movie;


import com.example.cin.model.dto.MovieMetaDto;
import com.example.cin.model.dto.UpMovieDto;
import com.example.cin.model.exception.DuplicateException;
import com.example.cin.repository.CinemaRep;
import com.example.cin.repository.MovieRep;
import com.example.cin.repository.SeanceRep;
import com.example.cin.service.abst.MovieSer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class MovieSerImpl implements MovieSer {

    @Autowired
    private MovieRep movieRep;
    @Autowired
    private CinemaRep cinemaRep;

    @Autowired
    private SeanceRep seanceRep;

    private String root = "C:/Users/User/Desktop/up";

    @Override
    @Transactional
    public String buildName(String extension) {
        Calendar calendar = Calendar.getInstance();
        return root + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                java.util.UUID.randomUUID().toString() + "." + extension;
    }

    @Override
    @Transactional
    public Movie add_movie(MovieMetaDto m_dto, Long cinema_id) throws com.example.cin.model.exception.NotFoundException, DuplicateException, IOException {
        DuplicateException.check(movieRep.getByNameAndDescription(m_dto.getName(), m_dto.getDescription()) != null, "dup.movie");
        String extension = FilenameUtils.getExtension(m_dto.getFile().getOriginalFilename());
        String path_file_name = buildName(extension);
        FileUtils.writeByteArrayToFile(new File(path_file_name), m_dto.getFile().getBytes());

        Metadata metadata = new Metadata();
        metadata.setPath(path_file_name);
        Movie movie = new Movie();
        return cinemaRep.findById(cinema_id).map(cinema -> {
            movie.setCinema(cinema);
            movie.setMetadata(metadata);
            return movieRep.save(movie);
        }).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("movie.not.found"));
    }


    @Override
    @Transactional
    public Movie update_movie(Long movie_id, Long cinema_id, UpMovieDto dto) throws com.example.cin.model.exception.NotFoundException {
        if (!cinemaRep.findById(cinema_id).isPresent()) {
            throw new com.example.cin.model.exception.NotFoundException("not.found.theatre");
        }
        return movieRep.findById(movie_id).map(db_movie -> {
                   db_movie.setDescription(dto.getDescription());
                   db_movie.setName(dto.getName());
                   db_movie.setDuration(dto.getDuration());
            return movieRep.save(db_movie);
        }).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("movie.not.found"));
    }


    @Override
    @Transactional
    public Map<String, Boolean> deleteMovie(Long movieId, Long cinema_id) throws com.example.cin.model.exception.NotFoundException {
        return movieRep.findByIdAndCinemaId(movieId, cinema_id).map(db_movie -> {

            if (db_movie.getSeance() != null) {
                seanceRep.delete(db_movie.getSeance());
            }
            movieRep.delete(db_movie);
            Map<String, Boolean> response = new HashMap<>();
            response.put("DELETED", Boolean.TRUE);
            return response;
        }).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("not.found " + "movie_id : " + movieId + " " + " cinema_id : " + cinema_id));
    }
}

