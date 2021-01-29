package com.example.cin.controller;


import com.example.cin.model.Seance;
import com.example.cin.model.dto.MDto;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.service.abst.SeanceSer;
import com.example.cin.util.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin/seance")
public class SeanceController {

    @Autowired
    private SeanceSer seanceSer;


    @RolesAllowed(Roles.ADMIN_ROLE)
    @PostMapping("/add/movie_id/{m_id}/cinema_id/{c_id}")
    public ResponseEntity<Seance> addSeance(@Valid @RequestBody Seance seance,
                                            @PathVariable(value = "m_id") Long movie_id,
                                            @PathVariable(value = "c_id") Long cinema_id) throws NotFoundException {
        return ResponseEntity.ok(seanceSer.addSeance(seance, movie_id, cinema_id));

    }

    @RolesAllowed(Roles.ADMIN_ROLE)
    @DeleteMapping("/del/seance/{s_id}/cinema/{c_id}/movie/{m_id}")
    public ResponseEntity<Map<String, Boolean>> del(@PathVariable(value = "s_id") Long seance_id,
                                                    @PathVariable(value = "c_id") Long cinema_id,
                                                    @PathVariable(value = "m_id") Long movie_id) throws NotFoundException {
        return ResponseEntity.ok(seanceSer.del(seance_id, cinema_id, movie_id));
    }

    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @GetMapping("/get")
    public ResponseEntity<String> getMovie(@Valid @RequestBody MDto mDto) {
        return ResponseEntity.ok(seanceSer.getMovie(mDto));
    }

}
