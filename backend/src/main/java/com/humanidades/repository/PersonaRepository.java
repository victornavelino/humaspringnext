package com.humanidades.repository;
import com.humanidades.model.Persona.Persona;
import java.util.List;

import com.humanidades.model.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :cadena, '%')) OR LOWER(p.apellido) LIKE LOWER(CONCAT('%', :cadena, '%'))")
    public List<Persona> buscarPersonaNombre(@org.springframework.data.repository.query.Param("cadena") String cadena);

    @org.springframework.data.jpa.repository.Query(name = "Persona.FindPersonaByDNI")
    public Boolean FindPersonaByDNI(@org.springframework.data.repository.query.Param("dni") String dni, @org.springframework.data.repository.query.Param("id") Long id);
}
