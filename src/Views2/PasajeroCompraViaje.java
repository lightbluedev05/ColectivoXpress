/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.PagoMP;
import Models.Pasajero;
import Models.Viaje;
import Repository.ViajeRepository;
import Views2.PasajeroHistorial;
import Vista.DashboardPasajero;
import com.mercadopago.resources.Preference;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.Duration;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Mihae
 */
public class PasajeroCompraViaje extends javax.swing.JPanel {

    private Pasajero pasajero;
    private Statement st;
    
    public PasajeroCompraViaje(Pasajero pasajero, Statement st) {
        this.pasajero = pasajero;
        this.st = st;
        initComponents();
        correcciones_iniciales();
        Terminar_Compra.setVisible(false); 
       
 
    cargarViajesIniciales();

    }
    
    private void correcciones_iniciales(){
        ID_viajes.setBackground(new Color(168, 168, 168));   
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
    
  
    
private void cargarViajesIniciales() {
    // Obtener todos los viajes disponibles
    List<Viaje> viajes = pasajero.ver_viajes(st);

    if (viajes != null && !viajes.isEmpty()) {
        // Limpiar modelos
        DefaultTableModel modeloViajes = (DefaultTableModel) tabla_viajes.getModel();
        modeloViajes.setRowCount(0);

        ID_viajes.removeAllItems();
        ID_c.removeAllItems();
        ID_c.addItem("Todos"); // Agregar opción "Todos"

        // Filtrar viajes activos
        List<Viaje> viajesActivos = viajes.stream()
            .filter(Viaje::get_estado)
            .collect(Collectors.toList());

        // Llenar tabla de viajes
        for (Viaje viaje : viajesActivos) {
            Duration tiempoAprox = viaje.get_ruta().get_tiempo_aproximado(); 
            long horas = tiempoAprox.toHours();
            long minutos = tiempoAprox.toMinutesPart(); 
            String tiempo_string = String.format("%02d:%02d", horas, minutos); 

            LocalTime horaSalida = viaje.get_hora_salida();
            String horaSalidaString = horaSalida.toString();

            modeloViajes.addRow(new Object[]{
                viaje.get_id_viaje(),
                viaje.get_fecha_salida(),
                viaje.get_ruta().get_origen(),
                viaje.get_ruta().get_destino(),
                horaSalidaString,
                tiempo_string,
                viaje.get_ruta().get_precio()
            });

            // Agregar el ID del viaje a ambos ComboBox
            ID_viajes.addItem(viaje.get_id_viaje());
            ID_c.addItem(viaje.get_id_viaje());
        }

        // Actualizar el modelo de la tabla
        tabla_viajes.setModel(modeloViajes);

        // Habilitar/deshabilitar botón de compra
        Comprar_Boleto.setEnabled(!viajesActivos.isEmpty());

        // Cargar todos los conductores
        cargarTodosLosConductores(viajesActivos);
    } else {
        JOptionPane.showMessageDialog(this, 
            "No se pudieron cargar los viajes.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

private void cargarTodosLosConductores(List<Viaje> viajes) {
    // Limpiar la tabla de datos del conductor
    DefaultTableModel modeloConductor = (DefaultTableModel) datos_conductor.getModel();
    modeloConductor.setRowCount(0);

    for (Viaje viaje : viajes) {
                // Llenar tabla de conductor
                if (viaje.get_conductor() != null) {
                    modeloConductor.addRow(new Object[]{
                        viaje.get_id_viaje(), // ID Viaje
                        viaje.get_conductor().get_nombre() != null && !viaje.get_conductor().get_nombre().isEmpty() 
                            ? viaje.get_conductor().get_nombre() 
                            : "N/A",

                        viaje.get_conductor().get_correo() != null && !viaje.get_conductor().get_correo().isEmpty() 
                            ? viaje.get_conductor().get_correo() 
                            : "N/A",

                        viaje.get_conductor().get_telefono() != null && !viaje.get_conductor().get_telefono().isEmpty() 
                            ? viaje.get_conductor().get_telefono() 
                            : "N/A",

                        viaje.get_conductor().get_placa_vehiculo() != null && !viaje.get_conductor().get_placa_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_placa_vehiculo()
                            : "N/A",

                        viaje.get_conductor().get_modelo_vehiculo() != null && !viaje.get_conductor().get_modelo_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_modelo_vehiculo()
                            : "N/A"
                    });
                }
    }

    // Actualizar la tabla de datos del conductor
    datos_conductor.setModel(modeloConductor);
}

      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ID_viajes = new javax.swing.JComboBox<>();
        Terminar_Compra = new javax.swing.JButton();
        Comprar_Boleto = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        ID_c = new javax.swing.JComboBox<>();
        Buscar_c = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        datos_conductor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        filtrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        tabla_viajes.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Viaje", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Duracion (HH:MM)", "Precio"
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
            tabla_viajes.getColumnModel().getColumn(0).setMaxWidth(140);
            tabla_viajes.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_viajes.getColumnModel().getColumn(2).setMaxWidth(70);
            tabla_viajes.getColumnModel().getColumn(3).setMaxWidth(70);
            tabla_viajes.getColumnModel().getColumn(4).setMaxWidth(380);
            tabla_viajes.getColumnModel().getColumn(5).setMaxWidth(270);
            tabla_viajes.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("COMPRAR BOLETO");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 23, 23));
        jLabel4.setText("Elija el ID del viaje");

        ID_viajes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ID_viajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_viajesActionPerformed(evt);
            }
        });

        Terminar_Compra.setBackground(new java.awt.Color(41, 82, 85));
        Terminar_Compra.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Terminar_Compra.setForeground(new java.awt.Color(240, 245, 247));
        Terminar_Compra.setText("Terminar");
        Terminar_Compra.setMaximumSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.setMinimumSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.setPreferredSize(new java.awt.Dimension(161, 40));
        Terminar_Compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Terminar_CompraActionPerformed(evt);
            }
        });

        Comprar_Boleto.setBackground(new java.awt.Color(41, 82, 85));
        Comprar_Boleto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Comprar_Boleto.setForeground(new java.awt.Color(240, 245, 247));
        Comprar_Boleto.setText("Comprar");
        Comprar_Boleto.setMaximumSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.setMinimumSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.setPreferredSize(new java.awt.Dimension(161, 40));
        Comprar_Boleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comprar_BoletoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ID_viajes, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ID_viajes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comprar_Boleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Terminar_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 23, 23));
        jLabel3.setText("Listado de Viajes Disponibles");

        ID_c.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ID_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_cActionPerformed(evt);
            }
        });

        Buscar_c.setBackground(new java.awt.Color(41, 82, 85));
        Buscar_c.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Buscar_c.setForeground(new java.awt.Color(240, 245, 247));
        Buscar_c.setText("Buscar");
        Buscar_c.setMaximumSize(new java.awt.Dimension(161, 40));
        Buscar_c.setMinimumSize(new java.awt.Dimension(161, 40));
        Buscar_c.setPreferredSize(new java.awt.Dimension(161, 40));
        Buscar_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_cActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 23, 23));
        jLabel6.setText("CONDUCTOR");

        datos_conductor.setBackground(new java.awt.Color(240, 245, 247));
        datos_conductor.setForeground(new java.awt.Color(22, 38, 35));
        datos_conductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Viaje", "Nombre", "Correo", "Numero", "Placa", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datos_conductor.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(datos_conductor);
        if (datos_conductor.getColumnModel().getColumnCount() > 0) {
            datos_conductor.getColumnModel().getColumn(0).setMaxWidth(60);
            datos_conductor.getColumnModel().getColumn(1).setMaxWidth(1000);
            datos_conductor.getColumnModel().getColumn(2).setMaxWidth(500);
            datos_conductor.getColumnModel().getColumn(3).setMaxWidth(100);
            datos_conductor.getColumnModel().getColumn(4).setMaxWidth(100);
            datos_conductor.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 23, 23));
        jLabel5.setText("Buscar Viaje");

        filtrar.setBackground(new java.awt.Color(0, 102, 102));
        filtrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        filtrar.setForeground(new java.awt.Color(240, 245, 247));
        filtrar.setText("Filtrar Busqueda");
        filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(Buscar_c, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ID_c, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Buscar_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ID_c, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Comprar_BoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comprar_BoletoActionPerformed
    // Obtener el ID del viaje seleccionado en el JComboBox
    String id_viaje = (String) ID_viajes.getSelectedItem();
    
    Viaje viaje = new ViajeRepository(st).buscar(id_viaje);
    
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
                boolean compraRealizada = pasajero.comprar_boleto(id_viaje, st);
                
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
        ((DashboardPasajero) SwingUtilities.getWindowAncestor(this)).ShowJPanel(new PasajeroHistorial(pasajero, st));
    }//GEN-LAST:event_Terminar_CompraActionPerformed

    private void ID_viajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_viajesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_viajesActionPerformed

    private void ID_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_cActionPerformed

    private void Buscar_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_cActionPerformed
            // Obtener el ID del viaje seleccionado
        String idViaje = (String) ID_c.getSelectedItem();

        // Limpiar la tabla de datos del conductor
        DefaultTableModel modeloConductor = (DefaultTableModel) datos_conductor.getModel();
        modeloConductor.setRowCount(0);

        // Obtener todos los viajes
        List<Viaje> viajes = pasajero.ver_viajes(st);

        if (viajes == null || viajes.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay viajes disponibles.", 
                "Sin Viajes", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (idViaje.equals("Todos")) {
            // Mostrar conductores de todos los viajes activos
            for (Viaje viaje : viajes) {
                if (viaje.get_estado() && viaje.get_conductor() != null) { // Solo viajes activos
                    modeloConductor.addRow(new Object[]{
                        viaje.get_id_viaje(), // ID Viaje
                        viaje.get_conductor().get_nombre() != null && !viaje.get_conductor().get_nombre().isEmpty() 
                            ? viaje.get_conductor().get_nombre() 
                            : "N/A",

                        viaje.get_conductor().get_correo() != null && !viaje.get_conductor().get_correo().isEmpty() 
                            ? viaje.get_conductor().get_correo() 
                            : "N/A",

                        viaje.get_conductor().get_telefono() != null && !viaje.get_conductor().get_telefono().isEmpty() 
                            ? viaje.get_conductor().get_telefono() 
                            : "N/A",

                        viaje.get_conductor().get_placa_vehiculo() != null && !viaje.get_conductor().get_placa_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_placa_vehiculo()
                            : "N/A",

                        viaje.get_conductor().get_modelo_vehiculo() != null && !viaje.get_conductor().get_modelo_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_modelo_vehiculo()
                            : "N/A"
                    });
                }
            }
        } else {
            // Buscar un viaje específico
            Viaje viaje = new ViajeRepository(st).buscar(idViaje);

            if (viaje == null) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró el viaje.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si el viaje tiene conductor
            if (viaje.get_conductor() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Este viaje no tiene conductor asignado.", 
                    "Sin Conductor", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Agregar datos del conductor a la tabla
                    modeloConductor.addRow(new Object[]{
                        viaje.get_id_viaje(), // ID Viaje
                        viaje.get_conductor().get_nombre() != null && !viaje.get_conductor().get_nombre().isEmpty() 
                            ? viaje.get_conductor().get_nombre() 
                            : "N/A",

                        viaje.get_conductor().get_correo() != null && !viaje.get_conductor().get_correo().isEmpty() 
                            ? viaje.get_conductor().get_correo() 
                            : "N/A",

                        viaje.get_conductor().get_telefono() != null && !viaje.get_conductor().get_telefono().isEmpty() 
                            ? viaje.get_conductor().get_telefono() 
                            : "N/A",

                        viaje.get_conductor().get_placa_vehiculo() != null && !viaje.get_conductor().get_placa_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_placa_vehiculo()
                            : "N/A",

                        viaje.get_conductor().get_modelo_vehiculo() != null && !viaje.get_conductor().get_modelo_vehiculo().isEmpty()
                            ? viaje.get_conductor().get_modelo_vehiculo()
                            : "N/A"
                    });
        }

        // Actualizar la tabla
        datos_conductor.setModel(modeloConductor);

        // Si no hay conductores, mostrar mensaje
        if (modeloConductor.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron conductores.", 
                "Sin Conductores", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Buscar_cActionPerformed

    private void filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarActionPerformed
        PasajeroComprarFiltrar filtrarViaje = new PasajeroComprarFiltrar(st, pasajero, this);
        filtrarViaje.setVisible(true);
    }//GEN-LAST:event_filtrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_c;
    private javax.swing.JButton Comprar_Boleto;
    public javax.swing.JComboBox<String> ID_c;
    public javax.swing.JComboBox<String> ID_viajes;
    private javax.swing.JButton Terminar_Compra;
    public javax.swing.JTable datos_conductor;
    private javax.swing.JButton filtrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}