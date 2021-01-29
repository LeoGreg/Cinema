package com.example.cin.repository;

import com.example.cin.model.Hole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoleRep extends CrudRepository<Hole, Long> {

    Hole save(Hole hole);

    Hole getByName(String name);

    Optional<Hole> findByIdAndCinemaId(Long id, Long cinema_id);
}
