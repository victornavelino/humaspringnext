package com.humanidades.phumanidades.controller;

import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.service.CohorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cohortes")
public class CohorteController {

    @Autowired
    private CohorteService cohorteService;

    @GetMapping
    public ResponseEntity<List<Cohorte>> getCohortes(@RequestParam(value = "search", required = false) String search) {
        try {
            if (search != null && !search.trim().isEmpty()) {
                return ResponseEntity.ok(cohorteService.findCohorteNombre(search.trim()));
            }
            return ResponseEntity.ok(cohorteService.findCohortes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cohorte> getCohorteById(@PathVariable("id") Long id) {
        try {
            Cohorte cohorte = cohorteService.findByiD(id);
            if (cohorte == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cohorte);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/inscripciones")
    public ResponseEntity<List<InscripcionAlumnos>> getCohorteInscripciones(@PathVariable("id") Long id) {
        try {
            Cohorte cohorte = cohorteService.findByiD(id);
            if (cohorte == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cohorteService.findAlumnoCohorte(cohorte));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createCohorte(@RequestBody Cohorte cohorte) {
        try {
            cohorteService.create(cohorte);
            return ResponseEntity.ok(cohorte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCohorte(@PathVariable("id") Long id, @RequestBody Cohorte cohorte) {
        try {
            cohorte.setId(id);
            cohorteService.edit(cohorte);
            return ResponseEntity.ok(cohorte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCohorte(@PathVariable("id") Long id) {
        try {
            Cohorte cohorte = new Cohorte();
            cohorte.setId(id);
            cohorteService.remove(cohorte);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
