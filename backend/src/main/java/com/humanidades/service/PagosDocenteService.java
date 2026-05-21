/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.PagosDocenteRepository;

import com.humanidades.model.Carreras.Carrera;
import com.humanidades.model.Carreras.Cuenta;
import com.humanidades.model.Egresos.PagosDocente;
import com.humanidades.model.Egresos.TipoEgreso;
import java.util.Date;
import java.util.List;
/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class PagosDocenteService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.PagosDocenteRepository pagosDocenteRepository;

    
    public void create(PagosDocente pagosDocente) throws Exception {
        pagosDocente.setBorrado(Boolean.FALSE);
        pagosDocente.setAnulado(Boolean.FALSE);
        pagosDocenteRepository.save(pagosDocente);
    }

    
    public void edit(PagosDocente pagosDocente) throws Exception {
        pagosDocenteRepository.save(pagosDocente);
    }

    
    public List<PagosDocente> findAll() throws Exception {
        return pagosDocenteRepository.findAll();
    }

    
    public List<PagosDocente> findAllDesc() throws Exception {
        return pagosDocenteRepository.findAllDesc();
    }

    
    public PagosDocente buscarPagosDocente(PagosDocente pagosDocente) {
        return pagosDocenteRepository.findById(pagosDocente.getId()).orElse(null);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<PagosDocente> findByFechaCarrera(Date ini, Date fin, Carrera carrera) throws Exception {
        return pagosDocenteRepository.findByFechaCarrera(ini, fin, carrera);
    }

    
    public List<PagosDocente> findPagosGeneralXFecha(Date ini, Date fin) {
        return pagosDocenteRepository.findPagosGeneralXFecha(ini, fin);
    }

    
    public List<PagosDocente> findByFechaCarreraDocente(Date ini, Date fin, Carrera carrera) {
        return pagosDocenteRepository.findByFechaCarreraDocente(ini, fin, carrera);
    }

    
    public List<PagosDocente> findPagosXFechaDocente(Date ini, Date fin) {
        return pagosDocenteRepository.findPagosXFechaDocente(ini, fin);
    }

    
    public List<PagosDocente> findPagosXFechaProveedor(Date ini, Date fin) {
        return pagosDocenteRepository.findPagosXFechaProveedor(ini, fin);
    }

    
    public void remove(PagosDocente pagosDocente, Boolean bEstado) throws Exception {
        //System.out.println("remove pagoDocente: " + bEstado + " " + pagosDocente.getId());
        pagosDocenteRepository.updateBorrado(bEstado, pagosDocente.getId());
    }

    
    public List<PagosDocente> findPagosByDni(String dni) throws Exception {
        return pagosDocenteRepository.findPagosByDni(dni);
    }

    
    public int findUltimoNumero() {
        return pagosDocenteRepository.findUltimoNumero();
    }

    
    public PagosDocente buscarPagosDocenteId(Long id) {
        return pagosDocenteRepository.buscarPagosDocenteId(id);
    }

    
    public List<PagosDocente> findPagosXFechaProveedorYCuenta(Date ini, Date fin, Cuenta cuenta) {
        return pagosDocenteRepository.findPagosXFechaProveedorYCuenta(ini, fin, cuenta);
    }

    
    public List<PagosDocente> findPagosByTipoEgreso(TipoEgreso tipo) throws Exception {
        return pagosDocenteRepository.findPagosByTipoEgreso(tipo);
    }

    
    public List<PagosDocente> findPagosByPredicates(Date ini, Date fin, Cuenta cuenta, TipoEgreso tipoEgreso, Carrera carrera) throws Exception {
        return pagosDocenteRepository.findPagosByPredicates(ini, fin, cuenta, tipoEgreso, carrera);
    }

    
    public List<PagosDocente> findPagosByNumeroOrdenPago(int numeroOrdenPago) throws Exception {
        return pagosDocenteRepository.findPagosByNumeroOrdenPago(numeroOrdenPago);
    }

    
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnio(int numeroOrdenPago, int year) throws Exception {
        return pagosDocenteRepository.findPagosByNumeroOrdenPagoAnio(numeroOrdenPago, year);
    }

    
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnioAnulado(int numeroOrdenPago, int year) throws Exception {
        return pagosDocenteRepository.findPagosByNumeroOrdenPagoAnioAnulado(numeroOrdenPago, year);
    }

    
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnioBorrado(int numeroOrdenPago, int year) throws Exception {
        return pagosDocenteRepository.findPagosByNumeroOrdenPagoAnioBorrado(numeroOrdenPago, year);
    }

    
    public void removeTotal(PagosDocente pagosDocente) throws Exception {
        pagosDocenteRepository.delete(pagosDocente);
    }

}
