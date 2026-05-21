package com.humanidades.repository;
import java.util.List;
import com.humanidades.model.Localidades.Departamento;
import com.humanidades.model.Localidades.Localidad;

import com.humanidades.model.Localidades.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT l FROM Localidad l WHERE l.departamento = :depto")
    public List<Localidad> buscarLocalidadesDepto(@org.springframework.data.repository.query.Param("depto") Departamento depto);
}
