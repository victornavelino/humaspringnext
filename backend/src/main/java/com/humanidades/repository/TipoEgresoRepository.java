package com.humanidades.repository;
import com.humanidades.model.Egresos.TipoEgreso;
import java.util.List;

import com.humanidades.model.Egresos.TipoEgreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEgresoRepository extends JpaRepository<TipoEgreso, Long> {
    @org.springframework.data.jpa.repository.Query(name = "TipoEgreso.findNoBorrados")
    public List<TipoEgreso> findNoBorrados();
}
