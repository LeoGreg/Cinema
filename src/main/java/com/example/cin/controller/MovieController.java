package com.example.cin.controller;


import com.example.cin.model.Movie;
import com.example.cin.model.dto.MovieMetaDto;
import com.example.cin.model.dto.UpMovieDto;
import com.example.cin.model.exception.DuplicateException;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.service.abst.MovieSer;
import com.example.cin.util.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/admin/movie")
public class MovieController {

    @Autowired
    private MovieSer movieSer;

    @RolesAllowed(Roles.ADMIN_ROLE)
    @PostMapping("/add/cinema_id/{id}")
    public ResponseEntity<Movie> add_movie(@Valid @RequestBody MovieMetaDto dto,
                                           @PathVariable(value = "id") Long cinema_id) throws DuplicateException, IOException, NotFoundException {
        return ResponseEntity.ok(movieSer.add_movie(dto, cinema_id));
    }

    @RolesAllowed(Roles.ADMIN_ROLE)
    @PutMapping("/up/movie_id/{m_id}/cinema_id/{cin_id}")
    public ResponseEntity<Movie> update(@PathVariable(value = "m_id") Long movie_id,
                                        @PathVariable(value = "cin_id") Long cinema_id,
                                        @Valid @RequestBody UpMovieDto movie
    ) throws DuplicateException, NotFoundException, javassist.NotFoundException {
        return ResponseEntity.ok(movieSer.update_movie(movie_id, cinema_id, movie));
    }

    @RolesAllowed(Roles.ADMIN_ROLE)
    @DeleteMapping("/del/movie_id/{m_id}/cinema_id/{cin_id}")
    public ResponseEntity<Map<String, Boolean>> update(@PathVariable(value = "m_id") Long movie_id,
                                                       @PathVariable(value = "cin_id") Long cinema_id) throws NotFoundException {
        return ResponseEntity.ok(movieSer.deleteMovie(movie_id, cinema_id));
    }

}
