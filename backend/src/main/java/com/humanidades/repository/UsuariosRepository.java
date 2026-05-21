package com.humanidades.repository;
import com.humanidades.model.Usuarios.Usuarios;

import com.humanidades.model.Usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Usuarios.findUserByNombreContrasena")
    public Usuarios findUserByNombreContrasena(@org.springframework.data.repository.query.Param("nombre") String nombre, @org.springframework.data.repository.query.Param("contrasena") String contrasena);
}
