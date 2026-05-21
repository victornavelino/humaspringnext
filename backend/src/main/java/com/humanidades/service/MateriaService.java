/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.MateriaRepository;

import com.humanidades.model.Carreras.Materia;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class MateriaService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.MateriaRepository materiaRepository;

    
    public void create(Materia m) throws Exception {
        materiaRepository.save(m);
    }

    
    public void edit(Materia m) throws Exception {
        materiaRepository.save(m);
    }

    
    public void remove(Materia m) {
        materiaRepository.delete(m);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }
}
