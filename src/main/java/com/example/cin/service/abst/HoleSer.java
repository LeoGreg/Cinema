package com.example.cin.service.abst;

import com.example.cin.model.Hole;
import com.example.cin.model.exception.DuplicateException;
import com.example.cin.model.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.Map;

public interface HoleSer {


    @Transactional
    Hole add_audit(Hole hole, Long cinema_id) throws NotFoundException, DuplicateException;

    @Transactional
    Hole update_audit(Long hole_id, Long cinema_id, Hole up_hole) throws NotFoundException, DuplicateException;

    @Transactional
    Map<String, Boolean> deleteAudit(Long hole_id, Long cinemaId) throws NotFoundException;
}
