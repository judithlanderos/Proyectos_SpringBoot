 package com.example.Proyecto_ABCC_MySQL_SpringBoot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Entidades
@Entity
public class AlumnoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String num_Control;
    private String nombre;
    private String primer_Ap;
    private String segundo_Ap;
    private String fecha_nac;
    private Integer semestre;
    private String carrera;

    public AlumnoModel() {
    }

//get
    public Integer getId() {
        return id;
    }
    public String getNum_Control() {
        return num_Control;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPrimer_Ap() {
        return primer_Ap;
    }
    public String getSegundo_Ap() {
        return segundo_Ap;
    }
    public String getFecha_nac() {
        return fecha_nac;
    }
    public Integer getSemestre() {
        return semestre;
    }
    public String getCarrera() {
        return carrera;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNum_Control(String num_Control) {
        this.num_Control = num_Control;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimer_Ap(String primer_Ap) {
        this.primer_Ap = primer_Ap;
    }

    public void setSegundo_Ap(String segundo_Ap) {
        this.segundo_Ap = segundo_Ap;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
 
    
}
