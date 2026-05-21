/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.service;

import com.humanidades.repository.IngresoRepository;

import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.Cuenta;
import com.humanidades.model.Ingresos.Ingreso;
import com.humanidades.model.Ingresos.TipoIngreso;
import com.humanidades.model.Persona.Alumno;
import com.humanidades.model.Usuarios.Usuarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;



/**
 *
 * @author vouilloz
 */

@org.springframework.stereotype.Service
public class IngresoService  {

    @org.springframework.beans.factory.annotation.Autowired
    private com.humanidades.repository.IngresoRepository ingresoRepository;

    
    public void create(Ingreso ingresoCuota) throws Exception {
        this.validar(ingresoCuota);
        ingresoRepository.save(ingresoCuota);
    }

    
    public void create(Ingreso ingresoCuota, boolean multiCuota) throws Exception {
        this.validar(ingresoCuota, true);
        ingresoRepository.save(ingresoCuota);
    }

    
    public void edit(Ingreso ingresoCuota) throws Exception {
        this.validar(ingresoCuota, true);
        ingresoRepository.save(ingresoCuota);
    }

    
    public List<Ingreso> findAll() throws Exception {
        return ingresoRepository.findAll();
    }

    
    public List<Ingreso> findAllDesc() throws Exception {
        return ingresoRepository.findAllDesc();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Ingreso> findCuotasAlumno(Alumno alumno) throws Exception {
        return ingresoRepository.findCuotasAlumno(alumno);
    }

    
    public void remove(Ingreso ingresoCuota, Boolean bEstado) throws Exception {
        this.validar(ingresoCuota, true);
        ingresoRepository.updateBorrado(bEstado, ingresoCuota.getId());
    }

    
    public List<Ingreso> findByFechaCohorte(Date ini, Date fin, Cohorte cohorte) throws Exception {
        return ingresoRepository.findByFechaCohorte(ini, fin, cohorte);
    }

    
    public List<Ingreso> findByFecha(Date ini, Date fin) {
        return ingresoRepository.findByFecha(ini, fin);
    }

    
    public List<Ingreso> findByFechaGenerales(Date ini, Date fin) {
        return ingresoRepository.findByFechaGenerales(ini, fin);
    }

    
    public Ingreso getByNumeroRecibo(Cuenta cuenta, int numero, int anio) {
        return ingresoRepository.getByNumeroRecibo(cuenta, numero, anio);
    }

    
    public void anular(Ingreso ingreso, Usuarios usuario) throws Exception {
        if (ingreso.getNumeroRecibo() != 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(ingreso.getFechaPago());
            int anio = c.get(Calendar.YEAR);
            List<Ingreso> findAllByNumeroRecibo = ingresoRepository.findAllByNumeroRecibo(ingreso.getCuenta(), ingreso.getNumeroRecibo(), anio);
            for (Ingreso i : findAllByNumeroRecibo) {
                i.setFechaModificado(new Date());
                i.setModificadoPor(usuario.getUsuario());
                i.setAnulado(true);
                this.edit(i);
            }
        }
    }

    private void validar(Ingreso ingresoCuota) throws Exception {
        if (ingresoCuota.getFechaPago() == null) {
            throw new Exception("No selecciono Fecha de Pago");
        }//fin if
        Date ultimoPago = ingresoRepository.findFechaUltimaCuotaAlumno(ingresoCuota.getAlumno());
        if (ingresoCuota.getFechaPago() != null && ultimoPago != null) {
            if (ingresoCuota.getFechaPago().compareTo(ultimoPago) < 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(ultimoPago);
                String format = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                throw new Exception("La Fecha de Pago debe ser mayor o igual que el ultimo pago: "
                        + format);
            }
        }
        if (ingresoCuota.getCuenta() == null) {
            throw new Exception("Debe seleccionar una cuenta");
        } else if (ingresoCuota.getCuenta().getCodigo() == null) {
            throw new Exception("Cuenta Incorrecta");
        } else if (ingresoCuota.getId() == null) { //si es nulo entonces es un nuevo ingreso
            int numeroRecibo = ingresoCuota.getNumeroRecibo();
            if (numeroRecibo != 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(ingresoCuota.getFechaPago());
                int anio = c.get(Calendar.YEAR);
                if (ingresoRepository.existeNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio)) {
                    throw new Exception("Ya existe el numero de recibo, seleccione otro");
                }
            }
        } else {
            int numeroRecibo = ingresoCuota.getNumeroRecibo();
            Calendar c = Calendar.getInstance();
            c.setTime(ingresoCuota.getFechaPago());
            int anio = c.get(Calendar.YEAR);
            Ingreso byNumeroRecibo = ingresoRepository.getByNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio);
            ingresoRepository.findAllByNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio);

            if (byNumeroRecibo != null) {
                if (!Objects.equals(byNumeroRecibo.getId(), ingresoCuota.getId())) {
                    if (numeroRecibo != 0) {
                        List<Ingreso> findAllByNumeroRecibo = ingresoRepository.findAllByNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio);
                        if (findAllByNumeroRecibo.size() < 2) {
                            if (ingresoRepository.existeNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio)) {
                                throw new Exception("Ya existe el numero de recibo, seleccione otro");
                            }
                        }
                    }
                }
            }
        }

    }

    private void validar(Ingreso ingresoCuota, boolean multiCuotas) throws Exception {
        if (ingresoCuota.getFechaPago() == null) {
            throw new Exception("No selecciono Fecha de Pago");
        }//fin if
        if (ingresoCuota.getCuenta() == null) {
            throw new Exception("Debe seleccionar una cuenta");
        } else if (ingresoCuota.getCuenta().getCodigo() == null) {
            throw new Exception("Cuenta Incorrecta");
        } else if (ingresoCuota.getId() == null) { //si es nulo entonces es un nuevo ingreso
            int numeroRecibo = ingresoCuota.getNumeroRecibo();
            if (numeroRecibo != 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(ingresoCuota.getFechaPago());
                int anio = c.get(Calendar.YEAR);
                if (ingresoRepository.existeNumeroRecibo(ingresoCuota.getCuenta(), numeroRecibo, anio)) {
                    throw new Exception("Ya existe el numero de recibo, seleccione otro");
                }
            }
        }

    }

    
    public List<Ingreso> findCobrosByDni(String dni) throws Exception {
        return ingresoRepository.findCobrosByDni(dni);
    }

    
    public List<Ingreso> findCobrosByDniOTexto(String dni) throws Exception {
        return ingresoRepository.findCobrosByDniOTexto(dni);
    }

    
    public int numeroReciboSegunCuenta(Cuenta cuenta) throws Exception {
        return ingresoRepository.numeroReciboSegunCuenta(cuenta);
    }

    
    public List<Ingreso> findCobrosGeneralXFecha(Date fechaIni, Date fechaFin) {
        return ingresoRepository.findCobrosGeneralXFecha(fechaIni, fechaFin);
    }

    
    public List<Ingreso> findCobrosGeneralXFechaTipo(Date fechaIni, Date fechaFin, TipoIngreso tipoIngreso) {
        return ingresoRepository.findCobrosGeneralXFechaTipo(fechaIni, fechaFin, tipoIngreso);
    }

    
    public List<Ingreso> findCobrosXFecha(Date fechaIni, Date fechaFin) {
        return ingresoRepository.findCobrosXFecha(fechaIni, fechaFin);
    }

    
    public List<Ingreso> findCuotasAlumnoCohorte(Alumno alumno, Cohorte cohorte) {
        return ingresoRepository.findCuotasAlumnoCohorte(alumno, cohorte);
    }

    
    public int findUltimaCuotaAlumno(Alumno alumno) {
        return ingresoRepository.findUltimaCuotaAlumno(alumno);
    }

    
    public int findUltimaCuotaAlumnoCohorte(Alumno alumno, Cohorte cohorte) {
        return ingresoRepository.findUltimaCuotaAlumnoCohorte(alumno, cohorte);
    }

    
    public Ingreso find(Long id) {
        return ingresoRepository.findById(id).orElse(null);
    }

    
    public List<Ingreso> findCuotasAlumnoGeneral(Alumno alumno) throws Exception {
        return ingresoRepository.findCuotasAlumnoGeneral(alumno);
    }

    
    public List<Object[]> consultaUltimaCuotaAlumno() {
        return ingresoRepository.consultaUltimaCuotaAlumno();
    }
    
      
    public List<Object[]> consultaUltimaCuotaAlumno(Cohorte cohorte) {
        return ingresoRepository.consultaUltimaCuotaAlumno(cohorte);
    }

    
    public List<Ingreso> findAllByNumeroRecibo(Cuenta cuenta, int numero, int anio) {
        return ingresoRepository.findAllByNumeroRecibo(cuenta, numero, anio);
    }

    
    public List<Ingreso> findAllByCuenta(Cuenta cuenta, int anio) {
        return ingresoRepository.findAllByCuenta(cuenta, anio);
    }

    
    public void edit(Ingreso ingresoCuota, boolean validar) throws Exception {
        if (validar) {
            this.validar(ingresoCuota);
        }
        ingresoRepository.save(ingresoCuota);
    }

    
    public int findUltimoNumero(Cuenta cuenta, int anio) {
        return ingresoRepository.findUltimoNumero(cuenta, anio);
    }

    
    public List<Ingreso> findAllNoCerrados() {
        return ingresoRepository.findAllNoCerrados();
    }

}
