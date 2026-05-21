/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.CorreoElectronicoRepository;

import com.humanidades.model.Persona.CorreoElectronico;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class CorreoElectronicoService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.CorreoElectronicoRepository correoElectronicoRepository;

    
    public void create(CorreoElectronico correoElectronico) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void edit(CorreoElectronico correoElectronico) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void remove(CorreoElectronico correoElectronico) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public List<CorreoElectronico> findAll() throws Exception {
        return correoElectronicoRepository.findAll();
    }

    
    public CorreoElectronico findById(Long id) throws Exception {
        return correoElectronicoRepository.findById(id).orElse(null);
    }

}
