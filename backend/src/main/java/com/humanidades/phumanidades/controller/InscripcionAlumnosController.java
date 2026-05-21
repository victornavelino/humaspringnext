package com.humanidades.phumanidades.controller;

import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.service.InscripcionAlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionAlumnosController {

    @Autowired
    private InscripcionAlumnosService inscripcionAlumnosService;

    @GetMapping
    public ResponseEntity<List<InscripcionAlumnos>> getInscripciones(
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "cohorteId", required = false) Long cohorteId) {
        try {
            if (dni != null && !dni.trim().isEmpty()) {
                if (cohorteId != null) {
                    return ResponseEntity.ok(inscripcionAlumnosService.findAlumnoCohorte(dni.trim(), cohorteId));
                }
                return ResponseEntity.ok(inscripcionAlumnosService.inscripcionFindDni(dni.trim()));
            }
            return ResponseEntity.ok(inscripcionAlumnosService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionAlumnos> getInscripcionById(@PathVariable("id") Long id) {
        try {
            InscripcionAlumnos ins = inscripcionAlumnosService.find(id);
            if (ins == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ins);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createInscripcion(@RequestBody InscripcionAlumnos inscripcion) {
        try {
            inscripcionAlumnosService.create(inscripcion);
            return ResponseEntity.ok(inscripcion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInscripcion(@PathVariable("id") Long id, @RequestBody InscripcionAlumnos inscripcion) {
        try {
            inscripcion.setId(id);
            inscripcionAlumnosService.edit(inscripcion);
            return ResponseEntity.ok(inscripcion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInscripcion(@PathVariable("id") Long id) {
        try {
            InscripcionAlumnos ins = new InscripcionAlumnos();
            ins.setId(id);
            inscripcionAlumnosService.remove(ins);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
