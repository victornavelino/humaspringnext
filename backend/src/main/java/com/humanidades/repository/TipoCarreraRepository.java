package com.humanidades.repository;

import com.humanidades.model.Carreras.TipoCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCarreraRepository extends JpaRepository<TipoCarrera, Long> {
}
