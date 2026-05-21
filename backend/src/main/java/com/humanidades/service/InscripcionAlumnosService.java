/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.InscripcionAlumnosRepository;

import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.model.Persona.Alumno;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class InscripcionAlumnosService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.InscripcionAlumnosRepository inscripcionAlumnosRepository;

    
    public void create(InscripcionAlumnos inscripcionAlumnos) throws Exception {
        inscripcionAlumnosRepository.save(inscripcionAlumnos);
    }

    
    public void edit(InscripcionAlumnos inscripcionAlumnos) throws Exception {
        inscripcionAlumnosRepository.save(inscripcionAlumnos);
    }

    
    public void remove(InscripcionAlumnos inscripcionAlumnos) throws Exception {
        inscripcionAlumnosRepository.delete(inscripcionAlumnos);
    }

    
    public List<InscripcionAlumnos> findAll() throws Exception {
        return inscripcionAlumnosRepository.findAll();
    }

    
    public InscripcionAlumnos buscarInscripcionAlumnos(InscripcionAlumnos inscripcionAlumnos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Cohorte> alumnoFindCohortes(Alumno alumno) throws Exception {
        return inscripcionAlumnosRepository.alumnoFindCohorte(alumno);
    }

    
    public List<InscripcionAlumnos> findAlumnoCohorte(String dni, Long id) throws Exception {
        return inscripcionAlumnosRepository.findAlumnoCohorte(dni, id);
        
    }

    
    public List<InscripcionAlumnos> inscripcionFindDni(String dni) throws Exception {
        return inscripcionAlumnosRepository.inscripcionFindDni(dni);
    }

    
    public InscripcionAlumnos find(Long id) throws Exception {
        return inscripcionAlumnosRepository.findById(id).orElse(null);
    }
}
