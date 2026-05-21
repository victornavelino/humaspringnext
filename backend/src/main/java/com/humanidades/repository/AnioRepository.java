package com.humanidades.repository;

import com.humanidades.model.Carreras.Anio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnioRepository extends JpaRepository<Anio, Long> {
}
