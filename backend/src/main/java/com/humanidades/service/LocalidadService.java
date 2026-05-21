/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.LocalidadRepository;

import com.humanidades.model.Localidades.Departamento;
import com.humanidades.model.Localidades.Localidad;
import java.util.List;
/**
 *
 * @author hugo
 */

@org.springframework.stereotype.Service
public class LocalidadService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.LocalidadRepository localidadRepository;

    
    public void create(Localidad localidad) throws Exception {
        //Agregar validaciones
        localidadRepository.save(localidad);
    }

    
    public void edit(Localidad localidad) throws Exception {
        localidadRepository.save(localidad);
    }

    
    public void remove(Localidad localidad) throws Exception {
        localidadRepository.delete(localidad);
    }

    
    public List<Localidad> findAll() throws Exception {
        return localidadRepository.findAll();
    }

    
    public List<Localidad> buscarLocalidadesDepto(Departamento depto) {
        return localidadRepository.buscarLocalidadesDepto(depto);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Localidad buscarLocalidad(Localidad localidad) {
        return localidadRepository.findById(localidad.getId()).orElse(null);
    }

}
