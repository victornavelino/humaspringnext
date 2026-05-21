package com.humanidades.repository;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import com.humanidades.model.Carreras.Cohorte;
import java.util.List;

import com.humanidades.model.Carreras.Cohorte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CohorteRepository extends JpaRepository<Cohorte, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Cohorte.findCohorteNombre")
    public List<Cohorte> findCohorteNombre(@org.springframework.data.repository.query.Param("nombre") String nombre);

    @org.springframework.data.jpa.repository.Query(name = "Cohorte.findAlumnoCohorte2")
    public List<InscripcionAlumnos> findAlumnoCohorte(@org.springframework.data.repository.query.Param("cohorte") Cohorte cohorte);
}
