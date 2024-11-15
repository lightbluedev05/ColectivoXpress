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
    }
    
    private void correcciones_iniciales(){
        id_buscar_input.setBackground(new Color(168, 168, 168));   
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
    
private void listar_viajes(){
    DefaultTableModel modelo = (DefaultTableModel)tabla_viajes.getModel();
    modelo.setRowCount(0);      
    List<Viaje> viajes = pasajero.ver_viajes(); 

    for(Viaje viaje : viajes){
        String origen = viaje.get_ruta().get_origen(); 
        String destino = viaje.get_ruta().get_destino(); 
        
        // Obtener la duración y convertirla a String
        Duration tiempoAprox = viaje.get_ruta().get_tiempo_aproximado(); 
        long horas = tiempoAprox.toHours();
        long minutos = tiempoAprox.toMinutesPart(); 
        String tiempo_string = String.format("%02d:%02d", horas, minutos); 

        double precio = viaje.get_ruta().get_precio(); 
        
        LocalTime horaSalida = viaje.get_hora_salida();
        String horaSalidaString = horaSalida.toString(); // Convertir LocalTime a String

        modelo.addRow(new Object[]{
            viaje.get_id_viaje(), // ID Viaje
            viaje.get_fecha_salida(), // Fecha
            origen, // Origen
            destino, // Destino
            horaSalidaString,
            tiempo_string, // Tiempo Aproximado en formato "HH:mm"
            precio // Precio
        });
    }   
    tabla_viajes.setModel(modelo);
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
        listar_viajes_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Comprar_Boleto = new javax.swing.JButton();
        id_buscar_input = new javax.swing.JTextField();
        Terminar_Compra = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        mensaje_pago = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Listado de Viajes");

        listar_viajes_button.setBackground(new java.awt.Color(30, 30, 30));
        listar_viajes_button.setForeground(new java.awt.Color(245, 245, 245));
        listar_viajes_button.setText("Listar Viajes");
        listar_viajes_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        listar_viajes_button.setMaximumSize(new java.awt.Dimension(170, 40));
        listar_viajes_button.setPreferredSize(new java.awt.Dimension(170, 40));
        listar_viajes_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar_viajes_buttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
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

        id_buscar_input.setCaretColor(new java.awt.Color(168, 168, 168));
        id_buscar_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_buscar_inputActionPerformed(evt);
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
                "ID ", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Duracion (HH:MM)", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            tabla_viajes.getColumnModel().getColumn(0).setMaxWidth(200);
            tabla_viajes.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(2).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(3).setMaxWidth(150);
            tabla_viajes.getColumnModel().getColumn(4).setMaxWidth(420);
            tabla_viajes.getColumnModel().getColumn(5).setMaxWidth(300);
            tabla_viajes.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        jLabel4.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 23, 23));
        jLabel4.setText("Ingresar ID del viaje");

        mensaje_pago.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        mensaje_pago.setForeground(new java.awt.Color(23, 23, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_buscar_input, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2)))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mensaje_pago)
                        .addGap(93, 93, 93))))
            .addGroup(layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(listar_viajes_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_buscar_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mensaje_pago)
                        .addGap(73, 73, 73)
                        .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listar_viajes_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listar_viajes_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar_viajes_buttonActionPerformed
        listar_viajes();
    }//GEN-LAST:event_listar_viajes_buttonActionPerformed

    private void Comprar_BoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comprar_BoletoActionPerformed
    String id_viaje = id_buscar_input.getText().trim();
    
    if (id_viaje.isEmpty()) {
        mensaje_pago.setText("Ingrese un ID de viaje");
        return;
    }
    
    Viaje viaje = new ViajeRepository().buscar(id_viaje);
    
    if (viaje == null) {
        mensaje_pago.setText("El viaje no existe");
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
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Has completado el pago en Mercado Pago?.\n"+
            "Si desea cancelar, pulse no",
            "Confirmar Pago", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Verificar estado del pago
            boolean pagoExitoso = pasajero.comprar_boleto(id_viaje);
            
            if (pagoExitoso) {
                mensaje_pago.setText("Pago Verificado!!");
                Terminar_Compra.setVisible(true);
            } else {
                mensaje_pago.setText("Pago no verificado");
            }
        }
        
    } catch (Exception e) {
        mensaje_pago.setText("Error al procesar el pago");
        e.printStackTrace();
    }
    }//GEN-LAST:event_Comprar_BoletoActionPerformed

    private void Terminar_CompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Terminar_CompraActionPerformed

    }//GEN-LAST:event_Terminar_CompraActionPerformed

    private void id_buscar_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_buscar_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_buscar_inputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Comprar_Boleto;
    private javax.swing.JButton Terminar_Compra;
    private javax.swing.JTextField id_buscar_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton listar_viajes_button;
    private javax.swing.JLabel mensaje_pago;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
