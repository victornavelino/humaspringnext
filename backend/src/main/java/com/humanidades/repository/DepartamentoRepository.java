package com.humanidades.repository;
import java.util.List;
import com.humanidades.model.Localidades.Departamento;
import com.humanidades.model.Localidades.Provincia;

import com.humanidades.model.Localidades.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT d FROM Departamento d WHERE d.provincia = :provincia")
    public List<Departamento> buscarDptosProvincia(@org.springframework.data.repository.query.Param("provincia") Provincia provincia);
}
