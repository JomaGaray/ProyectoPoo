/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universidad.modelo;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Alumno {
    
    private Integer documento;
    private String nombre;
    private ArrayList <ArrayList <HistoricoMateria>> materiasCursadas ; //10 cuatrimestres de historicos de materias
    private Carrera carrera;
    private Integer cursadasObligatoriasAprobadas; //cursadas regulares
    private Integer cursadasOptativasAprobadas;
   // private Integer finalesAprob;

     
    
    
    public Alumno(Integer documento, String nombre, Carrera carrera) {
        this.documento = documento;
        this.nombre = nombre;
        this.carrera = carrera;
        
        
        cursadasObligatoriasAprobadas = 0;
        cursadasOptativasAprobadas = 0;
        //finalesAprob = 0;
        
        //creo una lista de materias cursadas, vacia
        this.materiasCursadas = new ArrayList();
        
        this.inicializarMateriasCursadas();
    }

    @Override
    public String toString() {
        return  nombre + " ; documento: " + documento + " ; carrera: " + carrera.getNombre();
    }
    
    
    private void inicializarMateriasCursadas(){
        
        //inicializo correctamente todos los semestres
        for (int i = 0; i <= 10; i++) {
            //agrego los cuatrimestres para el alumno de las materias cursadas
            //esto me facilita las consultas posteriores
           this.materiasCursadas.add(new ArrayList<HistoricoMateria>());
        }
    }
    
    
    public void inscribirEnCursada(Integer semestre, HistoricoMateria nuevaCursada){
        
        //lo agrego al semestre correspondiente
        this.materiasCursadas.get(semestre).add(nuevaCursada);
        
    }
    
    
    public boolean materiaRegular(HistoricoMateria historico){
        
        return (historico.isRegular());
        
    }
    
    
    
    public HistoricoMateria obtenerHistoricoPorNombre( String nombreMateria){
        
        
        for (int i = 0; i < materiasCursadas.size(); i++) {
            
            for (int j = 0; j < materiasCursadas.get(i).size() ; j++) {
                
                HistoricoMateria historico  = materiasCursadas.get(i).get(j);
                
                if (historico.getMateria().getNombre().equalsIgnoreCase(nombreMateria)) {
                    
                    System.out.println("se encontro el historico de: " + historico.getMateria().getNombre());
                    return historico; //retorno la materia 
                    
                }else{
                    System.out.println("no se encontro el historico de "+ nombreMateria);
                }
                
                
            }
            
        }
        
        return null;
    }
    
    
    public void actualizarHistorico( HistoricoMateria nuevoHistorico, int semestre, String nombreMateria){
        
         HistoricoMateria historico;
        
        for (int j = 0; j < materiasCursadas.get(semestre).size() ; j++) {
                
                historico  = materiasCursadas.get(semestre).get(j);
                
                if (historico.getMateria().getNombre().equalsIgnoreCase(nombreMateria)) {
                    
                    historico = nuevoHistorico; //le asigno los nuevos valores
                    
                    historico.actualizarCondicion();
                    
                    //actualizo la cantidad de finales que aprobo
                    if (historico.aproboFinal()){
                        
                        if (! historico.getMateria().isOptativa()){
                            
                            cursadasObligatoriasAprobadas++;
                            
                        }else{
                            cursadasOptativasAprobadas++;
                        }
                        
                    }
                    
                    System.out.println("se actualizo el historico de: " + historico.getMateria().getNombre() + 
                                        " ; nota final: "+ historico.getNotaFinal());
                }
        }
    }
    

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList <ArrayList <HistoricoMateria>> getMateriasCursadas() {
        return materiasCursadas;
    }

    public void setMateriasCursadas(ArrayList <ArrayList <HistoricoMateria>> materiasCursadas) {
        this.materiasCursadas = materiasCursadas;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Integer getCursadasRegAprobadas() {
        return cursadasObligatoriasAprobadas;
    }

    
    public void setCursadasObligatoriasAprobadas(Integer cursadasObligatoriasAprobadas) {
        this.cursadasObligatoriasAprobadas = cursadasObligatoriasAprobadas;
    }
//
//    public Integer getFinalesAprob() {
//        return finalesAprob;
//    }
//
//    public void setFinalesAprob(Integer finalesAprob) {
//        this.finalesAprob = finalesAprob;
//    }

    public Integer getCursadasOptativasAprobadas() {
        return cursadasOptativasAprobadas;
    }

    public void setCursadasOptativasAprobadas(Integer cursadasOptativasAprobadas) {
        this.cursadasOptativasAprobadas = cursadasOptativasAprobadas;
    }
    
    
       
    
}
