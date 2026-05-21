package com.humanidades.repository;
import com.humanidades.model.Ingresos.TipoIngreso;
import java.util.List;

import com.humanidades.model.Ingresos.TipoIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIngresoRepository extends JpaRepository<TipoIngreso, Long> {
    @org.springframework.data.jpa.repository.Query(name = "TipoIngreso.findNoBorrados")
    public List<TipoIngreso> findNoBorrados();
}
