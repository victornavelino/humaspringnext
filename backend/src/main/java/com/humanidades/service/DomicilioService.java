/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.DomicilioRepository;

import com.humanidades.model.Persona.Domicilio;
import java.util.List;
/**
 *
 * @author hugo
 */

@org.springframework.stereotype.Service
public class DomicilioService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.DomicilioRepository domicilioRepository;

    
    public void create(Domicilio domicilio) throws Exception {
        //Agregar validaciones
        domicilioRepository.save(domicilio);
    }

    
    public void edit(Domicilio domicilio) throws Exception {
        domicilioRepository.save(domicilio);
    }

    
    public void remove(Domicilio domicilio) throws Exception {
        domicilioRepository.delete(domicilio);
    }

    
    public List<Domicilio> findAll() throws Exception {
        return domicilioRepository.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Domicilio buscarDomicilio(Domicilio domicilio) {
        return domicilioRepository.findById(domicilio.getId()).orElse(null);
    }

}
