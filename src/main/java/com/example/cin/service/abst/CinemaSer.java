package com.example.cin.service.abst;

import com.example.cin.model.Cinema;
import com.example.cin.model.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.Map;

public interface CinemaSer {
    @Transactional
    Cinema add_cinema(Cinema cinema);

    @Transactional
    Cinema update_cinema(Cinema up_cinema, Long cinema_id) throws com.example.cin.model.exception.NotFoundException;

    @Transactional
    Map<String, Boolean> delete_cinema(Long cinema_id) throws NotFoundException;
}
