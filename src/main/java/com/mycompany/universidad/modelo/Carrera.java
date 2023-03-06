/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universidad.modelo;

/**
 *
 * @author jose
 */
public class Carrera {
    
    
    
    private String codigo;
    private String nombre;
    
    private PlanEstudio planEstudio;
    private Integer materiasOptativas;
    

    public Carrera(String codigo, String nombre, Integer materiasOptativas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.materiasOptativas = materiasOptativas;
    }

    @Override
    public String toString() {
        return  nombre +", codigo:" + codigo + ", Tipo de plan:" + planEstudio.getTipoPlan();
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(PlanEstudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public Integer getMateriasOptativas() {
        return materiasOptativas;
    }

    public void setMateriasOptativas(Integer materiasOptativas) {
        this.materiasOptativas = materiasOptativas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
//    public Integer [] getCantidadMateriasobligatorias(){
//        
//        int [] materias;
//        
//        int cuatrimestres = this.planEstudio.getCuatrimestres().size();
//        
//        for (int i = 1; i <= cuatrimestres ; i++) {
//            
//            for (int j = 0; j <=  this.planEstudio.getCuatrimestres().get(i).size(); j++) {
//            
//                if ( ! this.planEstudio.getCuatrimestres().get(i).get(j ).isOptativa() ){
//                    
//                }
//                
//            }
//            
//            
//            
//        }
//        
//        
//        
//        
//    }
    
    
}
