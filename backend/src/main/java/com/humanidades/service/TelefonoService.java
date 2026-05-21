/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.TelefonoRepository;

import com.humanidades.model.Persona.Telefono;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class TelefonoService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.TelefonoRepository telefonoRepository;

    
    public void create(Telefono telefono) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void edit(Telefono telefono) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void remove(Telefono telefono) throws Exception {
        telefonoRepository.delete(telefono);
    }

    
    public List<Telefono> findAll() throws Exception {
        return telefonoRepository.findAll();
    }

    
    public Telefono findById(Long id) throws Exception {
        return telefonoRepository.findById(id).orElse(null);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
