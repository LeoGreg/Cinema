package com.example.cin.service.impl;

import com.example.cin.model.Cinema;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.repository.CinemaRep;
import com.example.cin.service.abst.CinemaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class CinemaSerImpl implements CinemaSer {

    @Autowired
    private CinemaRep cinemaRep;

    @Override
    @Transactional
    public Cinema add_cinema(Cinema cinema) {
        return cinemaRep.save(cinema);
    }


    @Override
    @Transactional
    public Cinema update_cinema(Cinema up_cinema, Long cinema_id) throws com.example.cin.model.exception.NotFoundException {
        Cinema db_cinema = cinemaRep.findById(cinema_id).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("not found::" + cinema_id));
        db_cinema.setName(up_cinema.getName());
        return cinemaRep.save(db_cinema);
    }

    @Override
    @Transactional
    public Map<String, Boolean> delete_cinema(Long cinema_id) throws NotFoundException {
        Cinema db_cinema = cinemaRep.findById(cinema_id).orElseThrow(() -> new NotFoundException("theatre.not.found:: " + cinema_id));
        cinemaRep.delete(db_cinema);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
