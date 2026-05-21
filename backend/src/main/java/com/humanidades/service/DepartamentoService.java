/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.DepartamentoRepository;

import com.humanidades.model.Localidades.Departamento;
import com.humanidades.model.Localidades.Provincia;
import java.util.List;
/**
 *
 * @author hugo
 */

@org.springframework.stereotype.Service
public class DepartamentoService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.DepartamentoRepository departamentoRepository;

    
    public void create(Departamento departamento) throws Exception {
        //Agregar validaciones
        departamentoRepository.save(departamento);
    }

    
    public void edit(Departamento departamento) throws Exception {
        departamentoRepository.save(departamento);
    }

    
    public void remove(Departamento departamento) throws Exception {
        departamentoRepository.delete(departamento);
    }

    
    public List<Departamento> findAll() throws Exception {
        return departamentoRepository.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Departamento buscarDepartamento(Departamento departamento) {
        return departamentoRepository.findById(departamento.getId()).orElse(null);
    }

    
    public List<Departamento> buscarDptoProvincia(Provincia provincia) {
        return departamentoRepository.buscarDptosProvincia(provincia);
    }

}
