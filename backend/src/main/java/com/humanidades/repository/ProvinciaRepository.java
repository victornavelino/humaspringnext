package com.humanidades.repository;
import com.humanidades.model.Localidades.Pais;
import java.util.List;
import com.humanidades.model.Localidades.Provincia;

import com.humanidades.model.Localidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT p FROM Provincia p WHERE p.pais = :pais")
    public List<Provincia> buscarProvinciasPais(@org.springframework.data.repository.query.Param("pais") Pais pais);
}
