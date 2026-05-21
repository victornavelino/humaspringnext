/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.CarreraRepository;

import com.humanidades.model.Carreras.Carrera;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class CarrerasService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.CarreraRepository carreraRepository;

    
    public void create(Carrera carrera) throws Exception {
        if (validar(carrera)) {
            carreraRepository.save(carrera);
        } else {
            throw new Exception("Debe ingresar una descripción");
        }
    }

    
    public void edit(Carrera carrera) throws Exception {
        if (validar(carrera)) {
            carreraRepository.save(carrera);
        } else {
            throw new Exception("Debe ingresar una descripción");
        }
    }

    
    public void remove(Carrera carrera) {
        carreraRepository.delete(carrera);
    }

    
    public void findAll() {
        carreraRepository.findAll();
    }

    
    public List<Carrera> findCarreras() throws Exception {
        return carreraRepository.findAll();
    }

    
    public List<Carrera> findCarreraNombre(String nombre) {
        return carreraRepository.findCarreraNombre(nombre);
    }

    
    public Carrera findByiD(Long id) throws Exception {
        return carreraRepository.findById(id).orElse(null);
    }

    private boolean validar(Carrera carrera) {
        return !carrera.getDescripcion().isEmpty();
    }

}
