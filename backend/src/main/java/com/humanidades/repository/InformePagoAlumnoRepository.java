package com.humanidades.repository;

import com.humanidades.model.Ingresos.InformePagoAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformePagoAlumnoRepository extends JpaRepository<InformePagoAlumno, Long> {
}
