/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.universidad.vista;

import com.mycompany.universidad.Universidad;
import com.mycompany.universidad.modelo.Alumno;
import com.mycompany.universidad.modelo.HistoricoMateria;
import com.mycompany.universidad.modelo.Materia;
import com.mycompany.universidad.modelo.PlanEstudio;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jose
 */
public class PantallaMateriasAlumno extends javax.swing.JFrame {
    
    private Alumno alumno;
    
    
    /**
     * Creates new form PantallaDatosAlumno
     */
    public PantallaMateriasAlumno(Alumno alumno) {
        initComponents();
        setSize(950,800 );
        setLocation(500, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        this.alumno = alumno;
        
        cargarDatosAlumno(alumno);
        
        cargarMateriasPuedeCursar(alumno);
        
        
        
        setVisible(true);
        
    }
    
    private void inicializarListas(ArrayList< ArrayList<Materia> > materias ){
        
        //inicializo correctamente todos los semestres
        for (int i = 0; i <= 10; i++) {
            //agrego los cuatrimestres para el alumno de las materias cursadas
            //esto me facilita las consultas posteriores
           materias.add(new ArrayList<Materia>());
        }
    }
    
    private void completarTablaConMaterias(ArrayList< ArrayList<Materia> > materias){
        
        System.out.println("Entramos en el metodo para completar la tabla...");
        //recibo una lista de materias para mostrar
        for (int i = 0; i < materias.size(); i++) {
            
            for (int j = 0; j < materias.get(i).size(); j++) {
                
                
                String nombreMateria = materias.get(i).get(j).getNombre();
                    
                //seteo el nombre de la materia en la tabla
                this.jTablePosiblesMaterias.getModel().setValueAt(nombreMateria, j, i );
                
            }
        }
    }
    
    
    //metodo para la logica de como mostrar las materias
    private void cargarMateriasPuedeCursar(Alumno alumno){
        
        PlanEstudio planEstudio = alumno.getCarrera().getPlanEstudio();
        
        char tipoPlan = planEstudio.getTipoPlan();
        
        int nroCuatrimestre = 1;
        
        ArrayList< ArrayList<Materia> > materiasCandidatas = new ArrayList();
        
        inicializarListas(materiasCandidatas);
        
        if (alumno.getMateriasCursadas().get(1).isEmpty()){
            //significa que no curso nada todavia, puede haver cursado algunas 
            //muestro las materias del primer cuatrimestre de la carrera
            
            System.out.println("Mostramos las materias del primer cuatrimestre xq no curso nada");
            
            //materiasCandidatas.get(0) =  planEstudio.getCuatrimestres().get(1);
            //agrego todas las materias del 1er semestre
            materiasCandidatas.get(0).addAll(planEstudio.getCuatrimestres().get(1));
            
            
        }else{
            //verifico las materias que no curso
            
            for (int i = 1; i < planEstudio.getCuatrimestres().size() ; i++) {

                for (int j = 0; j < planEstudio.getCuatrimestres().get(i).size() ; j++) {

                    Materia materia = planEstudio.getCuatrimestres().get(i).get(j);

                    if ( ! yaCursoMateria(materia) ){

                        //significa que no curso esa materia
                        System.out.println("Se encontro una materia que no curso: "+ materia.getNombre());
                        
                        if (puedeCursarMateria(materia, alumno.getCarrera().getPlanEstudio().getTipoPlan() )){

                            materiasCandidatas.get(i-1).add(materia);
                        }
                        
                        //ahora filtro sobre las candidatas
                       // filtrarMaterias(materiasCandidatas);
                        
                        
                    }
                }
            }
        }
        //mandamos todas las materias
        completarTablaConMaterias(materiasCandidatas);
    }



    private boolean puedeCursarMateria(Materia materia, char tipoPlan){

        Materia correlativa = materia.getCorrelativaAnterior();
        
        //verifico que tenga correlativas
        if (correlativa != null){
            
            if (tipoPlan == 'A'){
                    
                if (alumno.obtenerHistoricoPorNombre(correlativa.getNombre()) != null  ) {
                    
                    //pregunto siaprobo la cursada de la correlativa
                    return alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).isRegular();
                }
            }
            
            if (tipoPlan == 'B'){
                
                if (alumno.obtenerHistoricoPorNombre(correlativa.getNombre()) != null  ) {

                    //pregunto si aprobo el final de la maeria
                    return alumno.obtenerHistoricoPorNombre(correlativa.getNombre()).aproboFinal();
                }
            }
            
            if (tipoPlan == 'C'){
                
                if (alumno.obtenerHistoricoPorNombre(correlativa.getNombre()) != null  ) {
                    
                    //si la materia no es del semetres 6, solo pregunto por la regularidad
                    if ( materia.getSemestre() <= 5 &&  alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).isRegular() ){
                        
                        return true;
                        
                    }else{
                    
                        //pregunto si aprobo la cursada de la correlativa y si aprobo los finales anteriorees
                        return ( alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).isRegular() && aproboFinales(5, materia.getSemestre()) );
                    }
                }
            }
            
            if (tipoPlan == 'D'){
                
                if (alumno.obtenerHistoricoPorNombre(correlativa.getNombre()) != null  ) {
                    
                    //si la materia no es del semetres 4, solo pregunpo por la regularidad
                    if ( materia.getSemestre() <= 3 &&  alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).isRegular() ){
                        
                        return true;
                        
                    }else{
                    
                        //pregunto siaprobo la cursada de la correlativa y si aprobo los finales anteriorees
                        return ( alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).isRegular() && aproboFinales(3, materia.getSemestre()) );
                    }
                }
            }
            
            if (tipoPlan == 'E'){
                
                if (alumno.obtenerHistoricoPorNombre(correlativa.getNombre()) != null  ) {
                    
                    //si la materia no es del semetres 4, solo pregunpo por si aprobo el final
                    if ( materia.getSemestre() <= 3 &&  alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).aproboFinal() ){
                        
                        return true;
                        
                    }else{
                    
                        //pregunto siaprobo la cursada de la correlativa y si aprobo los finales anteriorees
                        return ( alumno.obtenerHistoricoPorNombre(correlativa.getNombre() ).aproboFinal() && aproboFinales(3, materia.getSemestre()) );
                    }
                }
            }

        }else{
            //como no tiene correlativas, la puede cursar
            return true;
        }
        
        return false;
        
    }
    
    
                
//metodo para saber si aprobo cierta cantidad de materias previas        
private boolean aproboFinales(Integer cuatrimestresPrevios, Integer semestreInscripcion){
    
    PlanEstudio planEstudio = alumno.getCarrera().getPlanEstudio();

    //vble para saber si aprobo todas las materias
    boolean aproboTodas = true;

    //indices para saber cuales semestres consultar
    int indiceSemestreInicial = semestreInscripcion - cuatrimestresPrevios;

    int indiceSemestreFinal = semestreInscripcion -1;

    for (int i = indiceSemestreInicial  ; i <= indiceSemestreFinal ; i++) {

        //tengo que comparar la cantidad de materias cursadas y las del plan 
        ArrayList<HistoricoMateria> materiasYaCursadas = alumno.getMateriasCursadas().get(i);
        
        ArrayList<Materia> materiasPlanEstudio = alumno.getCarrera().getPlanEstudio().getCuatrimestres().get(i);

        if ( materiasYaCursadas.size() != materiasPlanEstudio.size() ){
            
            System.out.println("El alumno no curso la misma cantidad de materias que el plan , para el semestre "+ i);
            
            return false; //si no tiene la misma cantidad de materias en un cuatrimestre no sigo preguntando
            
            
        }else{
            
            // si tiene la misma cantidad, pregunto si aprobo el final de todas
            int indice = 0;
            
            while (aproboTodas && indice < materiasYaCursadas.size()){
                
                HistoricoMateria m = materiasYaCursadas.get(indice);

                if ( ! m.aproboFinal()){
                    
                    aproboTodas = false;
                    
                    return aproboTodas; // si no aprobo al menos una, devuelvo false
                }else{
                    indice++;
                }
            }
            
        }

    }
    
    return aproboTodas;
    
}       
        


    private boolean yaCursoMateria(Materia materia){
        
        //verifico si ya curso esa materia
        return ( this.alumno.obtenerHistoricoPorNombre(  materia.getNombre( ) ) != null ) ;
        
        
    }
    
    
    //metodo para mostrar las materias que ya curso el alumno
    private void cargarDatosAlumno(Alumno alumno){
        
        this.jLabel1NombreAlumno.setText("Alumno " + alumno.getNombre());
        
        this.jLabel1AlumnoInscriptoEn.setText("Inscripto en: "+ alumno.getCarrera().getNombre());
        
        this.jLabelTipoPlan.setText("Tipo de Plan: "+ alumno.getCarrera().getPlanEstudio().getTipoPlan());
        

        //pregunto si el alumno curso al menos una materia 
        if (alumno.getMateriasCursadas().get(1).isEmpty() ) {
        
            System.out.println("El alumno no curso ninguna materia");   
        }else{
            
            //recorro los semestres, columnas
            for (int i = 1; i < alumno.getMateriasCursadas().size() ; i++) {
                
                //recorro las materias de cada semestre, filas
                for (int j = 0; j < alumno.getMateriasCursadas().get(i).size(); j++) {
                    
                    
                    String nombreMateria = alumno.getMateriasCursadas().get(i).get(j).getMateria().getNombre();
                    
                    //System.out.println("VALOR DE I: "+ i);
                    //seteo el nombre de la materia en la tabla
                    this.jTableMateriasCursadas.getModel().setValueAt(nombreMateria, j, i-1 ); 
                    
                }
            }
        }        
            //jTableMateriasCursadas.getModel().setValueAt("hola", 3, 3);
    }
    
    private String obtenerMateriaDeTabla( JTable tabla ){
        
        int nroFila = tabla.getSelectedRow();
        //el nro de columna es el nro de cuatrimestre
        int nroColumna = tabla.getSelectedColumn();
        
        System.out.println("nro de fila: "+ nroFila + " ; nro col: "+ nroColumna);
        
        
        String materiaSeleccionada = (String) tabla.getModel().getValueAt(nroFila, nroColumna);
        
        System.out.println("Materia seleccionada: " + materiaSeleccionada);
        
        return materiaSeleccionada;
    }
    
    private void limpiarCelda(){
        
        int nroFila = this.jTablePosiblesMaterias.getSelectedRow();
        //el nro de columna es el nro de cuatrimestre
        int nroColumna = this.jTablePosiblesMaterias.getSelectedColumn();
        
        this.jTablePosiblesMaterias.setValueAt(null, nroFila, nroColumna);
    }
    
    
    private boolean verificarSeleccionCeldaEnTabla(JTable tabla){
        
        return (tabla.getSelectedRow() != -1) && (tabla.getSelectedRow() != -1);
        
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1NombreAlumno = new javax.swing.JLabel();
        jLabel1AlumnoInscriptoEn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMateriasCursadas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelMateriasPuedeCursar = new javax.swing.JLabel();
        Inscribir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePosiblesMaterias = new javax.swing.JTable();
        jButtonCargarNotas = new javax.swing.JButton();
        jButtonSituacionAlumno = new javax.swing.JButton();
        jLabelTipoPlan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1NombreAlumno.setText("jLabel1");

        jLabel1AlumnoInscriptoEn.setText("jLabel1");

        jTableMateriasCursadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Semestre 1", "Semestre 2", "Semestre 3", "Semestre 4", "Semestre 5", "Semestre 6", "Semestre 7", "Semestre 8", "Semestre 9", "Semestre 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMateriasCursadas.setColumnSelectionAllowed(true);
        jTableMateriasCursadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableMateriasCursadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableMateriasCursadas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableMateriasCursadas);
        jTableMateriasCursadas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableMateriasCursadas.getColumnModel().getColumnCount() > 0) {
            jTableMateriasCursadas.getColumnModel().getColumn(0).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(1).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(2).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(3).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(4).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(5).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(6).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(7).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(8).setResizable(false);
            jTableMateriasCursadas.getColumnModel().getColumn(9).setResizable(false);
        }

        jLabel1.setText("Materias Cursadas");

        jLabelMateriasPuedeCursar.setText("Materias que puede cursar");

        Inscribir.setText("Inscribir");
        Inscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InscribirActionPerformed(evt);
            }
        });

        jTablePosiblesMaterias.setAutoCreateRowSorter(true);
        jTablePosiblesMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Semestre 1", "Semestre 2", "Semestre 3", "Semestre 4", "Semestre 5", "Semestre 6", "Semestre 7", "Semestre 8", "Semestre 9", "Semestre 10"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePosiblesMaterias.setColumnSelectionAllowed(true);
        jTablePosiblesMaterias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePosiblesMaterias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePosiblesMaterias.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTablePosiblesMaterias);
        jTablePosiblesMaterias.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTablePosiblesMaterias.getColumnModel().getColumnCount() > 0) {
            jTablePosiblesMaterias.getColumnModel().getColumn(0).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(0).setHeaderValue("Semestre 1");
            jTablePosiblesMaterias.getColumnModel().getColumn(1).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(1).setHeaderValue("Semestre 2");
            jTablePosiblesMaterias.getColumnModel().getColumn(2).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(2).setHeaderValue("Semestre 3");
            jTablePosiblesMaterias.getColumnModel().getColumn(3).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(3).setHeaderValue("Semestre 4");
            jTablePosiblesMaterias.getColumnModel().getColumn(4).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(4).setHeaderValue("Semestre 5");
            jTablePosiblesMaterias.getColumnModel().getColumn(5).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(5).setHeaderValue("Semestre 6");
            jTablePosiblesMaterias.getColumnModel().getColumn(6).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(6).setHeaderValue("Semestre 7");
            jTablePosiblesMaterias.getColumnModel().getColumn(7).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(7).setHeaderValue("Semestre 8");
            jTablePosiblesMaterias.getColumnModel().getColumn(8).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(8).setHeaderValue("Semestre 9");
            jTablePosiblesMaterias.getColumnModel().getColumn(9).setResizable(false);
            jTablePosiblesMaterias.getColumnModel().getColumn(9).setHeaderValue("Semestre 10");
        }
        jTablePosiblesMaterias.getAccessibleContext().setAccessibleParent(jPanel2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonCargarNotas.setText("Cargar notas");
        jButtonCargarNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarNotasActionPerformed(evt);
            }
        });

        jButtonSituacionAlumno.setText("Ver situacion");
        jButtonSituacionAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSituacionAlumnoActionPerformed(evt);
            }
        });

        jLabelTipoPlan.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(391, 391, 391)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(393, 393, 393)
                                .addComponent(jLabelMateriasPuedeCursar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(Inscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jButtonCargarNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButtonSituacionAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1NombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1AlumnoInscriptoEn, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelTipoPlan))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1NombreAlumno)
                .addGap(18, 18, 18)
                .addComponent(jLabel1AlumnoInscriptoEn)
                .addGap(18, 18, 18)
                .addComponent(jLabelTipoPlan)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabelMateriasPuedeCursar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Inscribir)
                    .addComponent(jButtonCargarNotas)
                    .addComponent(jButtonSituacionAlumno))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void InscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InscribirActionPerformed
        
        //le paso la tabla correspondiente
        String materiaSeleccionada = obtenerMateriaDeTabla(this.jTablePosiblesMaterias);
        
        Universidad.inscribirEnMateria(materiaSeleccionada, this.alumno);
        
        
        this.cargarDatosAlumno(this.alumno);
        
        //limpio la celda seleccionada
        limpiarCelda();
        
    }//GEN-LAST:event_InscribirActionPerformed

    private void jButtonCargarNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarNotasActionPerformed
        // TODO add your handling code here:
        
        if (! alumno.getMateriasCursadas().get(1).isEmpty()){


            //si no tiene materias del 1er cuatrimestre no habilito el boton
            //JOptionPane.showMessageDialog(rootPane, "No hay materias cursando");


            if (verificarSeleccionCeldaEnTabla(this.jTableMateriasCursadas)){

                //le paso la tabla correspondiente, las materias ya cursadas
                String materiaSeleccionada = obtenerMateriaDeTabla(this.jTableMateriasCursadas);

                HistoricoMateria historicoMateria = alumno.obtenerHistoricoPorNombre(materiaSeleccionada);

                PantallaCargaNotas pantallaCargaNotas = new PantallaCargaNotas(historicoMateria, this.alumno);
                
                

            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione una de las materias cursadas", "Aviso", 2);
            }

        }else{
            JOptionPane.showMessageDialog(rootPane, "No hay materias cursando", "Aviso", 2);
        }
        
    }//GEN-LAST:event_jButtonCargarNotasActionPerformed

    private void jButtonSituacionAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSituacionAlumnoActionPerformed
        // TODO add your handling code here:
        
        int cantMateriasAprobadas = alumno.getCursadasRegAprobadas();
        
        int cantMateriasOptativasAprobadas = alumno.getCursadasOptativasAprobadas();
        
        int cantMateriasObligatorias = alumno.getCarrera().getPlanEstudio().getCantMateriasObligatorias();
        
        int cantMateriasOptativas = alumno.getCarrera().getMateriasOptativas();
        
        JOptionPane.showMessageDialog(rootPane, "Materias obligatorias aprobadas " + cantMateriasAprobadas+ "/"+ cantMateriasObligatorias , "Aviso", 1);
        
        JOptionPane.showMessageDialog(rootPane, "Materias optativas aprobadas " + cantMateriasOptativasAprobadas + "/"+ cantMateriasOptativas , "Aviso", 1);
        
    }//GEN-LAST:event_jButtonSituacionAlumnoActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PantallaDatosAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PantallaDatosAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PantallaDatosAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PantallaDatosAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PantallaDatosAlumno(null).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Inscribir;
    private javax.swing.JButton jButtonCargarNotas;
    private javax.swing.JButton jButtonSituacionAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1AlumnoInscriptoEn;
    private javax.swing.JLabel jLabel1NombreAlumno;
    private javax.swing.JLabel jLabelMateriasPuedeCursar;
    private javax.swing.JLabel jLabelTipoPlan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMateriasCursadas;
    private javax.swing.JTable jTablePosiblesMaterias;
    // End of variables declaration//GEN-END:variables
}
