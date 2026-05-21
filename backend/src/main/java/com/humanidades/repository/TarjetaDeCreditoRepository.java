package com.humanidades.repository;

import com.humanidades.model.Ingresos.TarjetaDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaDeCreditoRepository extends JpaRepository<TarjetaDeCredito, Long> {
}
