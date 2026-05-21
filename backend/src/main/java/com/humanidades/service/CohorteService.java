/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.CohorteRepository;

import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.model.Persona.Alumno;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class CohorteService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.CohorteRepository cohorteRepository;

    
    public void create(Cohorte cohorte) throws Exception {
        if (validar(cohorte)) {
            cohorteRepository.save(cohorte);
        } else {
            throw new Exception("Debe ingresar una carrera");
        }
    }

    
    public void edit(Cohorte cohorte) throws Exception {
        if (validar(cohorte)) {
            cohorteRepository.save(cohorte);
        } else {
            throw new Exception("Debe ingresar una descripción");
        }
    }

    
    public List<Cohorte> findCohortes() throws Exception {
        return cohorteRepository.findAll();
    }

    
    public void remove(Cohorte cohorte) {
        cohorteRepository.delete(cohorte);
    }

    
    public List<Cohorte> findCohorteNombre(String nombre) {
        return cohorteRepository.findCohorteNombre(nombre);
    }

    
    public Cohorte findByiD(Long id) throws Exception {
        return cohorteRepository.findById(id).orElse(null);
    }

    
    public List<InscripcionAlumnos> findAlumnoCohorte(Cohorte cohorte) throws Exception {
        return cohorteRepository.findAlumnoCohorte(cohorte);
    }

    private boolean validar(Cohorte cohorte) {
        return cohorte.getCarrera() != null;
    }
}
