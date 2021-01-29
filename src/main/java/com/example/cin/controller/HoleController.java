package com.example.cin.controller;


import com.example.cin.model.Hole;
import com.example.cin.model.exception.DuplicateException;
import com.example.cin.model.exception.NotFoundException;
import com.example.cin.service.abst.HoleSer;
import com.example.cin.util.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin/audit")
public class HoleController {

    @Autowired
    private HoleSer holeSer;

    @RolesAllowed(Roles.ADMIN_ROLE)
    @PostMapping("/add/cinema_id/{id}")
    public ResponseEntity<Hole> add(@Valid @RequestBody Hole a, @PathVariable(value = "id") Long cinema_id) throws NotFoundException, DuplicateException {
        return ResponseEntity.ok(holeSer.add_audit(a, cinema_id));
    }

    @RolesAllowed(Roles.ADMIN_ROLE)
    @PutMapping("/up/hole_id/{hole_id}/cinema_id/{cin_id}")
    public ResponseEntity<Hole> update(@PathVariable(value = "hole_id") Long hole_id,
                                       @PathVariable(value = "cin_id") Long cinema_id,
                                       @Valid @RequestBody Hole a
    ) throws DuplicateException, NotFoundException, javassist.NotFoundException {
        return ResponseEntity.ok(holeSer.update_audit(hole_id, cinema_id, a));
    }

    @RolesAllowed(Roles.ADMIN_ROLE)
    @DeleteMapping("/del/hole_id/{hole_id}/cinema_id/{cin_id}")
    public ResponseEntity<Map<String, Boolean>> update(@PathVariable(value = "hole_id") Long hole_id,
                                                       @PathVariable(value = "cin_id") Long cinema_id) throws NotFoundException {
        return ResponseEntity.ok(holeSer.deleteAudit(hole_id, cinema_id));
    }

}
