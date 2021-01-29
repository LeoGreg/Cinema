package com.example.cin.controller;

import com.example.cin.model.Cinema;
import com.example.cin.model.dto.MDto;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.service.abst.CinemaSer;
import com.example.cin.util.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin/cinema")
public class CinemaController {
    @Autowired
    private CinemaSer cinemaSer;

    @RolesAllowed(Roles.ADMIN_ROLE)
    @PostMapping("/add")
    public ResponseEntity<Cinema> add(@Valid @RequestBody Cinema cinema) {
        return ResponseEntity.ok(cinemaSer.add_cinema(cinema));
    }
    @RolesAllowed(Roles.ADMIN_ROLE)
    @PutMapping("/update/cinema_id/{id}")
    public ResponseEntity<Cinema> update(@Valid @RequestBody Cinema cinema, @PathVariable(value = "id") Long cinema_id) throws NotFoundException {
        return ResponseEntity.ok(cinemaSer.update_cinema(cinema, cinema_id));
    }
    @RolesAllowed(Roles.ADMIN_ROLE)
    @DeleteMapping("/del/cinema_id/{id}")
    public ResponseEntity<Map<String, Boolean>> del(@PathVariable(value = "id") Long cinema_id) throws NotFoundException {
        return ResponseEntity.ok(cinemaSer.delete_cinema(cinema_id));
    }



}
