package com.humanidades.phumanidades.controller;

import com.humanidades.model.Ingresos.Ingreso;
import com.humanidades.model.Usuarios.Usuarios;
import com.humanidades.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public ResponseEntity<List<Ingreso>> getIngresos(
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "fechaIni", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIni,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            if (dni != null && !dni.trim().isEmpty()) {
                return ResponseEntity.ok(ingresoService.findCobrosByDniOTexto(dni.trim()));
            } else if (fechaIni != null && fechaFin != null) {
                return ResponseEntity.ok(ingresoService.findCobrosXFecha(fechaIni, fechaFin));
            }
            return ResponseEntity.ok(ingresoService.findAllDesc());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable("id") Long id) {
        try {
            Ingreso ingreso = ingresoService.find(id);
            if (ingreso == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingreso);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createIngreso(@RequestBody Ingreso ingreso) {
        try {
            ingresoService.create(ingreso);
            return ResponseEntity.ok(ingreso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngreso(@PathVariable("id") Long id, @RequestBody Ingreso ingreso) {
        try {
            ingreso.setId(id);
            ingresoService.edit(ingreso, true);
            return ResponseEntity.ok(ingreso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/anular")
    public ResponseEntity<?> anularIngreso(@PathVariable("id") Long id, @RequestParam(value = "usuario", defaultValue = "admin") String username) {
        try {
            Ingreso ingreso = ingresoService.find(id);
            if (ingreso == null) {
                return ResponseEntity.notFound().build();
            }
            Usuarios dummyUser = new Usuarios();
            dummyUser.setUsuario(username);
            ingresoService.anular(ingreso, dummyUser);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
