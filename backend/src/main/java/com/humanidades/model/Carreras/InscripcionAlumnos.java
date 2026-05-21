/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.model.Carreras;

import com.humanidades.model.Persona.Alumno;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;

/**
 *
 * @author vouilloz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "InscripcionAlumnos.inscripcionFindDni", query = "SELECT i FROM InscripcionAlumnos i WHERE i.alumno.dni=:dni"),
    @NamedQuery(name = "InscripcionAlumnos.alumnoFindCohorte", query = "SELECT i.cohorte FROM InscripcionAlumnos i WHERE i.alumno=:alumno"),
    @NamedQuery(name = "InscripcionAlumnos.findAlumnoCohorte", query = "SELECT i.id FROM InscripcionAlumnos i WHERE i.alumno.dni = :dni AND i.cohorte.id = :id")})
public class InscripcionAlumnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date fechaInscripcion;
    private Boolean activo;
    private String matricula;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Cohorte cohorte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Cohorte getCohorte() {
        return cohorte;
    }

    public void setCohorte(Cohorte cohorte) {
        this.cohorte = cohorte;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionAlumnos)) {
            return false;
        }
        InscripcionAlumnos other = (InscripcionAlumnos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return alumno + " - " + cohorte + " - " + cohorte.getCarrera().getDescripcion();
        } catch (Exception e) {
            return "";
        }
    }

}
