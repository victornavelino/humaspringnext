package com.humanidades.phumanidades.controller;

import com.humanidades.model.Carreras.Carrera;
import com.humanidades.service.CarrerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarrerasService carrerasService;

    @GetMapping
    public ResponseEntity<List<Carrera>> getCarreras(@RequestParam(value = "search", required = false) String search) {
        try {
            if (search != null && !search.trim().isEmpty()) {
                return ResponseEntity.ok(carrerasService.findCarreraNombre(search.trim()));
            }
            return ResponseEntity.ok(carrerasService.findCarreras());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable("id") Long id) {
        try {
            Carrera carrera = carrerasService.findByiD(id);
            if (carrera == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrera);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createCarrera(@RequestBody Carrera carrera) {
        try {
            carrerasService.create(carrera);
            return ResponseEntity.ok(carrera);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @RequestBody Carrera carrera) {
        try {
            carrera.setId(id);
            carrerasService.edit(carrera);
            return ResponseEntity.ok(carrera);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable("id") Long id) {
        try {
            Carrera carrera = new Carrera();
            carrera.setId(id);
            carrerasService.remove(carrera);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
