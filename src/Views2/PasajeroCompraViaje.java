/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.PagoMP;
import Models.Ruta;
import Models.Pasajero;
import Models.Viaje;
import Repository.ViajeRepository;
import Vista.DashboardPasajero;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Mihae
 */
public class PasajeroCompraViaje extends javax.swing.JPanel {

    private Pasajero pasajero;
    public PasajeroCompraViaje(Pasajero pasajero) {
        this.pasajero = pasajero;
        initComponents();
        correcciones_iniciales();
        Terminar_Compra.setVisible(false);
        listar_viajes();
   
    }
    
    private void correcciones_iniciales(){
        ID_viajes.setBackground(new Color(168, 168, 168));   
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
    
private void listar_viajes() {
    DefaultTableModel modelo = (DefaultTableModel) tabla_viajes.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de llenarla
    
    // Verificar si la lista de viajes es null
    List<Viaje> viajes = pasajero.ver_viajes(); 
    if (viajes == null || viajes.isEmpty()) {
        // Deshabilitar botón de compra
        Comprar_Boleto.setEnabled(false);
        
        // Mostrar mensaje informativo
        JOptionPane.showMessageDialog(this, 
            "No hay viajes disponibles en este momento.", 
            "Sin Viajes", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Limpiar el JComboBox
        ID_viajes.removeAllItems();
        return; // Salir del método si no hay viajes
    }

    // Limpiar el JComboBox antes de llenarlo
    ID_viajes.removeAllItems();

    // Si hay viajes, continuar con el llenado de la tabla
    for (Viaje viaje : viajes) {
        String origen = viaje.get_ruta().get_origen(); 
        String destino = viaje.get_ruta().get_destino(); 
        
        Duration tiempoAprox = viaje.get_ruta().get_tiempo_aproximado(); 
        long horas = tiempoAprox.toHours();
        long minutos = tiempoAprox.toMinutesPart(); 
        String tiempo_string = String.format("%02d:%02d", horas, minutos); 

        double precio = viaje.get_ruta().get_precio(); 
        
        LocalTime horaSalida = viaje.get_hora_salida();
        String horaSalidaString = horaSalida.toString();
        
        String estadoTexto = viaje.get_estado() ? "Disponible" : "No disponible";

        modelo.addRow(new Object[]{
            viaje.get_id_viaje(),
            viaje.get_fecha_salida(),
            origen,
            destino,
            horaSalidaString,
            tiempo_string,
            precio,
            estadoTexto
        });

        // Agregar el ID del viaje al JComboBox
        ID_viajes.addItem(viaje.get_id_viaje());
    }   

    // Actualizar la tabla con el modelo
    tabla_viajes.setModel(modelo);
    
    // Habilitar el botón de compra si hay viajes
    Comprar_Boleto.setEnabled(true);
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
        jLabel2 = new javax.swing.JLabel();
        Comprar_Boleto = new javax.swing.JButton();
        Terminar_Compra = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        ID_viajes = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Listado de Viajes Disponibles");

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("COMPRAR BOLETO");

        Comprar_Boleto.setBackground(new java.awt.Color(30, 30, 30));
        Comprar_Boleto.setForeground(new java.awt.Color(245, 245, 245));
        Comprar_Boleto.setText("Comprar");
        Comprar_Boleto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        Comprar_Boleto.setMaximumSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.setMinimumSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.setPreferredSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comprar_BoletoActionPerformed(evt);
            }
        });

        Terminar_Compra.setBackground(new java.awt.Color(30, 30, 30));
        Terminar_Compra.setForeground(new java.awt.Color(245, 245, 245));
        Terminar_Compra.setText("Terminar");
        Terminar_Compra.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        Terminar_Compra.setMaximumSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.setMinimumSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.setPreferredSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Terminar_CompraActionPerformed(evt);
            }
        });

        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Duracion (HH:MM)", "Precio", "Estado"
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
            tabla_viajes.getColumnModel().getColumn(0).setMaxWidth(180);
            tabla_viajes.getColumnModel().getColumn(1).setMaxWidth(120);
            tabla_viajes.getColumnModel().getColumn(2).setMaxWidth(120);
            tabla_viajes.getColumnModel().getColumn(3).setMaxWidth(120);
            tabla_viajes.getColumnModel().getColumn(4).setMaxWidth(400);
            tabla_viajes.getColumnModel().getColumn(5).setMaxWidth(280);
            tabla_viajes.getColumnModel().getColumn(6).setMaxWidth(50);
            tabla_viajes.getColumnModel().getColumn(7).setMaxWidth(150);
        }

        jLabel4.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 23, 23));
        jLabel4.setText("Elija el ID del viaje");

        ID_viajes.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        ID_viajes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ID_viajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_viajesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(25, 25, 25))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(50, 50, 50)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ID_viajes, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ID_viajes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Comprar_BoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comprar_BoletoActionPerformed
    // Obtener el ID del viaje seleccionado en el JComboBox
    String id_viaje = (String) ID_viajes.getSelectedItem();
    
    Viaje viaje = new ViajeRepository().buscar(id_viaje);
    
    if (viaje == null) {
        JOptionPane.showMessageDialog(this, "El viaje no existe", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Verificar si el viaje está disponible
    if (!viaje.get_estado()) {
        JOptionPane.showMessageDialog(this, "El viaje no está disponible.", "Viaje No Disponible", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
    PagoMP.init();
    
    Preference preference = PagoMP.crearPreferencia(
        "Boleto de Viaje", 
        "Viaje " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino(), 
        viaje.get_ruta().get_precio(),
        pasajero.get_correo()
    );
    
    // Redirigir a la página de pago
    PagoMP.redirigirAWeb(preference);

    // Mostrar diálogo para confirmar pago
    boolean continuar = true;
    while (continuar) {
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Has completado el pago en Mercado Pago?\n" +
            "Si desea cancelar, pulse NO",
            "Confirmar Pago", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Verificar estado del pago usando la preferencia
            boolean pagoExitoso = PagoMP.verificarEstadoPago(preference);
            
            if (pagoExitoso) {
                // Procesar compra de boleto
                boolean compraRealizada = pasajero.comprar_boleto(id_viaje);
                
                if (compraRealizada) {
                    JOptionPane.showMessageDialog(this, "Pago Verificado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    Terminar_Compra.setVisible(true);
                    continuar = false;
                } else {
                    JOptionPane.showMessageDialog(this, "Error con el boleto", "Error", JOptionPane.ERROR_MESSAGE);
                    continuar = false;
                }
            } else {
                // Si el pago no fue exitoso, preguntar si desea intentar nuevamente
                int reintentar = JOptionPane.showConfirmDialog(
                    this, 
                    "El pago no fue verificado. ¿Deseas intentar nuevamente?", 
                    "Pago No Verificado", 
                    JOptionPane.YES_NO_OPTION
                );

                if (reintentar == JOptionPane.NO_OPTION) {
                    // Si elige NO, salir del bucle
                    continuar = false;
                }
            }
        } else if (confirmacion == JOptionPane.NO_OPTION) {
            // Preguntar si realmente quiere cancelar
            int cancelarConfirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea cancelar el pago?",
                "Cancelar Pago",
                JOptionPane.YES_NO_OPTION
            );

            if (cancelarConfirmacion == JOptionPane.YES_OPTION) {
                continuar = false;
            }
            // Si elige NO en la confirmación de cancelación, el bucle continúa
        }
    }
    
    // Añadir esta parte después del bucle while
    if (!Terminar_Compra.isVisible()) {
        // Si no se completó el pago, mostrar mensaje de pago cancelado
        JOptionPane.showMessageDialog(
            this, 
            "Pago cancelado o no completado.", 
            "Pago Cancelado", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error al procesar el pago", "Error", JOptionPane.ERROR_MESSAGE);
    e.printStackTrace();
}
    }//GEN-LAST:event_Comprar_BoletoActionPerformed

    private void Terminar_CompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Terminar_CompraActionPerformed
        ((DashboardPasajero) SwingUtilities.getWindowAncestor(this)).ShowJPanel(new PasajeroHistorial(pasajero));
    }//GEN-LAST:event_Terminar_CompraActionPerformed

    private void ID_viajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_viajesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_viajesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Comprar_Boleto;
    private javax.swing.JComboBox<String> ID_viajes;
    private javax.swing.JButton Terminar_Compra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}