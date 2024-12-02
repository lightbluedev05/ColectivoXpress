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
public class ConductorViajeAsignado extends javax.swing.JPanel {

    private Conductor conductor;
    private Statement st;

    public ConductorViajeAsignado(Conductor conductor, Statement st) {
        this.st = st;
        this.conductor = conductor;
        initComponents();
    }
    
   
    private void listar_viaje_asignado() {
    // Obtener los viajes asignados al conductor
    ViajeRepository viajeRepo = new ViajeRepository(st);
    List<Viaje> viajes = conductor.ver_viaje_asignado(st);
    
    // Filtrar solo los viajes activos (no finalizados)
    List<Viaje> viajes_activos = new ArrayList<>();
    for (Viaje viaje : viajes) {
        if (viaje.get_estado()) { // Asumiendo que get_estado() devuelve true para viajes activos
            viajes_activos.add(viaje);
        }
    }
    
    if (viajes_activos.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No hay viajes activos asignados al conductor.", 
            "Sin viajes activos", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Suponiendo que solo haya un viaje activo asignado al conductor, de lo contrario puede que quieras iterar
    Viaje viajeActivo = viajes_activos.get(0); // O si hay más de uno, puedes elegir cuál mostrar
    
    // Actualizar los labels con los datos del viaje
    actual_dni.setText(conductor.get_dni()); // Mostrar ID del viaje
    actual_id.setText(viajeActivo.get_id_viaje()); // Mostrar el ID del viaje en el label correspondiente

    // Convertir LocalDate a String para la fecha de salida
    actual_fecha.setText(viajeActivo.get_fecha_salida().toString()); // O puedes usar un formato específico

    // Convertir LocalTime a String para la hora de salida
    actual_salida.setText(viajeActivo.get_hora_salida().toString()); // O puedes usar un formato específico

    // Convertir Duration a String para el tiempo aproximado
    Duration duration = viajeActivo.get_ruta().get_tiempo_aproximado();
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;
    actual_duracion.setText(hours + " horas " + minutes + " minutos");

    // Actualizar otros labels
    actual_origen.setText(viajeActivo.get_ruta().get_origen()); // Mostrar origen de la ruta
    actual_destino.setText(viajeActivo.get_ruta().get_destino()); // Mostrar destino de la ruta
}
    
private void mostrar_pasajeros_viaje() {
    // Obtener el viaje activo asignado al conductor
    Viaje viajeActivo = null;
    
    // Obtener los viajes asignados al conductor
    ViajeRepository viajeRepo = new ViajeRepository(st);
    List<Viaje> viajes = conductor.ver_viaje_asignado(st);
    
    // Buscar el primer viaje activo (no finalizado)
    for (Viaje viaje : viajes) {
        if (viaje.get_estado()) { // Si el viaje está activo
            viajeActivo = viaje;
            break;
        }
    }
    
    if (viajeActivo == null) {
        JOptionPane.showMessageDialog(this, 
            "El conductor no tiene viajes activos.", 
            "Error", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Obtener los boletos del viaje activo
    BoletoRepository boletoRepo = new BoletoRepository(st);
    List<Boleto> boletos = boletoRepo.obtenerBoletosParaViaje(viajeActivo.get_id_viaje());
    
    // Limpiar la tabla antes de llenarla con los nuevos datos
    DefaultTableModel model = (DefaultTableModel) actual_pasajeros.getModel();
    model.setRowCount(0);  // Limpiar las filas existentes
    
    if (boletos != null && !boletos.isEmpty()) {
        for (Boleto boleto : boletos) {
            Pasajero pasajero = boleto.getPasajero();
            if (pasajero != null) {
                // Agregar una nueva fila con los datos del pasajero
                model.addRow(new Object[]{
                    pasajero.getNombre(), 
                    pasajero.getDni(), 
                    pasajero.getTelefono()
                });
            }
        }
    } else {
        // Si no hay boletos, agregar una fila indicando que no hay pasajeros
        model.addRow(new Object[]{
            "No hay pasajeros", "", ""
        });
    }
    
    // Notificar al usuario que los pasajeros se han cargado
    JOptionPane.showMessageDialog(this, 
        "Los pasajeros se han cargado en la tabla.", 
        "Pasajeros Cargados", 
        JOptionPane.INFORMATION_MESSAGE);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ListarViajesss = new javax.swing.JButton();
        finalizar_viaje_actual_button = new javax.swing.JButton();
        lista_pasajeros = new javax.swing.JButton();
        DNI = new javax.swing.JLabel();
        ID_viaje = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        origen = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        duracion = new javax.swing.JLabel();
        pasajeros = new javax.swing.JLabel();
        actual_dni = new javax.swing.JLabel();
        actual_id = new javax.swing.JLabel();
        actual_fecha = new javax.swing.JLabel();
        actual_origen = new javax.swing.JLabel();
        actual_destino = new javax.swing.JLabel();
        actual_salida = new javax.swing.JLabel();
        actual_duracion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actual_pasajeros = new javax.swing.JTable();

        jLabel2.setText("jLabel2");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setMinimumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Viaje Asignado");

        ListarViajesss.setBackground(new java.awt.Color(41, 82, 85));
        ListarViajesss.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ListarViajesss.setForeground(new java.awt.Color(240, 245, 247));
        ListarViajesss.setText("Lista");
        ListarViajesss.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListarViajesss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarViajesssActionPerformed(evt);
            }
        });

        finalizar_viaje_actual_button.setBackground(new java.awt.Color(41, 82, 85));
        finalizar_viaje_actual_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        finalizar_viaje_actual_button.setForeground(new java.awt.Color(240, 245, 247));
        finalizar_viaje_actual_button.setText("Finalizar Viaje");
        finalizar_viaje_actual_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finalizar_viaje_actual_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizar_viaje_actual_buttonActionPerformed(evt);
            }
        });

        lista_pasajeros.setBackground(new java.awt.Color(41, 82, 85));
        lista_pasajeros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lista_pasajeros.setForeground(new java.awt.Color(240, 245, 247));
        lista_pasajeros.setText("Lista Pasajeros");
        lista_pasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lista_pasajerosActionPerformed(evt);
            }
        });

        DNI.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        DNI.setForeground(new java.awt.Color(20, 20, 20));
        DNI.setText("DNI Conductor:");

        ID_viaje.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        ID_viaje.setForeground(new java.awt.Color(20, 20, 20));
        ID_viaje.setText("ID Viaje:");

        fecha.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        fecha.setForeground(new java.awt.Color(20, 20, 20));
        fecha.setText("Fecha:");

        origen.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        origen.setForeground(new java.awt.Color(20, 20, 20));
        origen.setText("Origen:");

        hora.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        hora.setForeground(new java.awt.Color(20, 20, 20));
        hora.setText("Hora de Salida:");

        destino.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        destino.setForeground(new java.awt.Color(20, 20, 20));
        destino.setText("Destino:");

        duracion.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        duracion.setForeground(new java.awt.Color(20, 20, 20));
        duracion.setText("Duración:");

        pasajeros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pasajeros.setForeground(new java.awt.Color(20, 20, 20));
        pasajeros.setText("Lista de Pasajeros");

        actual_dni.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_id.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_fecha.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_origen.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_destino.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_salida.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_duracion.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N

        actual_pasajeros.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        actual_pasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "DNI", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(actual_pasajeros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ID_viaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_id))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(duracion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_duracion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(destino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_destino))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_salida))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(origen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_origen))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actual_fecha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actual_dni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pasajeros)
                        .addGap(175, 175, 175))))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(ListarViajesss, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(lista_pasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addComponent(finalizar_viaje_actual_button, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
            .addGroup(layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DNI)
                    .addComponent(actual_dni)
                    .addComponent(pasajeros))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID_viaje)
                            .addComponent(actual_id))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha)
                            .addComponent(actual_fecha))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(origen)
                            .addComponent(actual_origen))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(destino)
                            .addComponent(actual_destino))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hora)
                            .addComponent(actual_salida))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(duracion)
                            .addComponent(actual_duracion)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finalizar_viaje_actual_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListarViajesss, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lista_pasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ListarViajesssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarViajesssActionPerformed

       listar_viaje_asignado();
    }//GEN-LAST:event_ListarViajesssActionPerformed

    private void finalizar_viaje_actual_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizar_viaje_actual_buttonActionPerformed
        finalizar_viaje_actual();
    }//GEN-LAST:event_finalizar_viaje_actual_buttonActionPerformed

    private void lista_pasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lista_pasajerosActionPerformed
        mostrar_pasajeros_viaje();
    }//GEN-LAST:event_lista_pasajerosActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DNI;
    private javax.swing.JLabel ID_viaje;
    private javax.swing.JButton ListarViajesss;
    private javax.swing.JLabel actual_destino;
    private javax.swing.JLabel actual_dni;
    private javax.swing.JLabel actual_duracion;
    private javax.swing.JLabel actual_fecha;
    private javax.swing.JLabel actual_id;
    private javax.swing.JLabel actual_origen;
    private javax.swing.JTable actual_pasajeros;
    private javax.swing.JLabel actual_salida;
    private javax.swing.JLabel destino;
    private javax.swing.JLabel duracion;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton finalizar_viaje_actual_button;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lista_pasajeros;
    private javax.swing.JLabel origen;
    private javax.swing.JLabel pasajeros;
    // End of variables declaration//GEN-END:variables
}
