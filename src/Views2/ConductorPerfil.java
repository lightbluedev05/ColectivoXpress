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
public class ConductorPerfil extends javax.swing.JPanel {

    /**
     * Creates new form PerfilAdmin
     */
    
    private Conductor conductor;
    private Statement st;

    public ConductorPerfil(Conductor conductor, Statement st) {
        this.conductor = conductor;
        this.st = st;
        initComponents();
        mostrarDatosConductor();
    }
    
    private void mostrarDatosConductor() {
    actual_nombre_label2.setText(conductor.get_nombre());
    actual_correo_label3.setText(conductor.get_correo());
    actual_dni_label5.setText(conductor.get_dni());
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        actual_fecha_label4.setText(conductor.get_fecha_nacimiento().format(formatter));
        
    actual_fecha_label4.setText(conductor.get_fecha_nacimiento().toString()); 
    actual_distrito_label8.setText(conductor.get_distrito());
    actual_provincia_label6.setText(conductor.get_provincia());
    actual_departamento_label7.setText(conductor.get_departamento());
    actual_dias_descanso_label.setText(conductor.get_dias_descanso());
    
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
        editar_perfil_button = new javax.swing.JButton();
        nombre_label1 = new javax.swing.JLabel();
        correo_label = new javax.swing.JLabel();
        dni_label = new javax.swing.JLabel();
        fecha_label1 = new javax.swing.JLabel();
        distrito_label3 = new javax.swing.JLabel();
        provincia_label4 = new javax.swing.JLabel();
        departamento_label2 = new javax.swing.JLabel();
        dias_descanso_label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        foto_label = new javax.swing.JLabel();
        actual_nombre_label2 = new javax.swing.JLabel();
        actual_correo_label3 = new javax.swing.JLabel();
        actual_dni_label5 = new javax.swing.JLabel();
        actual_fecha_label4 = new javax.swing.JLabel();
        actual_distrito_label8 = new javax.swing.JLabel();
        actual_provincia_label6 = new javax.swing.JLabel();
        actual_departamento_label7 = new javax.swing.JLabel();
        actual_dias_descanso_label = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1010, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1010, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(20, 20, 20));
        jLabel1.setText("DATOS DEL CONDUCTOR");

        editar_perfil_button.setBackground(new java.awt.Color(41, 82, 85));
        editar_perfil_button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        editar_perfil_button.setForeground(new java.awt.Color(240, 245, 247));
        editar_perfil_button.setText("Editar Perfil");
        editar_perfil_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_perfil_buttonActionPerformed(evt);
            }
        });

        nombre_label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nombre_label1.setForeground(new java.awt.Color(20, 20, 20));
        nombre_label1.setText("Nombre: ");

        correo_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        correo_label.setForeground(new java.awt.Color(20, 20, 20));
        correo_label.setText("Correo:");

        dni_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dni_label.setForeground(new java.awt.Color(20, 20, 20));
        dni_label.setText("DNI:");

        fecha_label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        fecha_label1.setForeground(new java.awt.Color(20, 20, 20));
        fecha_label1.setText("Fecha de Nacimiento: ");

        distrito_label3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        distrito_label3.setForeground(new java.awt.Color(20, 20, 20));
        distrito_label3.setText("Distrito:");

        provincia_label4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        provincia_label4.setForeground(new java.awt.Color(20, 20, 20));
        provincia_label4.setText("Provincia:");

        departamento_label2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        departamento_label2.setForeground(new java.awt.Color(20, 20, 20));
        departamento_label2.setText("Departamento:");

        dias_descanso_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dias_descanso_label.setForeground(new java.awt.Color(20, 20, 20));
        dias_descanso_label.setText("Dias de Descanso:");

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
        foto_label.setText("FOTO DE PERFIL");

        actual_nombre_label2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_nombre_label2.setForeground(new java.awt.Color(20, 20, 20));

        actual_correo_label3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_correo_label3.setForeground(new java.awt.Color(20, 20, 20));

        actual_dni_label5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_dni_label5.setForeground(new java.awt.Color(20, 20, 20));

        actual_fecha_label4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_fecha_label4.setForeground(new java.awt.Color(20, 20, 20));

        actual_distrito_label8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_distrito_label8.setForeground(new java.awt.Color(20, 20, 20));

        actual_provincia_label6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_provincia_label6.setForeground(new java.awt.Color(20, 20, 20));

        actual_departamento_label7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        actual_departamento_label7.setForeground(new java.awt.Color(20, 20, 20));

        actual_dias_descanso_label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        actual_dias_descanso_label.setForeground(new java.awt.Color(20, 20, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(editar_perfil_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(provincia_label4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_provincia_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(departamento_label2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_departamento_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(distrito_label3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_distrito_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fecha_label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_fecha_label4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dni_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_dni_label5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(correo_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_correo_label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nombre_label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_nombre_label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(foto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGap(226, 226, 226))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dias_descanso_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_dias_descanso_label))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_label1)
                            .addComponent(actual_nombre_label2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correo_label)
                            .addComponent(actual_correo_label3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dni_label)
                            .addComponent(actual_dni_label5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha_label1)
                            .addComponent(actual_fecha_label4))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(distrito_label3)
                            .addComponent(actual_distrito_label8))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(provincia_label4)
                            .addComponent(actual_provincia_label6))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(departamento_label2)
                            .addComponent(actual_departamento_label7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dias_descanso_label)
                            .addComponent(actual_dias_descanso_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar_perfil_button)
                        .addGap(65, 65, 65))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(foto_label, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editar_perfil_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_perfil_buttonActionPerformed
        ((DashboardConductor) SwingUtilities.getWindowAncestor(this)).ShowJPanel(new PerfilConductorEditar(conductor, st));
    }//GEN-LAST:event_editar_perfil_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actual_correo_label3;
    private javax.swing.JLabel actual_departamento_label7;
    private javax.swing.JLabel actual_dias_descanso_label;
    private javax.swing.JLabel actual_distrito_label8;
    private javax.swing.JLabel actual_dni_label5;
    private javax.swing.JLabel actual_fecha_label4;
    private javax.swing.JLabel actual_nombre_label2;
    private javax.swing.JLabel actual_provincia_label6;
    private javax.swing.JLabel correo_label;
    private javax.swing.JLabel departamento_label2;
    private javax.swing.JLabel dias_descanso_label;
    private javax.swing.JLabel distrito_label3;
    private javax.swing.JLabel dni_label;
    private javax.swing.JButton editar_perfil_button;
    private javax.swing.JLabel fecha_label1;
    private javax.swing.JLabel foto_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre_label1;
    private javax.swing.JLabel provincia_label4;
    // End of variables declaration//GEN-END:variables
}