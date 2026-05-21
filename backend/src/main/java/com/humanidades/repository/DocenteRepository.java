package com.humanidades.repository;
import com.humanidades.model.Persona.Docente;
import java.util.List;

import com.humanidades.model.Persona.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {


    @org.springframework.data.jpa.repository.Query(name = "Docente.findByDocenteDni")
    public List<Docente> findByDocenteDni(@org.springframework.data.repository.query.Param("dni") String dni);

    @org.springframework.data.jpa.repository.Query(name = "Docente.findLikeNombreApellido")
    public List<Docente> findLikeNombreApellido(@org.springframework.data.repository.query.Param("cadena") String cadena);

    @org.springframework.data.jpa.repository.Query(name = "Docente.findByDocenteDni")
    public Docente findDocenteDni(@org.springframework.data.repository.query.Param("dni") String dni);
}
