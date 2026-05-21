package com.humanidades.repository;
import com.humanidades.model.Persona.Alumno;
import java.util.List;

import com.humanidades.model.Persona.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Alumno.findLikeNombreApellido")
    public List<Alumno> findLikeNombreApellido(@org.springframework.data.repository.query.Param("cadena") String cadena);

    @org.springframework.data.jpa.repository.Query(name = "Alumno.findByAlumnoDni")
    public Alumno findAlumnoDni(@org.springframework.data.repository.query.Param("dni") String dni);
}
