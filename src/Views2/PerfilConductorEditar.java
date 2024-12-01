package Views2;

import Models.Conductor;
import Repository.ConductorRepository;
import Repository.RegulacionLaboral;
import javax.swing.JOptionPane;
import java.sql.Statement;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Mihae
 */
public class PerfilConductorEditar extends javax.swing.JPanel {

    /**
     * Creates new form PerfilAdmin
     */
    
    private Conductor conductor;
    private Statement st;

    public PerfilConductorEditar(Conductor conductor, Statement st) {
        this.st = st;
        this.conductor = conductor;
        initComponents();
        nuevo_contra_label.setVisible(false);
        nuevo_contra_input.setVisible(false);
        nuevo_contra_button.setVisible(false);
        
        nuevo_dias_descanso_label1.setVisible(false);
        nuevo_dias_descanso_input1.setVisible(false);
        nuevo_dias_descanso_button1.setVisible(false);
        
        nuevo_distrito_label.setVisible(false);
        nuevo_distrito_input.setVisible(false);
        nuevo_distrito_button.setVisible(false);
        
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
        contra_input = new javax.swing.JPasswordField();
        ingresar_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nuevo_contra_label = new javax.swing.JLabel();
        nuevo_contra_input = new javax.swing.JPasswordField();
        nuevo_contra_button = new javax.swing.JButton();
        nuevo_contra_message = new javax.swing.JLabel();
        nuevo_nombre_message = new javax.swing.JLabel();
        nuevo_distrito_label = new javax.swing.JLabel();
        nuevo_distrito_input = new javax.swing.JTextField();
        nuevo_distrito_button = new javax.swing.JButton();
        nuevo_distrito_message = new javax.swing.JLabel();
        nuevo_provincia_message = new javax.swing.JLabel();
        nuevo_departamento_message = new javax.swing.JLabel();
        nuevo_dias_descanso_label1 = new javax.swing.JLabel();
        nuevo_dias_descanso_input1 = new javax.swing.JTextField();
        nuevo_dias_descanso_button1 = new javax.swing.JButton();
        nuevo_dias_descanso_message = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1010, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1010, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1010, 580));

        contra_input.setBackground(new java.awt.Color(240, 245, 247));
        contra_input.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        contra_input.setMinimumSize(new java.awt.Dimension(370, 22));
        contra_input.setPreferredSize(new java.awt.Dimension(370, 22));

        ingresar_button.setBackground(new java.awt.Color(41, 82, 85));
        ingresar_button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ingresar_button.setForeground(new java.awt.Color(240, 245, 247));
        ingresar_button.setText("Ingresar");
        ingresar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresar_buttonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(20, 20, 20));
        jLabel1.setText("Ingrese su contraseña para modificar sus datos");

        nuevo_contra_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nuevo_contra_label.setForeground(new java.awt.Color(20, 20, 20));
        nuevo_contra_label.setText("Actualizar contraseña");

        nuevo_contra_input.setBackground(new java.awt.Color(240, 245, 247));
        nuevo_contra_input.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_contra_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_contra_inputActionPerformed(evt);
            }
        });

        nuevo_contra_button.setBackground(new java.awt.Color(41, 82, 85));
        nuevo_contra_button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_contra_button.setForeground(new java.awt.Color(240, 245, 247));
        nuevo_contra_button.setText("Modificar");
        nuevo_contra_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_contra_buttonActionPerformed(evt);
            }
        });

        nuevo_distrito_label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nuevo_distrito_label.setForeground(new java.awt.Color(20, 20, 20));
        nuevo_distrito_label.setText("Actualiza distrito");

        nuevo_distrito_input.setBackground(new java.awt.Color(240, 245, 247));
        nuevo_distrito_input.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_distrito_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_distrito_inputActionPerformed(evt);
            }
        });

        nuevo_distrito_button.setBackground(new java.awt.Color(41, 82, 85));
        nuevo_distrito_button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_distrito_button.setForeground(new java.awt.Color(240, 245, 247));
        nuevo_distrito_button.setText("Modificar");
        nuevo_distrito_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_distrito_buttonActionPerformed(evt);
            }
        });

        nuevo_dias_descanso_label1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nuevo_dias_descanso_label1.setForeground(new java.awt.Color(20, 20, 20));
        nuevo_dias_descanso_label1.setText("Actualiza dias de descanso");

        nuevo_dias_descanso_input1.setBackground(new java.awt.Color(240, 245, 247));
        nuevo_dias_descanso_input1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_dias_descanso_input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_dias_descanso_input1ActionPerformed(evt);
            }
        });

        nuevo_dias_descanso_button1.setBackground(new java.awt.Color(41, 82, 85));
        nuevo_dias_descanso_button1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nuevo_dias_descanso_button1.setForeground(new java.awt.Color(240, 245, 247));
        nuevo_dias_descanso_button1.setText("Modificar");
        nuevo_dias_descanso_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_dias_descanso_button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(ingresar_button, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contra_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevo_dias_descanso_label1)
                    .addComponent(nuevo_contra_label)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nuevo_contra_input, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_contra_button))
                    .addComponent(nuevo_distrito_label)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nuevo_distrito_input, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_distrito_button))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nuevo_dias_descanso_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_dias_descanso_button1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevo_dias_descanso_message)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevo_nombre_message)
                            .addComponent(nuevo_contra_message)
                            .addComponent(nuevo_distrito_message)
                            .addComponent(nuevo_provincia_message)
                            .addComponent(nuevo_departamento_message))))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(nuevo_contra_message)
                        .addGap(69, 69, 69)
                        .addComponent(nuevo_nombre_message)
                        .addGap(69, 69, 69)
                        .addComponent(nuevo_distrito_message)
                        .addGap(69, 69, 69)
                        .addComponent(nuevo_provincia_message)
                        .addGap(69, 69, 69)
                        .addComponent(nuevo_departamento_message))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contra_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ingresar_button)
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_contra_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevo_contra_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevo_contra_button))
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_distrito_label)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevo_distrito_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevo_distrito_button))
                        .addGap(18, 18, 18)
                        .addComponent(nuevo_dias_descanso_label1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevo_dias_descanso_input1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevo_dias_descanso_button1))))
                .addGap(49, 49, 49)
                .addComponent(nuevo_dias_descanso_message)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevo_dias_descanso_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_dias_descanso_button1ActionPerformed
        // Obtener el valor ingresado por el usuario para los nuevos días de descanso
        String input = nuevo_dias_descanso_input1.getText();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor válido para los días de descanso", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String[] diasDescanso = input.split(",");
            int cantidadDias = diasDescanso.length;

            // Obtener la instancia de RegulacionLaboral y validar el límite de días de descanso
            RegulacionLaboral regulacion = new RegulacionLaboral(st);
            int limiteDias = regulacion.get_limite_dias_descanso();

            // Validar que la cantidad de días de descanso no supere el límite establecido
            if (cantidadDias > limiteDias) {
                JOptionPane.showMessageDialog(this, "La cantidad de días de descanso no puede superar el límite de " + limiteDias + " días.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el DNI del conductor actual (esto depende de tu lógica de aplicación)
            String dniConductor = conductor.get_dni(); // Asumiendo que tienes acceso al objeto conductor
            ConductorRepository conductorRepo = new ConductorRepository(st);
            Conductor conductor = conductorRepo.buscar(dniConductor); // Busca el conductor usando su DNI

            if (conductor != null) {
                conductor.set_dias_descanso(input);
                boolean actualizado = conductorRepo.actualizar(conductor);
                if (!actualizado) {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar los días de descanso del conductor", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Conductor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Días de descanso actualizados exitosamente!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            nuevo_dias_descanso_input1.setText(""); // Limpiar el campo de entrada
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese los días de descanso en el formato correcto.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_nuevo_dias_descanso_button1ActionPerformed

    private void nuevo_dias_descanso_input1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_dias_descanso_input1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_dias_descanso_input1ActionPerformed

    private void nuevo_distrito_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_distrito_buttonActionPerformed
        String distrito = new String(nuevo_distrito_input.getText());

        if(distrito.isEmpty()){
            nuevo_distrito_message.setText("Ingrese un distrito valido");
            return;
        }

        boolean exito = conductor.actualizar_distrito(distrito, st);
        if(!exito){
            JOptionPane.showMessageDialog(this, "No se pudo cambiar el distrito", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Distrito cambiado exitosamente!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_nuevo_distrito_buttonActionPerformed

    private void nuevo_distrito_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_distrito_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_distrito_inputActionPerformed

    private void nuevo_contra_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_contra_buttonActionPerformed
        String contra = new String(nuevo_contra_input.getPassword());

        if(contra.isEmpty()){
            nuevo_contra_message.setText("Ingrese una contraseña valida");
            return;
        }

        boolean exito = conductor.actualizar_contrasena(contra, st);
        if(!exito){
            JOptionPane.showMessageDialog(this, "No se pudo cambiar la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente!!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_nuevo_contra_buttonActionPerformed

    private void nuevo_contra_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo_contra_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_contra_inputActionPerformed

    private void ingresar_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresar_buttonActionPerformed
        String contra = new String(contra_input.getPassword());
        if(conductor.get_contrasena().equals(contra)){
            nuevo_contra_label.setVisible(true);
            nuevo_contra_input.setVisible(true);
            nuevo_contra_button.setVisible(true);


            nuevo_dias_descanso_label1.setVisible(true);
            nuevo_dias_descanso_input1.setVisible(true);
            nuevo_dias_descanso_button1.setVisible(true);
            
            nuevo_distrito_label.setVisible(true);
            nuevo_distrito_input.setVisible(true);
            nuevo_distrito_button.setVisible(true);
        } else {
            // Si la contraseña es incorrecta, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Vuelva a intentar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ingresar_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contra_input;
    private javax.swing.JButton ingresar_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton nuevo_contra_button;
    private javax.swing.JPasswordField nuevo_contra_input;
    private javax.swing.JLabel nuevo_contra_label;
    private javax.swing.JLabel nuevo_contra_message;
    private javax.swing.JLabel nuevo_departamento_message;
    private javax.swing.JButton nuevo_dias_descanso_button1;
    private javax.swing.JTextField nuevo_dias_descanso_input1;
    private javax.swing.JLabel nuevo_dias_descanso_label1;
    private javax.swing.JLabel nuevo_dias_descanso_message;
    private javax.swing.JButton nuevo_distrito_button;
    private javax.swing.JTextField nuevo_distrito_input;
    private javax.swing.JLabel nuevo_distrito_label;
    private javax.swing.JLabel nuevo_distrito_message;
    private javax.swing.JLabel nuevo_nombre_message;
    private javax.swing.JLabel nuevo_provincia_message;
    // End of variables declaration//GEN-END:variables

}