/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universidad.modelo;

/**
 *
 * @author jose
 */
public class HistoricoMateria {
    
    private Alumno alumno;
    private Materia materia;
    private Integer parcial1;
    private Integer parcial2;

    private Integer rec1;
    private Integer rec2;
    private Integer notaFinal;
    private Integer teorico;
    private boolean regular;
    private boolean promocion;

    public HistoricoMateria(Alumno alumno, Materia materia) {
        this.alumno = alumno;
        this.materia = materia;
        this.regular = false;
    }
    
    
    public void actualizarCondicion(){
        
        if (parcial1>=6 || rec1 >=6 ){
            
            this.regular = true;
            System.out.println("El alumno debe rendir un final");
        }
        
        if (promocion ){
            
            if (parcial1>=8 && teorico >=7 && rec1 == null){
                
                this.regular =true;
                
                this.notaFinal= (parcial1 + teorico )/ 2;
                
                System.out.println("El alumno promociono la materia");
            }
        }
    }
    
    public boolean aproboFinal(){
        
        if (this.getNotaFinal() != null){
        
            if (this.getNotaFinal()>= 6){
                return true;
            }
        }
        
        return false;
        
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Integer getParcial1() {
        return parcial1;
    }

    public void setParcial1(Integer parcial1) {
        this.parcial1 = parcial1;
    }

    public Integer getParcial2() {
        return parcial2;
    }

    public void setParcial2(Integer parcial2) {
        this.parcial2 = parcial2;
    }

    public Integer getRec1() {
        return rec1;
    }

    public void setRec1(Integer rec1) {
        this.rec1 = rec1;
    }

    public Integer getRec2() {
        return rec2;
    }

    public void setRec2(Integer rec2) {
        this.rec2 = rec2;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Integer getTeorico() {
        return teorico;
    }

    public void setTeorico(Integer teorico) {
        this.teorico = teorico;
    }

        public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public boolean isPromocion() {
        return promocion;
    }

    public void setPromocion(boolean promocion) {
        this.promocion = promocion;
    }
    
    
    
}
