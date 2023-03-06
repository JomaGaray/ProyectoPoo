/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.universidad;

import com.mycompany.universidad.vista.VentanaPrincipal;
import com.mycompany.universidad.modelo.Carrera;
import com.mycompany.universidad.modelo.Alumno;
import com.mycompany.universidad.modelo.HistoricoMateria;
import com.mycompany.universidad.modelo.Materia;
import com.mycompany.universidad.modelo.PlanEstudio;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Universidad {
    
    private static ArrayList<Carrera> carrerasDisponibles;
    private static ArrayList<Alumno> alumnos;
    
    
//    public static void main(String[] args) {
//        
//        carrerasDisponibles = new ArrayList();
//        alumnos = new ArrayList();
//        
//        cargarDatosPrueba();
//        
//        VentanaPrincipal ventana = new VentanaPrincipal();
//        
//    }
    
    public static void main(String[] args) {
        carrerasDisponibles = new ArrayList();
        alumnos = new ArrayList();
        
        cargarDatosPrueba();
        
        VentanaPrincipal ventana = new VentanaPrincipal();
    }
    
    public static void agregarCarrera(Carrera carrera){

        carrerasDisponibles.add(carrera );
    }
    
    public static void inscribirAlumno(int indiceCarrera, String nombreAlumno, Integer docAlumno){
        
        
        Carrera carrera = carrerasDisponibles.get(indiceCarrera);
        
        System.out.println("indice: "+ indiceCarrera + " carrera: "+ carrera.getNombre());
        
        
        Alumno alumno = new Alumno(docAlumno, nombreAlumno, carrera);
        
        alumnos.add(alumno);
        
        
        System.out.println("Alumno: "+ alumno.getNombre() + " agregado a la carrera: "+ carrera.getNombre());
        
    }
    
    public static void inscribirEnMateria(String nombreMateria, Alumno alumno){
        
        Materia materia = alumno.getCarrera().getPlanEstudio().obtenerMateriaPorNombre(nombreMateria);
        
        
        //creo una nueva instancia, lo inscribo;
        HistoricoMateria historicoMateria= new HistoricoMateria(alumno, materia);
        
        
        alumno.inscribirEnCursada(materia.getSemestre(), historicoMateria);
        
        System.out.println("Se inscribio correctamente al "+ alumno.getNombre() + " en la materia : "+ materia.getNombre());
        
        
        
        
        
    }
    
//    private Materia obtenerMateriaPorNombre ( PlanEstudio plan, String nombreMateria){
//        
//        
//        
//        for (int i = 0; i < plan.getCuatrimestres().size(); i++) {
//            
//            for (int j = 0; j < plan.getCuatrimestres().get(i).size() ; j++) {
//                
//                Materia m  = plan.getCuatrimestres().get(i).get(j);
//                
//                if (m.getNombre().equalsIgnoreCase(nombreMateria)) {
//                    
//                    return m; //retorno la materia 
//                    
//                }
//                
//                
//            }
//            
//        }
//        
//        return null;
//    }
    private static Carrera cargarCarreraDePrueba( String nombreCarrera, char tipoPlan){
        
        System.out.println("Cargando datos de la nueva carrera...");
  
        Carrera carrera = new Carrera(nombreCarrera + "1", nombreCarrera , 3 );
        
        PlanEstudio plan = new PlanEstudio(tipoPlan);
        
        carrera.setPlanEstudio(plan);
        
//        Materia m1 = new Materia ("m1", "Algorimica", 1, false);
//        Materia m2 = new Materia ("m2", "Elementos", 1, false);
//        Materia m3 = new Materia ("m3", "Objetos", 1, false);
        
        int semestre = 1;
        
        Materia nuevaMateria = new Materia ("m"+ 1, "Materia " + 1, semestre, true, false);
       
        plan.agregarMateria(nuevaMateria);

        
        for (int nroMateria = 2; nroMateria <= 10 ; nroMateria++) { // 1 materia x semestre, 10 en total

            semestre ++;
           
            Materia correlativa = nuevaMateria;
                
            nuevaMateria = new Materia ("m"+ nroMateria, "Materia " + nroMateria, semestre, true, false);
            
            nuevaMateria.setCorrelativaAnterior(correlativa);
            
            plan.agregarMateria(nuevaMateria);
        }
        
        carrerasDisponibles.add(carrera);
        
        
        return carrera;
        
//        Alumno alumno = new Alumno(20740150, "Agustin Dominguez", carrera); 
//        alumnos.add(alumno);
        
        
        
    }
    
    private static void cargarDatosPrueba(){
        
       Carrera sistemas = cargarCarreraDePrueba("Lic Sistemas", 'A');
       Carrera biologia = cargarCarreraDePrueba("Lic Biologia", 'B');
       Carrera medios = cargarCarreraDePrueba("Lic Audiovisual", 'C');
       Carrera geologia = cargarCarreraDePrueba("Lic Geologia", 'D');
       Carrera sociologia = cargarCarreraDePrueba("Lic Sociologia", 'E');
        
       Alumno alumno = new Alumno(20740150, "Agustin Dominguez", sistemas); 
       Alumno alumno2 = new Alumno(20740150, "Jose Garay", biologia); 
       Alumno alumno3 = new Alumno(20740150, "Pepe Sand", medios); 
       Alumno alumno4 = new Alumno(20740150, "Mariana Martinez", geologia); 
       Alumno alumno5 = new Alumno(20740150, "Lucia Gutierrez", sociologia); 
       
       
       alumnos.add(alumno);
       alumnos.add(alumno2);
       alumnos.add(alumno3);
       alumnos.add(alumno4);
       alumnos.add(alumno5);
       
       
    }
    

    public static ArrayList<Carrera> getCarrerasDisponibles() {
        return carrerasDisponibles;
    }

    public static void setCarrerasDisponibles(ArrayList<Carrera> carrerasDisponibles) {
        Universidad.carrerasDisponibles = carrerasDisponibles;
    }

    public static ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public static void setAlumnos(ArrayList<Alumno> alumnos) {
        Universidad.alumnos = alumnos;
    }
    
    
    
    
    
    
}
