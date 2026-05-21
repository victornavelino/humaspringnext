/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.UsuariosRepository;

import com.humanidades.model.Usuarios.Usuarios;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class UsuariosService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.UsuariosRepository usuariosRepository;

    
    public void create(Usuarios u) throws Exception {
        usuariosRepository.save(u);
    }

    
    public void edit(Usuarios u, String contrasena) throws Exception {
        usuariosRepository.save(u);
    }

    
    public void remove(Long idUsuario, Boolean bEstado) throws Exception {

    }

    
    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Usuarios> findUsuarios(String grupo) throws Exception {
        return usuariosRepository.findAll();
    }

    
    public Usuarios findUserByNombreContrasena(String nombre, String contrasena) throws Exception {
        return usuariosRepository.findUserByNombreContrasena(nombre, contrasena);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void remove(Usuarios usuario) {
        usuariosRepository.delete(usuario);
    }

    
    public void edit(Usuarios usuario) {
        usuariosRepository.save(usuario);
    }

}
