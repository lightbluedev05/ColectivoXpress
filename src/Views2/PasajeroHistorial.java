/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.Boleto;
import Models.Pasajero;
import Models.Viaje;
import Repository.BoletoRepository;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Mihae
 */
public class PasajeroHistorial extends javax.swing.JPanel {

    private Pasajero pasajero;
    private Statement st;
    
    public PasajeroHistorial(Pasajero pasajero, Statement st) {
        this.st = st;
    this.pasajero = pasajero;
    
    List<Viaje> viajes = pasajero.ver_historial_viajes(st);
    
    initComponents();
    correcciones_iniciales();
    listar_boletos();
    listar_boxboletos();
    listar_viajes_conductores();
    tabla_boletos.setDefaultRenderer(Object.class, new EstadoBoletoRenderer());
}
    
    private void correcciones_iniciales(){ 
        tabla_boletos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_boletos.setBackground(new Color(230, 230, 230));
    }
    
    private void listar_boletos() {
        DefaultTableModel modelo = (DefaultTableModel) tabla_boletos.getModel();
        modelo.setRowCount(0);      

        // Obtener el historial de viajes
        List<Viaje> viajes = pasajero.ver_historial_viajes(st); 

        // Verificar si la lista de viajes está vacía
        if (viajes.isEmpty()) {
            System.out.println("No hay historial de viajes disponible.");

            JOptionPane.showMessageDialog(this, 
                "No tiene viajes en su historial.", 
                "Sin Viajes", 
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        // Obtener la lista de boletos
        List<Boleto> boletos = new BoletoRepository(st).listar();

        // Si boletos es null, inicializar como lista vacía
        if (boletos == null) {
            boletos = new ArrayList<>();
        }

        // Procesar los viajes y boletos
        for (Viaje viaje : viajes) {
            String idBoleto = "N/A";

            for (Boleto boleto : boletos) {
                if (boleto != null && 
                    boleto.get_viaje() != null && 
                    boleto.get_viaje().get_id_viaje().equals(viaje.get_id_viaje()) && 
                    boleto.get_pasajero().get_dni().equals(pasajero.get_dni())) {
                    idBoleto = boleto.get_id_boleto();
                    break;
                }
            }
            
        

         String estadoTexto = viaje.get_estado() ? "Disponible" : "Terminado"; // Convertir booleano a texto

            modelo.addRow(new Object[]{
                idBoleto, // ID Boleto
                estadoTexto
            });
        }   

        // Si no se agregaron filas, mostrar mensaje
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron viajes en su historial.", 
                "Sin Viajes", 
                JOptionPane.INFORMATION_MESSAGE);
        }

        tabla_boletos.setModel(modelo);
    }
    
    
    private void listar_boxboletos() {
        // Limpiar el combo box de IDs de boletos
        box_boletos.removeAllItems();
        box_boletos.addItem("Todos"); // Añadir opción "Todos" como primera opción

        // Obtener la lista de boletos del pasajero
        List<Boleto> boletos = new BoletoRepository(st).listar();

        if (boletos == null || boletos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No tiene boletos disponibles.", 
                "Sin Boletos", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Filtrar y agregar solo los boletos del pasajero actual
        for (Boleto boleto : boletos) {
            if (boleto != null && 
                boleto.get_pasajero() != null && 
                boleto.get_pasajero().get_dni().equals(pasajero.get_dni())) {
                box_boletos.addItem(boleto.get_id_boleto());
            }
        }
    }
    
    private void listar_viajes_conductores() {
    // Limpiar tablas
    DefaultTableModel modeloViaje = (DefaultTableModel) tabla_viajes.getModel();
    DefaultTableModel modeloConductor = (DefaultTableModel) tabla_conductor.getModel();
    modeloViaje.setRowCount(0);
    modeloConductor.setRowCount(0);

    // Obtener historial de viajes
    List<Viaje> viajes = pasajero.ver_historial_viajes(st);
    List<Boleto> boletos = new BoletoRepository(st).listar();

    // Llenar tablas con todos los viajes del pasajero
    for (Viaje viaje : viajes) {
        // Buscar el boleto correspondiente
        Boleto boletoAsociado = null;
        for (Boleto boleto : boletos) {
            if (boleto.get_viaje().get_id_viaje().equals(viaje.get_id_viaje()) && 
                boleto.get_pasajero().get_dni().equals(pasajero.get_dni())) {
                boletoAsociado = boleto;
                break;
            }
        }

        LocalTime horaSalida = viaje.get_hora_salida();
        String horaSalidaString = horaSalida.toString();

        if (boletoAsociado != null) {
            String estadoTexto = viaje.get_estado() ? "Disponible" : "Terminado";

            // Llenar tabla de viajes
            modeloViaje.addRow(new Object[]{
                boletoAsociado.get_id_boleto(), // ID Boleto
                viaje.get_id_viaje(),   // ID Viaje
                viaje.get_fecha_salida(),
                viaje.get_ruta().get_origen(),
                viaje.get_ruta().get_destino(),
                horaSalidaString,
                viaje.get_ruta().get_precio(), // Precio
                estadoTexto
            });

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
    }

    // Verificar si se encontraron viajes
    if (viajes.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No se encontraron viajes en su historial.", 
            "Sin Viajes", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
       
    
    public class EstadoBoletoRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
                                                   boolean isSelected, boolean hasFocus, 
                                                   int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Obtener el estado de la segunda columna (índice 1)
        String estado = table.getModel().getValueAt(row, 1).toString();
        
        // Definir colores
        if ("Terminado".equals(estado)) {
            c.setBackground(new Color(255,153,153)); // Rojo suave
            c.setForeground(Color.BLACK);
        } else if ("Disponible".equals(estado)) {
            c.setBackground(new Color(204,255,204)); // Verde suave
            c.setForeground(Color.BLACK);
        } else {
            // Color por defecto si no coincide con ningún estado
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
        }
        
        return c;
    }
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_boletos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_conductor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Buscar_viaje = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        box_boletos = new javax.swing.JComboBox<>();
        filtrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("Detalles del Viaje");

        tabla_viajes.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Boleto", "ID Viaje", "Fecha", "Origen", "Destino", "Hora de Salida (HH:MM)", "Precio", "Estado"
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
        jScrollPane3.setViewportView(tabla_viajes);
        if (tabla_viajes.getColumnModel().getColumnCount() > 0) {
            tabla_viajes.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_viajes.getColumnModel().getColumn(1).setMaxWidth(80);
            tabla_viajes.getColumnModel().getColumn(2).setMaxWidth(100);
            tabla_viajes.getColumnModel().getColumn(3).setMaxWidth(80);
            tabla_viajes.getColumnModel().getColumn(4).setMaxWidth(80);
            tabla_viajes.getColumnModel().getColumn(5).setMaxWidth(400);
            tabla_viajes.getColumnModel().getColumn(6).setMaxWidth(50);
            tabla_viajes.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Listado de Boletos ");

        tabla_boletos.setBackground(new java.awt.Color(240, 245, 247));
        tabla_boletos.setForeground(new java.awt.Color(22, 38, 35));
        tabla_boletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Boleto", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_boletos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabla_boletos);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 23, 23));
        jLabel3.setText("Detalles del Conductor");

        tabla_conductor.setBackground(new java.awt.Color(240, 245, 247));
        tabla_conductor.setForeground(new java.awt.Color(22, 38, 35));
        tabla_conductor.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_conductor.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tabla_conductor);
        if (tabla_conductor.getColumnModel().getColumnCount() > 0) {
            tabla_conductor.getColumnModel().getColumn(0).setMaxWidth(60);
            tabla_conductor.getColumnModel().getColumn(1).setMaxWidth(1000);
            tabla_conductor.getColumnModel().getColumn(2).setMaxWidth(500);
            tabla_conductor.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_conductor.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla_conductor.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 23, 23));
        jLabel4.setText("BUSCAR");

        Buscar_viaje.setBackground(new java.awt.Color(41, 82, 85));
        Buscar_viaje.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Buscar_viaje.setForeground(new java.awt.Color(240, 245, 247));
        Buscar_viaje.setText("Buscar");
        Buscar_viaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_viajeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 23, 23));
        jLabel5.setText("Por boleto");

        box_boletos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        box_boletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_boletosActionPerformed(evt);
            }
        });

        filtrar.setBackground(new java.awt.Color(0, 102, 102));
        filtrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        filtrar.setForeground(new java.awt.Color(240, 245, 247));
        filtrar.setText("Filtrar Busqueda");
        filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(box_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel4)))
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(filtrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(box_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(filtrar)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void box_boletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_boletosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_box_boletosActionPerformed

    private void Buscar_viajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_viajeActionPerformed
        // Limpiar tablas
        DefaultTableModel modeloViaje = (DefaultTableModel) tabla_viajes.getModel();
        DefaultTableModel modeloConductor = (DefaultTableModel) tabla_conductor.getModel();
        modeloViaje.setRowCount(0);
        modeloConductor.setRowCount(0);

        // Obtener los filtros
        String idBoleto = (String) box_boletos.getSelectedItem();

        // Lista para almacenar viajes filtrados
        List<Viaje> viajesFiltrados = new ArrayList<>();

        // Obtener historial de viajes
        List<Viaje> viajes = pasajero.ver_historial_viajes(st);
        List<Boleto> boletos = new BoletoRepository(st).listar();

        // Filtrar viajes
        for (Viaje viaje : viajes) {
            boolean cumpleFiltro = true;

            // Filtro por boleto
            if (idBoleto != null && !idBoleto.equals("Todos")) {
                boolean encontradoEnBoletos = false;
                for (Boleto boleto : boletos) {
                    if (boleto.get_id_boleto().equals(idBoleto) && 
                        boleto.get_viaje().get_id_viaje().equals(viaje.get_id_viaje())) {
                        encontradoEnBoletos = true;
                        break;
                    }
                }
                cumpleFiltro = cumpleFiltro && encontradoEnBoletos;
            }

            // Si cumple todos los filtros, agregar a la lista
            if (cumpleFiltro) {
                viajesFiltrados.add(viaje);
            }
        }

        // Llenar tablas con viajes filtrados
        for (Viaje viaje : viajesFiltrados) {
            // Buscar el boleto correspondiente
            Boleto boletoAsociado = null;
            for (Boleto boleto : boletos) {
                if (boleto.get_viaje().get_id_viaje().equals(viaje.get_id_viaje()) && 
                    boleto.get_pasajero().get_dni().equals(pasajero.get_dni())) {
                    boletoAsociado = boleto;
                    break;
                }
            }

            LocalTime horaSalida = viaje.get_hora_salida();
            String horaSalidaString = horaSalida.toString();

            if (boletoAsociado != null) {
                String estadoTexto = viaje.get_estado() ? "Disponible" : "Terminado";

                // Llenar tabla de viajes
                modeloViaje.addRow(new Object[]{
                    boletoAsociado.get_id_boleto(), // ID Boleto
                    viaje.get_id_viaje(),   // ID Viaje
                    viaje.get_fecha_salida(),
                    viaje.get_ruta().get_origen(),
                    viaje.get_ruta().get_destino(),
                    horaSalidaString,
                    viaje.get_ruta().get_precio(), // Asegúrate de incluir el precio
                    estadoTexto
                });

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
        }

        // Verificar si se encontraron viajes
        if (viajesFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron viajes con los filtros aplicados.", 
                "Sin Resultados", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Buscar_viajeActionPerformed

    private void filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarActionPerformed
        PasajeroHistorialFiltrar ventanaFecha = new PasajeroHistorialFiltrar(st, pasajero, this);
        ventanaFecha.setVisible(true);
    }//GEN-LAST:event_filtrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_viaje;
    private javax.swing.JComboBox<String> box_boletos;
    private javax.swing.JButton filtrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabla_boletos;
    public javax.swing.JTable tabla_conductor;
    public javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
