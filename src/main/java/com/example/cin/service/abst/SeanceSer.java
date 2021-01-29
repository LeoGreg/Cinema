package com.example.cin.service.abst;

import com.example.cin.model.Seance;
import com.example.cin.model.dto.MDto;
import com.example.cin.model.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.Map;

public interface SeanceSer {
    @Transactional
    Seance addSeance(Seance seance, Long movie_id, Long cinema_id) throws NotFoundException;

    @Transactional
    Map<String, Boolean> del(Long seance_id, Long cinema_id, Long movie_id) throws NotFoundException;

    @Transactional
    String  getMovie(MDto mDto);
}
