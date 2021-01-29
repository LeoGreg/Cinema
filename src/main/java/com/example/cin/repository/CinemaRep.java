package com.example.cin.repository;

import com.example.cin.model.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRep extends CrudRepository<Cinema, Long> {
    Cinema save(Cinema cinema);

    Cinema getById(Long id);
}
