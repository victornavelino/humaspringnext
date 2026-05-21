package com.humanidades.repository;
import com.humanidades.model.Carreras.Cuenta;

import com.humanidades.model.Carreras.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Cuenta.findAllByCodigo")
    public Cuenta findAllByCodigo(@org.springframework.data.repository.query.Param("codigo") String codigo);
}
