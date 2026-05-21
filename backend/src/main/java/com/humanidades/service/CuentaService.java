/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.CuentaRepository;

import com.humanidades.model.Carreras.Cuenta;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class CuentaService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.CuentaRepository cuentaRepository;

    
    public void create(Cuenta cuenta) throws Exception {
        cuentaRepository.save(cuenta);
    }

    
    public void remove(Cuenta cuenta) {
        cuentaRepository.delete(cuenta);
    }

    
    public void edit(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    
    public Cuenta findByiD(Long id) throws Exception {
        return cuentaRepository.findById(id).orElse(null);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    public Cuenta findAllByCodigo(String codigo) {
        return cuentaRepository.findAllByCodigo(codigo);
    }
}
