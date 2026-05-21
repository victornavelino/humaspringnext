package com.humanidades.repository;
import com.humanidades.model.Persona.Proveedor;
import java.util.List;

import com.humanidades.model.Persona.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Proveedor.findByCuit")
    public List<Proveedor> findByCuit(@org.springframework.data.repository.query.Param("cuit") String cuit);

    @org.springframework.data.jpa.repository.Query(name = "Proveedor.findByRazonSocial")
    public Proveedor findByRazonSocial(@org.springframework.data.repository.query.Param("razonSocial") String razonSocial);

    @org.springframework.data.jpa.repository.Query(name = "Proveedor.buscarProveedorRazonSocial")
    public List<Proveedor> buscarProveedorRazonSocial(@org.springframework.data.repository.query.Param("razonSocial") String razonSocial);
}
