/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanidades.model.Persona;

import com.humanidades.model.Carreras.Cohorte;
import com.humanidades.model.Carreras.InscripcionAlumnos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author vouilloz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Alumno.findByAlumnoDni",
            query = "SELECT a FROM Alumno a where a.dni=:dni"),

    @NamedQuery(name = "Alumno.findLikeNombreApellido", query = "SELECT a FROM Alumno a WHERE a.nombre LIKE :cadena "
            + "OR a.apellido LIKE :cadena ORDER BY a.apellido, a.nombre")})

public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;

    @Enumerated(EnumType.STRING)
    private Calidad calidad;
    @Enumerated(EnumType.STRING)
    private Condicion condicion;

    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefono> telefonos;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<CorreoElectronico> correosElectronicos;

    @JsonIgnore
    @OneToMany(mappedBy = "alumno")
    private List<InscripcionAlumnos> inscripcionesAlumnos;

    public List<InscripcionAlumnos> getInscripcionesAlumnos() {
        return inscripcionesAlumnos;
    }

    public void setInscripcionesAlumnos(List<InscripcionAlumnos> inscripcionesAlumnos) {
        this.inscripcionesAlumnos = inscripcionesAlumnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<CorreoElectronico> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(List<CorreoElectronico> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
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
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return apellido + ", " + nombre;
        } catch (Exception e) {
            return "";
        }
    }

}
