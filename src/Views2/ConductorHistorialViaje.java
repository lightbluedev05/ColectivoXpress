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
import java.sql.Statement;


/**
 *
 * @author Mihae
 */
public class ConductorHistorialViaje extends javax.swing.JPanel {

    private Conductor conductor;
    private Statement st;
    

    public ConductorHistorialViaje(Conductor conductor, Statement st) {
        this.st = st;
        this.conductor = conductor;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){ 
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
 private void asignarViajeAlConductor() {
    ViajeRepository viajeRepo = new ViajeRepository(st);
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
    if (conductor.tieneViajeActivo(st)) {
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
    
    List<Viaje> viajes = conductor.ver_viaje_asignado(st);
    
    for (Viaje viaje : viajes) {
        //Para ver si el estado de viaje esta finalizado o no 
        String estado = viaje.get_estado() ? "No" : "Si"; 
        
        modelo.addRow(new Object[]{
            conductor.get_dni(),
            viaje.get_id_viaje(),
            viaje.get_fecha_salida(),
            viaje.get_ruta().get_origen(),
            viaje.get_ruta().get_destino(),
            viaje.get_hora_salida(),
            viaje.get_ruta().get_tiempo_aproximado(),
            estado 
        });
    }
 
    tabla_viajes.setModel(modelo);
}
private void mostrar_pasajeros_viaje() {
    int selectedRow = tabla_viajes.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, seleccione un viaje primero.", 
            "Seleccionar Viaje", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String idViaje = (String) tabla_viajes.getValueAt(selectedRow, 1);
    
    // Buscar viaje del repo
    ViajeRepository viajeRepo = new ViajeRepository(st);
    Viaje viaje = viajeRepo.buscar(idViaje);
    
    if (viaje == null) {
        JOptionPane.showMessageDialog(this, 
            "No se encontró el viaje seleccionado.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Debugging: print the ID to verify
    System.out.println("Buscando pasajeros para viaje ID: " + idViaje);
    
    // Ver los pasajeros - añadir más debugging
     BoletoRepository boletoRepo = new BoletoRepository(st);
    List<Boleto> boletos = boletoRepo.obtenerBoletosParaViaje(idViaje);
    
    if (boletos != null && !boletos.isEmpty()) {
        StringBuilder pasajerosInfo = new StringBuilder("Pasajeros del Viaje:\n");
        for (Boleto boleto : boletos) {
            Pasajero pasajero = boleto.getPasajero();
            if (pasajero != null) {
                pasajerosInfo.append(String.format("Nombre: %s, DNI: %s, Telefono: %s\n", 
                    pasajero.getNombre(), 
                    pasajero.getDni(), 
                    pasajero.getTelefono()));
            }
        }
        
        if (pasajerosInfo.length() > 18) {  // More than "Pasajeros del Viaje:\n"
            JOptionPane.showMessageDialog(this, 
                pasajerosInfo.toString(), 
                "Pasajeros - Viaje " + viaje.get_id_viaje(), 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "No hay pasajeros para este viaje.", 
                "Sin pasajeros", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "No hay boletos encontrados para este viaje.", 
            "Sin pasajeros", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
    private void finalizar_viaje_actual(){
        List<Viaje> viajes_activos = new ViajeRepository(st).listar_activos();
        for(Viaje viaje_activo:viajes_activos){
            if(viaje_activo.get_conductor().get_dni().equals(conductor.get_dni())){
                viaje_activo.set_estado(false);
                new ViajeRepository(st).actualizar(viaje_activo);
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
        ListarPasajero = new javax.swing.JButton();
        ListarViaje = new javax.swing.JButton();
        FiltrarBusqueda = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setMinimumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Historial de Viajes");

        tabla_viajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI Conductor", "ID Viaje", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Duracion (HH:MM)", "Finalizado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            tabla_viajes.getColumnModel().getColumn(7).setMaxWidth(120);
        }

        ListarPasajero.setBackground(new java.awt.Color(41, 82, 85));
        ListarPasajero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ListarPasajero.setForeground(new java.awt.Color(240, 245, 247));
        ListarPasajero.setText("Lista Pasajeros");
        ListarPasajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarPasajeroActionPerformed(evt);
            }
        });

        ListarViaje.setBackground(new java.awt.Color(41, 82, 85));
        ListarViaje.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ListarViaje.setForeground(new java.awt.Color(240, 245, 247));
        ListarViaje.setText("Lista");
        ListarViaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarViajeActionPerformed(evt);
            }
        });

        FiltrarBusqueda.setBackground(new java.awt.Color(41, 82, 85));
        FiltrarBusqueda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        FiltrarBusqueda.setForeground(new java.awt.Color(240, 245, 247));
        FiltrarBusqueda.setText("Filtrar Busqueda");
        FiltrarBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FiltrarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ListarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(FiltrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(ListarPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListarPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FiltrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ListarPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarPasajeroActionPerformed
        // TODO add your handling code here:
       mostrar_pasajeros_viaje();
    }//GEN-LAST:event_ListarPasajeroActionPerformed

    private void ListarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarViajeActionPerformed
        listar_viaje_asignado();
    }//GEN-LAST:event_ListarViajeActionPerformed

    private void FiltrarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarBusquedaActionPerformed
        
    }//GEN-LAST:event_FiltrarBusquedaActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FiltrarBusqueda;
    private javax.swing.JButton ListarPasajero;
    private javax.swing.JButton ListarViaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
