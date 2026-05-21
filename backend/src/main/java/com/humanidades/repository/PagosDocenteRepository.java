package com.humanidades.repository;
import com.humanidades.model.Egresos.TipoEgreso;
import com.humanidades.model.Egresos.PagosDocente;
import com.humanidades.model.Carreras.Cuenta;
import java.util.Date;
import java.util.List;
import com.humanidades.model.Carreras.Carrera;

import com.humanidades.model.Egresos.PagosDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosDocenteRepository extends JpaRepository<PagosDocente, Long> {
    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findByFechaCarrera")
    public List<PagosDocente> findByFechaCarrera(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin, @org.springframework.data.repository.query.Param("carrera") Carrera carrera);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findAllDesc")
    public List<PagosDocente> findAllDesc();

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.UpdateBorrado")
    public void updateBorrado(@org.springframework.data.repository.query.Param("bEstado") Boolean bEstado, @org.springframework.data.repository.query.Param("id") Long id);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByDni")
    public List<PagosDocente> findPagosByDni(@org.springframework.data.repository.query.Param("dni") String dni);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosGeneralXFecha")
    public List<PagosDocente> findPagosGeneralXFecha(@org.springframework.data.repository.query.Param("fechaIni") Date fechaIni, @org.springframework.data.repository.query.Param("fechaFin") Date fechaFin);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findByFechaCarreraDocente")
    public List<PagosDocente> findByFechaCarreraDocente(@org.springframework.data.repository.query.Param("fechaIni") Date fechaIni, @org.springframework.data.repository.query.Param("fechaFin") Date fechaFin, @org.springframework.data.repository.query.Param("carrera") Carrera carrera);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findUltimoNumero")
    public int findUltimoNumero();

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosDocentesId")
    public PagosDocente buscarPagosDocenteId(@org.springframework.data.repository.query.Param("id") Long id);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosXFechaDocente")
    public List<PagosDocente> findPagosXFechaDocente(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosXFechaProveedor")
    public List<PagosDocente> findPagosXFechaProveedor(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosXFechaProveedorYCuenta")
    public List<PagosDocente> findPagosXFechaProveedorYCuenta(@org.springframework.data.repository.query.Param("ini") Date ini, @org.springframework.data.repository.query.Param("fin") Date fin, @org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByTipoEgreso")
    public List<PagosDocente> findPagosByTipoEgreso(@org.springframework.data.repository.query.Param("tipo") TipoEgreso tipo);

    @org.springframework.data.jpa.repository.Query("SELECT p FROM PagosDocente p WHERE p.borrado = false AND p.anulado = false " +
        "AND p.fechaRegistro BETWEEN :FechaInicio AND :FechaFin " +
        "AND (:cuenta IS NULL OR p.cuenta = :cuenta) " +
        "AND (:tipoEgreso IS NULL OR p.tipoEgreso = :tipoEgreso) " +
        "AND (:carrera IS NULL OR p.carrera = :carrera) " +
        "ORDER BY p.fechaRegistro DESC")
    public List<PagosDocente> findPagosByPredicates(@org.springframework.data.repository.query.Param("FechaInicio") Date FechaInicio, @org.springframework.data.repository.query.Param("FechaFin") Date FechaFin, @org.springframework.data.repository.query.Param("cuenta") Cuenta cuenta, @org.springframework.data.repository.query.Param("tipoEgreso") TipoEgreso tipoEgreso, @org.springframework.data.repository.query.Param("carrera") Carrera carrera);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByNumeroOrdenPago")
    public List<PagosDocente> findPagosByNumeroOrdenPago(@org.springframework.data.repository.query.Param("numeroOrdenPago") int numeroOrdenPago);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByNumeroOrdenPagoAnio")
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnio(@org.springframework.data.repository.query.Param("numeroOrdenPago") int numeroOrdenPago, @org.springframework.data.repository.query.Param("year") int year);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByNumeroOrdenPagoAnioAnulado")
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnioAnulado(@org.springframework.data.repository.query.Param("numeroOrdenPago") int numeroOrdenPago, @org.springframework.data.repository.query.Param("year") int year);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findPagosByNumeroOrdenPagoAnioBorrado")
    public List<PagosDocente> findPagosByNumeroOrdenPagoAnioBorrado(@org.springframework.data.repository.query.Param("numeroOrdenPago") int numeroOrdenPago, @org.springframework.data.repository.query.Param("year") int year);

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findAllNoCerrados")
    public List<PagosDocente> findAllNoCerrados();

    @org.springframework.data.jpa.repository.Query(name = "PagosDocente.findNoCerradosFecha")
    public List<PagosDocente> findNoCerradosFecha(@org.springframework.data.repository.query.Param("fechaCierre") Date fechaCierre);
}
