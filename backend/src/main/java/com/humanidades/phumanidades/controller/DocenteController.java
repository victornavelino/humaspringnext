package com.humanidades.phumanidades.controller;

import com.humanidades.model.Persona.Docente;
import com.humanidades.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<Docente>> getDocentes(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "dni", required = false) String dni) {
        try {
            if (dni != null && !dni.trim().isEmpty()) {
                List<Docente> filtered = docenteService.findByDocenteDni(dni.trim());
                return ResponseEntity.ok(filtered);
            } else if (search != null && !search.trim().isEmpty()) {
                List<Docente> filtered = docenteService.findLikeNombreApellido(search.trim());
                return ResponseEntity.ok(filtered);
            }
            return ResponseEntity.ok(docenteService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable("id") Long id) {
        try {
            List<Docente> all = docenteService.findAll();
            for (Docente d : all) {
                if (d.getId().equals(id)) {
                    return ResponseEntity.ok(d);
                }
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createDocente(@RequestBody Docente docente) {
        try {
            docenteService.create(docente);
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocente(@PathVariable("id") Long id, @RequestBody Docente docente) {
        try {
            docente.setId(id);
            docenteService.edit(docente);
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocente(@PathVariable("id") Long id) {
        try {
            Docente docente = new Docente();
            docente.setId(id);
            docenteService.remove(docente);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
