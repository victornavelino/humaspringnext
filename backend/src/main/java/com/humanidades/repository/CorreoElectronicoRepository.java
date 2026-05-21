package com.humanidades.repository;

import com.humanidades.model.Persona.CorreoElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorreoElectronicoRepository extends JpaRepository<CorreoElectronico, Long> {
}
