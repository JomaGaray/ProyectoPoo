/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.universidad.vista;

import javax.swing.JFrame;

import com.mycompany.universidad.*;
import com.mycompany.universidad.modelo.Alumno;
import com.mycompany.universidad.modelo.Carrera;
import com.mycompany.universidad.modelo.PlanEstudio;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    
    
    //private ArrayList<Carrera> carreras;
    
    /**
     * Creates new form Ventana
     */
    public VentanaPrincipal() {
        
        initComponents();
        setSize(700,700 );
        setLocation(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        
        
        
        setVisible(true);
        
        cargarCarrerasEnListado(Universidad.getCarrerasDisponibles());
        cargarAlumnosEnListado(Universidad.getAlumnos());
    }
    
//    public void setCarreras(ArrayList<Carrera> carreras){
//        this.carreras = carreras;
//    }
    
    public void cargarCarrerasEnListado(ArrayList<Carrera> carreras){
        
        DefaultListModel modelo = new DefaultListModel();
        
        
        if (!carreras.isEmpty()){

            //tengo que publicar en la lista las carreras disponibls
            for (int i = 0; i < carreras.size() ; i++) {

                modelo.addElement(carreras.get(i).toString());    

            }
            
        }else{
            modelo.addElement(" No hay carreras agregadas");
        }
        
        jListCarreras.setModel(modelo);
        
    }
    
    private void cargarAlumnosEnListado(ArrayList<Alumno> alumnos){
        
        DefaultListModel modelo = new DefaultListModel();
        
        
        if (!alumnos.isEmpty()){

            //tengo que publicar en la lista las carreras disponibls
            for (int i = 0; i < alumnos.size() ; i++) {

                modelo.addElement(alumnos.get(i).toString());    

            }
            
        }else{
            modelo.addElement(" No hay alumnos agregadas");
        }
        
        jList1AlumnosCargados.setModel(modelo);
        
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
        jLabel1 = new javax.swing.JLabel();
        jButtonCargarAlumno = new javax.swing.JButton();
        jButtonCargarCarrera = new javax.swing.JButton();
        jButton3HacerConsultas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCarreras = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1AlumnosCargados = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButtonRefrescar = new javax.swing.JButton();
        jButtonVisualizarCarrera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Administrador Facultad");

        jButtonCargarAlumno.setText("Inscribir Alumno");
        jButtonCargarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarAlumnoActionPerformed(evt);
            }
        });

        jButtonCargarCarrera.setText("Cargar Carrera");
        jButtonCargarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarCarreraActionPerformed(evt);
            }
        });

        jButton3HacerConsultas.setText("Hacer Consultas");
        jButton3HacerConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3HacerConsultasActionPerformed(evt);
            }
        });

        jListCarreras.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListCarreras);

        jLabel2.setText("Carreras Agregadas");

        jList1AlumnosCargados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1AlumnosCargados);

        jLabel3.setText("Alumnos Cargados");

        jButtonRefrescar.setText("Refrescar");
        jButtonRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefrescarActionPerformed(evt);
            }
        });

        jButtonVisualizarCarrera.setText("Ver carrera");
        jButtonVisualizarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualizarCarreraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jButtonCargarAlumno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jButtonCargarCarrera)
                .addGap(185, 185, 185))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButtonRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton3HacerConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jButtonVisualizarCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(292, 292, 292)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(123, 123, 123)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                                .addComponent(jScrollPane2)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCargarAlumno)
                    .addComponent(jButtonCargarCarrera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3HacerConsultas)
                    .addComponent(jButtonRefrescar)
                    .addComponent(jButtonVisualizarCarrera))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarAlumnoActionPerformed
        // TODO add your handling code here:
        
        if  (!Universidad.getCarrerasDisponibles().isEmpty()){
        
            PantallaAlumno pantallaAlumno = new PantallaAlumno(Universidad.getCarrerasDisponibles());
        
        }else{
            JOptionPane.showMessageDialog(rootPane, "No hay carreras Disponibles");
        }
        
        this.cargarAlumnosEnListado(Universidad.getAlumnos());
        
    }//GEN-LAST:event_jButtonCargarAlumnoActionPerformed

    private void jButtonCargarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarCarreraActionPerformed
        
        PantallaCarrera  pantallaCarrera = new PantallaCarrera();
        
        this.cargarCarrerasEnListado(Universidad.getCarrerasDisponibles());
        
        //this.jListCarreras.updateUI();
        
    }//GEN-LAST:event_jButtonCargarCarreraActionPerformed

    private void jButton3HacerConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3HacerConsultasActionPerformed
        // TODO add your handling code here:
        
        if (!Universidad.getAlumnos().isEmpty()){
            
            //pregunto si se selecciono algun alumno
            if (! this.jList1AlumnosCargados.isSelectionEmpty()){
                
                //obtengo el alumno correspondiente para hacer las consultas
                Alumno alumno = Universidad.getAlumnos().get(this.jList1AlumnosCargados.getSelectedIndex());


                //ahora tengo que cargar la pantalla con los datos del alumno
                //materias cursadas, aprobadas, materias que puede cursar
                //tengo que mandar el historioMaterias, plan de estudio y el alumno

                new PantallaMateriasAlumno(alumno);
                
                
            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione algun alumno", "Aviso", 2);
            }
            
            
        }else{
            
            //JOptionPane.showMessageDialog(rootPane, "No hay alumnos cargados");
            JOptionPane.showMessageDialog(rootPane, "No hay alumnos cargados", "Aviso", 2);
        }
    }//GEN-LAST:event_jButton3HacerConsultasActionPerformed

    private void jButtonRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefrescarActionPerformed
        
         this.cargarAlumnosEnListado(Universidad.getAlumnos());
         
         this.cargarCarrerasEnListado(Universidad.getCarrerasDisponibles());
        
    }//GEN-LAST:event_jButtonRefrescarActionPerformed

    private void jButtonVisualizarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualizarCarreraActionPerformed
        
        if (! this.jListCarreras.isSelectionEmpty()){
            
            int indiceCarrera = this.jListCarreras.getSelectedIndex();

            System.out.println("indice seleccionado de carrera: "+ indiceCarrera);

            PlanEstudio plan = Universidad.getCarrerasDisponibles().get(indiceCarrera).getPlanEstudio();

            PantallaPlanCarrera pantalla = new PantallaPlanCarrera(plan, Universidad.getCarrerasDisponibles().get(indiceCarrera).getNombre(), Universidad.getCarrerasDisponibles().get(indiceCarrera).getMateriasOptativas() );

            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "Seleccione alguna carrera", "Aviso", 2);
            
        }
        
        
    }//GEN-LAST:event_jButtonVisualizarCarreraActionPerformed

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
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentanaPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3HacerConsultas;
    private javax.swing.JButton jButtonCargarAlumno;
    private javax.swing.JButton jButtonCargarCarrera;
    private javax.swing.JButton jButtonRefrescar;
    private javax.swing.JButton jButtonVisualizarCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1AlumnosCargados;
    private javax.swing.JList<String> jListCarreras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
