package com.example.cin.service.impl;

import com.example.cin.model.Cinema;
import com.example.cin.model.Hole;
import com.example.cin.model.exception.DuplicateException;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.repository.CinemaRep;
import com.example.cin.repository.HoleRep;
import com.example.cin.service.abst.HoleSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class HoleSerImpl implements HoleSer {


    @Autowired
    private HoleRep holeRep;

    @Autowired
    private CinemaRep cinemaRep;

    @Override
    @Transactional
    public Hole add_audit(Hole hole, Long cinema_id) throws com.example.cin.model.exception.NotFoundException, DuplicateException {
        DuplicateException.check(holeRep.getByName(hole.getName()) != null, "duplicate.audit");
        return cinemaRep.findById(cinema_id).map(cinema -> {
            hole.setCinema(cinema);
            return holeRep.save(hole);
        }).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("movie.not.found"));
    }


    @Override
    @Transactional
    public Hole update_audit(Long hole_id, Long cinema_id, Hole up_hole) throws com.example.cin.model.exception.NotFoundException, DuplicateException {
        DuplicateException.check(holeRep.getByName(up_hole.getName()) != null, "duplicate.audit");
        if (!cinemaRep.findById(cinema_id).isPresent()) {
            throw new com.example.cin.model.exception.NotFoundException("not.found.cinema");
        }
        return holeRep.findById(hole_id).map(db_hole -> {
            db_hole.setName(up_hole.getName());
            db_hole.setColumns(up_hole.getColumns());
            db_hole.setRows(up_hole.getRows());
            return holeRep.save(db_hole);
        }).orElseThrow(() -> new com.example.cin.model.exception.NotFoundException("hole.not.found"));
    }


    @Override
    @Transactional
    public Map<String, Boolean> deleteAudit(Long hole_id, Long cinemaId) throws NotFoundException {
        return holeRep.findByIdAndCinemaId(hole_id, cinemaId).map(db_hole -> {
            Cinema cinema = cinemaRep.getById(cinemaId);
            cinema.getHoles().remove(db_hole);
            cinemaRep.save(cinema);
            Map<String, Boolean> response = new HashMap<>();
            response.put("DELETED", Boolean.TRUE);
            return response;
        }).orElseThrow(() -> new NotFoundException("not.found " + "movie_id : " + hole_id + " " + " cinema_id : " + cinemaId));
    }
}
