package com.example.cin.service.impl;

import com.example.cin.model.Cinema;
import com.example.cin.model.Movie;
import com.example.cin.model.Seance;
import com.example.cin.model.dto.MDto;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.repository.CinemaRep;
import com.example.cin.repository.MovieRep;
import com.example.cin.repository.SeanceRep;
import com.example.cin.service.abst.SeanceSer;
import com.sun.mail.imap.protocol.BODY;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class SeanceSerImpl implements SeanceSer {


    @Autowired
    private SeanceRep seanceRep;

    @Autowired
    private MovieRep movieRep;
    @Autowired
    private CinemaRep cinemaRep;


    @Override
    @Transactional
    public Seance addSeance(Seance seance, Long movie_id, Long cinema_id) throws NotFoundException {
        Cinema db_cinema = cinemaRep.findById(cinema_id).orElseThrow(() -> new NotFoundException("not.found.cinema"));
        Movie db_movie = movieRep.findById(movie_id).orElseThrow(() -> new NotFoundException("not.found.movie"));
        seance.setCinema(db_cinema);
        seance.setMovie(db_movie);
        Long time = seance.getOffset().getTime() + (Long.parseLong(db_movie.getDuration()) * 60000) + 15 * 60000;
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date(time));
        seance.setOffset(new Date(date));
        return seanceRep.save(seance);
    }

    @Override
    @Transactional
    public Map<String, Boolean> del(Long seance_id, Long cinema_id, Long movie_id) throws NotFoundException {
        return seanceRep.findByIdAndCinemaIdAndMovieId(seance_id, cinema_id, movie_id).map(db_seance -> {
            seanceRep.delete(db_seance);
            Map<String, Boolean> response = new HashMap<>();
            response.put("DELETED", Boolean.TRUE);
            return response;
        }).orElseThrow(() -> new NotFoundException("not.found " + "seance_id " + seance_id + " " + "cinema_id " + cinema_id + "  movie_id " + movie_id));
    }


    @Override
    @Transactional
    public String  getMovie(MDto mDto) {
        Movie movie = movieRep.getByName(mDto.getName());
        Seance seance = seanceRep.getByMovie(movie);

        String info = "movie name : " + seance.getMovie().getName() + "\n" +
                "movie description : " + seance.getMovie().getDescription() + "\n" +
                "duration : " + seance.getMovie().getDuration()+"\n"+
                "cinema : "+seance.getCinema().getName()+"\n"+
                "starting time : "+seance.getOffset();
        return info;
    }
}
