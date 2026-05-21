/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.ProveedorRepository;

import com.humanidades.model.Persona.Proveedor;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class ProveedorService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.ProveedorRepository proveedorRepository;

    
    public void create(Proveedor proveedor) throws Exception {
        proveedorRepository.save(proveedor);
    }

    
    public void edit(Proveedor proveedor) throws Exception {
        proveedorRepository.save(proveedor);
    }

    
    public void remove(Proveedor proveedor) throws Exception {
        proveedorRepository.delete(proveedor);
    }

    
    public List<Proveedor> findAll() throws Exception {
        return proveedorRepository.findAll();
    }

    
    public Proveedor buscarProveedor(Proveedor proveedor) {
        return proveedorRepository.findById(proveedor.getId()).orElse(null);
    }
    
    public Proveedor findByRazonSocial(String razonSocial) {
        return proveedorRepository.findByRazonSocial(razonSocial);
    }
    
    public List<Proveedor> findLikeNombreApellido(String razonSocial) throws Exception {
        return proveedorRepository.buscarProveedorRazonSocial(razonSocial);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Proveedor> findByCuit(String cuit) {
        return proveedorRepository.findByCuit(cuit);
    }

    
    public List<Proveedor> buscarProveedorRazonSocial(String cadena) throws Exception {
        return proveedorRepository.buscarProveedorRazonSocial(cadena);
    }

    
    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }
}
