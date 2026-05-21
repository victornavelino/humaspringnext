/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.TipoCarreraRepository;

import com.humanidades.model.Carreras.TipoCarrera;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class TipoCarreraService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.TipoCarreraRepository tipoCarreraRepository;

    
    public void create(TipoCarrera tipoCarrera) throws Exception {
        tipoCarreraRepository.save(tipoCarrera);
    }

    
    public void remove(TipoCarrera tipoCarrera) {
        tipoCarreraRepository.delete(tipoCarrera);
    }

    
    public void edit(TipoCarrera tipoCarrera) {
        tipoCarreraRepository.save(tipoCarrera);
    }

    
    public List<TipoCarrera> findAll() {
        return tipoCarreraRepository.findAll();
    }

    
    public TipoCarrera findByiD(Long id) throws Exception {
        return tipoCarreraRepository.findById(id).orElse(null);
    }

}
