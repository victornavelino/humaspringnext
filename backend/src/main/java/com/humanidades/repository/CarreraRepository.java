package com.humanidades.repository;
import java.util.List;
import com.humanidades.model.Carreras.Carrera;

import com.humanidades.model.Carreras.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Carrera.findCarreraNombre")
    public List<Carrera> findCarreraNombre(@org.springframework.data.repository.query.Param("nombre") String nombre);
}
