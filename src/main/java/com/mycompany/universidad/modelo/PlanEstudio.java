/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universidad.modelo;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class PlanEstudio {
    
//    private ArrayList<Materia>  materiasObligatorias;
//    private ArrayList<Materia>  materiasOptativas;
    
    private char tipoPlan;
    private Integer cantMateriasObligatorias;
    private Integer cantMateriasOptativas;

    
    //lista de listas para cada cuatrimestre con sus materias
    private ArrayList<ArrayList<Materia>> cuatrimestres;
    
    
    
    public PlanEstudio(char tipoPlan) {
        
        this.tipoPlan = tipoPlan;
        this.cantMateriasObligatorias = 0;
        this.cantMateriasOptativas = 0;
        
        this.cuatrimestres = new ArrayList<>();
        
//        this.materiasObligatorias = new ArrayList(11);
//        this.materiasOptativas = new ArrayList(11);
        
        for(int i = 0; i <= 10; i++) {
            
            //agrego 10 cuatrimestres 
            cuatrimestres.add( new ArrayList<Materia>() );
        }
        
        System.out.println("cantidad de listas: "+ cuatrimestres.size() );
        
    }
    
    
    public Materia obtenerMateriaPorNombre( String nombreMateria){
        
        for (int i = 0; i < this.getCuatrimestres().size(); i++) {
            
            for (int j = 0; j < this.getCuatrimestres().get(i).size() ; j++) {
                
                Materia m  = this.getCuatrimestres().get(i).get(j);
                
                if (m.getNombre().equalsIgnoreCase(nombreMateria)) {
                    
                    return m; //retorno la materia 
                    
                }
                
                
            }
            
        }
        
        return null;
    }
    
    
    public void agregarMateria(Materia materia){
        
        if (materia.isOptativa() ){
            this.cantMateriasOptativas++;
        }else{
            this.cantMateriasObligatorias++;
        }
        
        //agrego la materia al cuatrimestre correspondiente
        this.cuatrimestres.get(materia.getSemestre()).add(materia);
        
        //obtengo la lista de materias de un cuatrimestre
        ArrayList<Materia> materias = this.cuatrimestres.get(materia.getSemestre());
        
        System.out.println("materias en el cuatrimestre "+ materia.getSemestre());
        
        for(Materia m: materias){
            System.out.println(m.getNombre());
            
        }
        
    }

    public ArrayList<ArrayList<Materia>> getCuatrimestres() {
        return cuatrimestres;
    }

    
    
    
    public Integer getCantMateriasObligatorias() {
        return cantMateriasObligatorias;
    }

    public void setCantMateriasObligatorias(Integer cantMateriasObligatorias) {
        this.cantMateriasObligatorias = cantMateriasObligatorias;
    }

    public Integer getCantMateriasOptativas() {
        return cantMateriasOptativas;
    }

//    public ArrayList<Materia> getMateriasObligatorias() {
//        return materiasObligatorias;
//    }
//
//    public void setMateriasObligatorias(ArrayList<Materia> materiasObligatorias) {
//        this.materiasObligatorias = materiasObligatorias;
//    }
//    public ArrayList<Materia> getMateriasOptativas() {
//        return materiasOptativas;
//    }
//
//    public void setMateriasOptativas(ArrayList<Materia> materiasOptativas) {
//        this.materiasOptativas = materiasOptativas;
//    }
    public void setCantMateriasOptativas(Integer cantMateriasOptativas) {
        this.cantMateriasOptativas = cantMateriasOptativas;
    }

    public char getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(char tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    
    
    
}
