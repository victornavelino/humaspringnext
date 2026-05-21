/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.TarjetaDeCreditoRepository;


import com.humanidades.model.Ingresos.TarjetaDeCredito;
import java.util.List;
/**
 *
 * @author hugo
 */



@org.springframework.stereotype.Service
public class TarjetaDeCreditoService  {
    
    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.TarjetaDeCreditoRepository tarjetaDeCreditoRepository;

    
    public void create(TarjetaDeCredito tarjetaDeCredito) throws Exception {
       tarjetaDeCreditoRepository.save(tarjetaDeCredito);
    }

    
    public void edit(TarjetaDeCredito tarjetaDeCredito) throws Exception {
        tarjetaDeCreditoRepository.save(tarjetaDeCredito);
    }

    
    public void remove(TarjetaDeCredito tarjetaDeCredito) throws Exception {
        tarjetaDeCreditoRepository.delete(tarjetaDeCredito);
    }

    
    public List<TarjetaDeCredito> findAll() throws Exception {
        return tarjetaDeCreditoRepository.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
