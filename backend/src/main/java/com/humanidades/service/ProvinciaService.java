/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.ProvinciaRepository;

import com.humanidades.model.Localidades.Pais;
import com.humanidades.model.Localidades.Provincia;
import java.util.List;
/**
 *
 * @author hugo
 */

@org.springframework.stereotype.Service
public class ProvinciaService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.ProvinciaRepository provinciaRepository;

    
    public void create(Provincia provincia) throws Exception {
        //Agregar validaciones
        provinciaRepository.save(provincia);
    }

    
    public void edit(Provincia provincia) throws Exception {
        provinciaRepository.save(provincia);
    }

    
    public void remove(Provincia provincia) throws Exception {
        provinciaRepository.delete(provincia);
    }

    
    public List<Provincia> findAll() throws Exception {
        return provinciaRepository.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Provincia buscarProvincia(Provincia provincia) {
        return provinciaRepository.findById(provincia.getId()).orElse(null);
    }

    
    public List<Provincia> buscarProvinciasPais(Pais pais) {
        return provinciaRepository.buscarProvinciasPais(pais);
    }

}
