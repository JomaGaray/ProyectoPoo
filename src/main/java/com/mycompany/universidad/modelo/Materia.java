/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universidad.modelo;

/**
 *
 * @author jose
 */
public class Materia {
    
    private String codigo;
    private String nombre;
    private Integer semestre;
    private String profesor;
    private boolean promocionable;
    private boolean optativa;
    private Materia correlativaAnterior; //se toma la correlativa anterior

    public Materia(String codigo, String nombre, Integer semestre, boolean promocionable, boolean optativa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.semestre = semestre;
        this.promocionable = promocionable;
        this.optativa = optativa;
    }

    @Override
    public String toString() {
        String texto =  (nombre + " cod:"+ codigo + " semestre:" + semestre + " profesor:" + profesor + " promocion:"+ promocionable + " correlativa anterior:");
        
        if (correlativaAnterior != null){
            texto = texto + correlativaAnterior.getNombre();
        }else{
            texto = texto + " no tiene";
        }
        
        return texto;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public boolean isPromocionable() {
        return promocionable;
    }

    public void setPromocionable(boolean promocionable) {
        this.promocionable = promocionable;
    }

    public Materia getCorrelativaAnterior() {
        return correlativaAnterior;
    }

    public void setCorrelativaAnterior(Materia correlativaAnterior) {
        this.correlativaAnterior = correlativaAnterior;
    }

    public boolean isOptativa() {
        return optativa;
    }

    public void setOptativa(boolean optativa) {
        this.optativa = optativa;
    }
    
    
    
    
}
