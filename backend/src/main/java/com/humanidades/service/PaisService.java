/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.PaisRepository;

import com.humanidades.model.Localidades.Pais;
import java.util.List;
/**
 *
 * @author hugo
 */

@org.springframework.stereotype.Service
public class PaisService  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.PaisRepository paisRepository;

    
    public void create(Pais pais) throws Exception {

        paisRepository.save(pais);
    }

    
    public void edit(Pais pais) throws Exception {
        paisRepository.save(pais);
    }

    
    public void remove(Pais pais) throws Exception {
        paisRepository.delete(pais);
    }

    
    public List<Pais> findAll() throws Exception {
        return paisRepository.findAll();
    }

}
