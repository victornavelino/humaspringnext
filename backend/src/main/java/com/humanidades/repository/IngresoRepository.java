package com.humanidades.repository;
import com.humanidades.model.Ingresos.TipoIngreso;
import java.util.List;
import com.humanidades.model.Ingresos.Ingreso;
import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.Cuenta;
import java.util.Date;
import com.humanidades.model.Persona.Alumno;

import com.humanidades.model.Ingresos.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCuotasAlumno")
    public List<Ingreso> findCuotasAlumno(@org.springframework.data.repository.query.Param("alumno") Alumno alumno);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCuotasAlumnoGeneral")
    public List<Ingreso> findCuotasAlumnoGeneral(@org.springframework.data.repository.query.Param("alumno") Alumno alumno);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findAllDesc")
    public List<Ingreso> findAllDesc();

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.UpdateBorrado")
    public void updateBorrado(@org.springframework.data.repository.query.Param("bEstado") Boolean bEstado, @org.springframework.data.repository.query.Param("id") Long id);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findByFechaCohorte")
    public List<Ingreso> findByFechaCohorte(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin, @org.springframework.data.repository.query.Param("cohorte") Cohorte cohorte);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findByFecha")
    public List<Ingreso> findByFecha(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findByFechaGenerales")
    public List<Ingreso> findByFechaGenerales(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCobrosByDni")
    public List<Ingreso> findCobrosByDni(@org.springframework.data.repository.query.Param("dni") String dni);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCobrosByDniOTexto")
    public List<Ingreso> findCobrosByDniOTexto(@org.springframework.data.repository.query.Param("dni") String dni);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.existeNumeroRecibo")
    public boolean existeNumeroRecibo(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("numero") int numero, @org.springframework.data.repository.query.Param("anio") int anio);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.existeNumeroRecibo")
    public Ingreso getByNumeroRecibo(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("numero") int numero, @org.springframework.data.repository.query.Param("anio") int anio);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findAllByNumeroRecibo")
    public List<Ingreso> findAllByNumeroRecibo(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("numero") int numero, @org.springframework.data.repository.query.Param("anio") int anio);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findAllByCuenta")
    public List<Ingreso> findAllByCuenta(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("anio") int anio);

    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(MAX(i.numeroRecibo), 0) + 1 FROM Ingreso i WHERE i.cuenta = :cuenta AND i.borrado = false")
    public int numeroReciboSegunCuenta(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findUltimoNumero")
    public int findUltimoNumero(@org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("anio") int anio);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCobrosGeneralXFecha")
    public List<Ingreso> findCobrosGeneralXFecha(@org.springframework.data.repository.query.Param("fechaIni") Date fechaIni, @org.springframework.data.repository.query.Param("fechaFin") Date fechaFin);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCobrosGeneralXFechaTipo")
    public List<Ingreso> findCobrosGeneralXFechaTipo(@org.springframework.data.repository.query.Param("fechaIni") Date fechaIni, @org.springframework.data.repository.query.Param("fechaFin") Date fechaFin, @org.springframework.data.repository.query.Param("tipoIngreso") TipoIngreso tipoIngreso);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCobrosXFecha")
    public List<Ingreso> findCobrosXFecha(@org.springframework.data.repository.query.Param("fechaIni") Date fechaIni, @org.springframework.data.repository.query.Param("fechaFin") Date fechaFin);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findCuotasAlumnoCohorte")
    public List<Ingreso> findCuotasAlumnoCohorte(@org.springframework.data.repository.query.Param("alumno") Alumno alumno, @org.springframework.data.repository.query.Param("cohorte") Cohorte cohorte);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.ConsultaUltimaCuotaAlumno")
    public List<Object[]> consultaUltimaCuotaAlumno();

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.ConsultaUltimaCuotaAlumnoCohorte")
    public List<Object[]> consultaUltimaCuotaAlumno(@org.springframework.data.repository.query.Param("cohorte") Cohorte cohorte);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findUltimaCuotaAlumno")
    public int findUltimaCuotaAlumno(@org.springframework.data.repository.query.Param("alumno") Alumno alumno);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findUltimaCuotaAlumnoCohorte")
    public int findUltimaCuotaAlumnoCohorte(@org.springframework.data.repository.query.Param("alumno") Alumno alumno, @org.springframework.data.repository.query.Param("cohorte") Cohorte cohorte);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findFechaUltimaCuotaAlumno")
    public Date findFechaUltimaCuotaAlumno(@org.springframework.data.repository.query.Param("alumno") Alumno alumno);

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findAllNoCerrados")
    public List<Ingreso> findAllNoCerrados();

    @org.springframework.data.jpa.repository.Query(name = "Ingreso.findNoCerradosFecha")
    public List<Ingreso> findNoCerradosFecha(@org.springframework.data.repository.query.Param("fechaCierre") Date fechaCierre);
}
