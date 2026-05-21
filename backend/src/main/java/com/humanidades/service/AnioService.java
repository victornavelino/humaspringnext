/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.AnioRepository;

import com.humanidades.model.Carreras.Anio;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class AnioService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.AnioRepository anioRepository;

    
    public void create(Anio anio) throws Exception {
        anioRepository.save(anio);
    }

    
    public void remove(Anio anio) {
        anioRepository.delete(anio);
    }

    
    public void edit(Anio anio) {
        anioRepository.save(anio);
    }

    
    public List<Anio> findAll() {
        return anioRepository.findAll();
    }

    
    public Anio findByiD(Long id) throws Exception {
        return anioRepository.findById(id).orElse(null);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
