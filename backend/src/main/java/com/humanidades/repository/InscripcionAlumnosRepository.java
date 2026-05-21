package com.humanidades.repository;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.model.Persona.Alumno;
import java.util.List;
import com.humanidades.model.Carreras.Cohorte;

import com.humanidades.model.Carreras.InscripcionAlumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionAlumnosRepository extends JpaRepository<InscripcionAlumnos, Long> {
    @org.springframework.data.jpa.repository.Query(name = "InscripcionAlumnos.alumnoFindCohorte")
    public List<Cohorte> alumnoFindCohorte(@org.springframework.data.repository.query.Param("alumno") Alumno alumno);

    @org.springframework.data.jpa.repository.Query(name = "InscripcionAlumnos.findAlumnoCohorte")
    public List<InscripcionAlumnos> findAlumnoCohorte(@org.springframework.data.repository.query.Param("dni") String dni, @org.springframework.data.repository.query.Param("id") Long id);

    @org.springframework.data.jpa.repository.Query(name = "InscripcionAlumnos.inscripcionFindDni")
    public List<InscripcionAlumnos> inscripcionFindDni(@org.springframework.data.repository.query.Param("dni") String dni);


}
