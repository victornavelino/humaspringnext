package com.humanidades.phumanidades.controller;

import com.humanidades.model.Persona.Alumno;
import com.humanidades.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "dni", required = false) String dni) {
        try {
            if (dni != null && !dni.trim().isEmpty()) {
                Alumno alumno = alumnoService.findByAlumnoDni(dni.trim());
                return ResponseEntity.ok(List.of(alumno));
            } else if (search != null && !search.trim().isEmpty()) {
                List<Alumno> filtered = alumnoService.findLikeNombreApellido(search.trim());
                return ResponseEntity.ok(filtered);
            }
            return ResponseEntity.ok(alumnoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable("id") Long id) {
        try {
            // Find by ID is not directly exposed as returning Optional in Service, but we can look it up or use repository if needed.
            // Let's check all Alumnos to find by ID, or we can use the service.
            // Since Alumno inherits from Persona, let's find it.
            List<Alumno> all = alumnoService.findAll();
            for (Alumno a : all) {
                if (a.getId().equals(id)) {
                    return ResponseEntity.ok(a);
                }
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAlumno(@RequestBody Alumno alumno) {
        try {
            alumnoService.create(alumno);
            return ResponseEntity.ok(alumno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable("id") Long id, @RequestBody Alumno alumno) {
        try {
            alumno.setId(id);
            alumnoService.edit(alumno);
            return ResponseEntity.ok(alumno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable("id") Long id) {
        try {
            Alumno alumno = new Alumno();
            alumno.setId(id);
            alumnoService.remove(alumno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
