/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.Conductor;
import Vista.DashboardConductor;
import Vista.DashboardPasajero;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.time.format.DateTimeFormatter;
import java.sql.Statement;


/**
 *
 * @author Mihae
 */
public class ConductorPerfilVehiculo extends javax.swing.JPanel {

    /**
     * Creates new form PerfilAdmin
     */
    
    private Conductor conductor;
    private Statement st;

    public ConductorPerfilVehiculo(Conductor conductor, Statement st) {
        this.conductor = conductor;
        this.st = st;
        initComponents();
        mostrarDatosConductor();
    }
    
    private void mostrarDatosConductor() {

    actual_placa_vehiculo.setText(conductor.get_placa_vehiculo());
    actual_modelo_vehiculo.setText (conductor.get_modelo_vehiculo());
    actual_capacidad_vehiculo.setText(String.valueOf(conductor.get_capacidad_vehiculo()));
    
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
        modelo_vehiculo = new javax.swing.JLabel();
        Placa_Vehiculo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        editar_perfil_button = new javax.swing.JButton();
        capacidad_vehiculo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        foto_label = new javax.swing.JLabel();
        actual_modelo_vehiculo = new javax.swing.JLabel();
        actual_placa_vehiculo = new javax.swing.JLabel();
        actual_capacidad_vehiculo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1010, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1010, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1010, 580));

        modelo_vehiculo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        modelo_vehiculo.setForeground(new java.awt.Color(20, 20, 20));
        modelo_vehiculo.setText("Modelo del Vehiculo:");

        Placa_Vehiculo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Placa_Vehiculo.setForeground(new java.awt.Color(20, 20, 20));
        Placa_Vehiculo.setText("Placa del Vehiculo:");

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(20, 20, 20));
        jLabel1.setText("DATOS DEL VEHICULO");

        editar_perfil_button.setBackground(new java.awt.Color(41, 82, 85));
        editar_perfil_button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editar_perfil_button.setForeground(new java.awt.Color(240, 245, 247));
        editar_perfil_button.setText("Editar Vehiculo");
        editar_perfil_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_perfil_buttonActionPerformed(evt);
            }
        });

        capacidad_vehiculo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        capacidad_vehiculo.setForeground(new java.awt.Color(20, 20, 20));
        capacidad_vehiculo.setText("Capacidad del Vehiculo:");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        foto_label.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        foto_label.setForeground(new java.awt.Color(20, 20, 20));
        foto_label.setText("FOTO DE VEHICULO");

        actual_modelo_vehiculo.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_placa_vehiculo.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_capacidad_vehiculo.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(foto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(modelo_vehiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_modelo_vehiculo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Placa_Vehiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_placa_vehiculo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(capacidad_vehiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_capacidad_vehiculo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addComponent(editar_perfil_button)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 152, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelo_vehiculo)
                    .addComponent(actual_modelo_vehiculo))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Placa_Vehiculo)
                    .addComponent(actual_placa_vehiculo))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capacidad_vehiculo)
                    .addComponent(actual_capacidad_vehiculo))
                .addGap(250, 250, 250)
                .addComponent(editar_perfil_button)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(foto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editar_perfil_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_perfil_buttonActionPerformed
        ((DashboardConductor) SwingUtilities.getWindowAncestor(this)).ShowJPanel(new ConductorVehiculoEditar(conductor, st));
    }//GEN-LAST:event_editar_perfil_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Placa_Vehiculo;
    private javax.swing.JLabel actual_capacidad_vehiculo;
    private javax.swing.JLabel actual_modelo_vehiculo;
    private javax.swing.JLabel actual_placa_vehiculo;
    private javax.swing.JLabel capacidad_vehiculo;
    private javax.swing.JButton editar_perfil_button;
    private javax.swing.JLabel foto_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel modelo_vehiculo;
    // End of variables declaration//GEN-END:variables
}