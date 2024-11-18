/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.Boleto;
import Models.Conductor;
import Models.PagoMP;
import Models.Ruta;
import Models.Pasajero;
import Models.Viaje;
import Repository.BoletoRepository;
import Repository.ViajeRepository;
import com.mercadopago.resources.Preference;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import kotlin.random.Random;
import java.time.Duration;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Mihae
 */
public class ViajeAsignado extends javax.swing.JPanel {

    private Conductor conductor;
    public ViajeAsignado(Conductor conductor) {
        this.conductor = conductor;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){ 
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
 private void asignarViajeAlConductor() {
    ViajeRepository viajeRepo = new ViajeRepository();
    List<Viaje> viajesDisponibles = viajeRepo.listar();
    
    // Crear una lista de viajes que no tienen conductor asignado
    List<Viaje> viajesSinAsignar = new ArrayList<>();
    for (Viaje viaje : viajesDisponibles) {
        if (viaje.get_conductor() == null) {
            viajesSinAsignar.add(viaje);
        }
    }
    
    if (viajesSinAsignar.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No hay viajes disponibles para asignar.", 
            "Sin viajes disponibles", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Verificar si el conductor ya tiene un viaje activo
    if (conductor.tieneViajeActivo()) {
        JOptionPane.showMessageDialog(this, 
            "El conductor ya tiene un viaje activo.", 
            "Viaje activo", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Crear un array con la información de los viajes para mostrar en el diálogo
    String[] opciones = new String[viajesSinAsignar.size()];
    for (int i = 0; i < viajesSinAsignar.size(); i++) {
        Viaje v = viajesSinAsignar.get(i);
        opciones[i] = String.format("ID: %s - Ruta: %s a %s - Fecha: %s - Hora: %s",
            v.get_id_viaje(),
            v.get_ruta().get_origen(),
            v.get_ruta().get_destino(),
            v.get_fecha_salida(),
            v.get_hora_salida());
    }
    
    // Mostrar diálogo de selección
    String seleccion = (String) JOptionPane.showInputDialog(
        this,
        "Seleccione un viaje para asignar:",
        "Asignar Viaje",
        JOptionPane.QUESTION_MESSAGE,
        null,
        opciones,
        opciones[0]);
    
    if (seleccion != null) {
        // Encontrar el viaje seleccionado
        int index = 0;
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(seleccion)) {
                index = i;
                break;
            }
        }
        
        Viaje viajeSeleccionado = viajesSinAsignar.get(index);
        viajeSeleccionado.set_conductor(this.conductor);
        
        // Actualizar el viaje en el repositorio
        if (viajeRepo.actualizar(viajeSeleccionado)) {
            JOptionPane.showMessageDialog(this,
                "Viaje asignado exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
            listar_viaje_asignado(); // Actualizar la tabla
        } else {
            JOptionPane.showMessageDialog(this,
                "Error al asignar el viaje.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

}
    
private void listar_viaje_asignado() {
    DefaultTableModel modelo = (DefaultTableModel) tabla_viajes.getModel();
    modelo.setRowCount(0);
    
    List<Viaje> viajes = conductor.ver_viaje_asignado();
    
    for (Viaje viaje : viajes) {
        modelo.addRow(new Object[]{
            conductor.get_dni(),
            viaje.get_id_viaje(),
            viaje.get_fecha_salida(),
            viaje.get_ruta().get_origen(),
            viaje.get_ruta().get_destino(),
            viaje.get_hora_salida(),
            viaje.get_ruta().get_tiempo_aproximado()
        });
    }
 
    tabla_viajes.setModel(modelo);
}
    private void finalizar_viaje_actual(){
        List<Viaje> viajes_activos = new ViajeRepository().listar_activos();
        for(Viaje viaje_activo:viajes_activos){
            if(viaje_activo.get_conductor().get_dni().equals(conductor.get_dni())){
                viaje_activo.set_estado(false);
                new ViajeRepository().actualizar(viaje_activo);
                JOptionPane.showMessageDialog(null, "Viaje Finalizado", "Viaje Finalizado", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No hay ningun viaje activo", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        ListarViajes = new javax.swing.JButton();
        ListarViajesss = new javax.swing.JButton();
        finalizar_viaje_actual_button = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setMinimumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Viajes Asignados");

        tabla_viajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI Conductor", "ID Viaje", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Duracion (HH:MM)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_viajes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabla_viajes);
        if (tabla_viajes.getColumnModel().getColumnCount() > 0) {
            tabla_viajes.getColumnModel().getColumn(0).setMaxWidth(300);
            tabla_viajes.getColumnModel().getColumn(1).setMaxWidth(200);
            tabla_viajes.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(3).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(4).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(5).setMaxWidth(400);
            tabla_viajes.getColumnModel().getColumn(6).setMaxWidth(280);
        }

        ListarViajes.setBackground(new java.awt.Color(41, 82, 85));
        ListarViajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ListarViajes.setForeground(new java.awt.Color(240, 245, 247));
        ListarViajes.setText("Asignar Viaje");
        ListarViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarViajesActionPerformed(evt);
            }
        });

        ListarViajesss.setBackground(new java.awt.Color(41, 82, 85));
        ListarViajesss.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ListarViajesss.setForeground(new java.awt.Color(240, 245, 247));
        ListarViajesss.setText("Lista");
        ListarViajesss.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarViajesss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarViajesssActionPerformed(evt);
            }
        });

        finalizar_viaje_actual_button.setBackground(new java.awt.Color(41, 82, 85));
        finalizar_viaje_actual_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        finalizar_viaje_actual_button.setForeground(new java.awt.Color(240, 245, 247));
        finalizar_viaje_actual_button.setText("Finalizar Viaje Actual");
        finalizar_viaje_actual_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finalizar_viaje_actual_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizar_viaje_actual_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ListarViajes)
                .addGap(195, 195, 195)
                .addComponent(ListarViajesss)
                .addGap(397, 397, 397))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(finalizar_viaje_actual_button, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ListarViajes)
                            .addComponent(ListarViajesss))
                        .addGap(32, 32, 32)))
                .addComponent(finalizar_viaje_actual_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ListarViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarViajesActionPerformed
        // TODO add your handling code here:
         // Llamar al método para asignar un viaje al conductor
    asignarViajeAlConductor();
    listar_viaje_asignado();

    }//GEN-LAST:event_ListarViajesActionPerformed

    private void ListarViajesssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarViajesssActionPerformed
        // TODO add your handling code here:
        listar_viaje_asignado();
    }//GEN-LAST:event_ListarViajesssActionPerformed

    private void finalizar_viaje_actual_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizar_viaje_actual_buttonActionPerformed
        finalizar_viaje_actual();
    }//GEN-LAST:event_finalizar_viaje_actual_buttonActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ListarViajes;
    private javax.swing.JButton ListarViajesss;
    private javax.swing.JButton finalizar_viaje_actual_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
