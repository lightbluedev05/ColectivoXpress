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
import java.time.Duration;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
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
    listar_viajes();
    listarBoletos();
    cargarOrigenes();  
    cargarDestinos();  
    tabla_viajes.setDefaultRenderer(Object.class, new EstadoBoletoRenderer());
}
    
    private void correcciones_iniciales(){ 
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
    }
    
    private void listar_viajes() {
        DefaultTableModel modelo = (DefaultTableModel) tabla_viajes.getModel();
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

        tabla_viajes.setModel(modelo);
    }
    
    
    private void listarBoletos() {
        // Limpiar el combo box de IDs de boletos
        box_boletos.removeAllItems();
        box_boletos.addItem(null); // Añadir elemento nulo como primera opción

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
    
        private void cargarOrigenes() {
            box_origen.removeAllItems();
            box_origen.addItem(null); // Añadir elemento nulo como primera opción

            List<Viaje> viajes = pasajero.ver_historial_viajes(st);
            Set<String> origenes = new HashSet<>();

            for (Viaje viaje : viajes) {
                if (viaje.get_ruta() != null && viaje.get_ruta().get_origen() != null) {
                    origenes.add(viaje.get_ruta().get_origen());
                }
            }

            for (String origen : origenes) {
                box_origen.addItem(origen);
            }
        }

        private void cargarDestinos() {
            box_destino.removeAllItems();
            box_destino.addItem(null); // Añadir elemento nulo como primera opción

            List<Viaje> viajes = pasajero.ver_historial_viajes(st);
            Set<String> destinos = new HashSet<>();

            for (Viaje viaje : viajes) {
                if (viaje.get_ruta() != null && viaje.get_ruta().get_destino() != null) {
                    destinos.add(viaje.get_ruta().get_destino());
                }
            }

            for (String destino : destinos) {
                box_destino.addItem(destino);
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
        tabla_viajes1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        datos_conductor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Buscar_viaje = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        box_boletos = new javax.swing.JComboBox<>();
        box_origen = new javax.swing.JComboBox<>();
        box_destino = new javax.swing.JComboBox<>();
        fecha_buscar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("Detalles del Viaje");

        tabla_viajes1.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes1.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Boleto", "ID Viaje", "Fecha", "Origen", "Destino", "Precio", "Estado"
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
        tabla_viajes1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabla_viajes1);
        if (tabla_viajes1.getColumnModel().getColumnCount() > 0) {
            tabla_viajes1.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(1).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(2).setMaxWidth(100);
            tabla_viajes1.getColumnModel().getColumn(3).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(4).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(5).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(23, 23, 23));
        jLabel1.setText("Listado de Viajes ");

        tabla_viajes.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_viajes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabla_viajes);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 23, 23));
        jLabel3.setText("Detalles del Conductor");

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

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(23, 23, 23));
        jLabel6.setText("Por origen");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(23, 23, 23));
        jLabel7.setText("Por destino");

        box_boletos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        box_boletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_boletosActionPerformed(evt);
            }
        });

        box_origen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        box_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_origenActionPerformed(evt);
            }
        });

        box_destino.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        box_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_destinoActionPerformed(evt);
            }
        });

        fecha_buscar.setBackground(new java.awt.Color(41, 82, 85));
        fecha_buscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        fecha_buscar.setForeground(new java.awt.Color(240, 245, 247));
        fecha_buscar.setText("Por Fecha");
        fecha_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fecha_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(box_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(box_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(box_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(box_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(box_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(box_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(fecha_buscar)
                .addGap(14, 14, 14))
        );

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
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void box_boletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_boletosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_box_boletosActionPerformed

    private void Buscar_viajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_viajeActionPerformed
        // Limpiar tablas
        DefaultTableModel modeloViaje = (DefaultTableModel) tabla_viajes1.getModel();
        DefaultTableModel modeloConductor = (DefaultTableModel) datos_conductor.getModel();
        modeloViaje.setRowCount(0);
        modeloConductor.setRowCount(0);

        // Obtener los filtros
        String idBoleto = (String) box_boletos.getSelectedItem();
        String origen = (String) box_origen.getSelectedItem();
        String destino = (String) box_destino.getSelectedItem();

        // Lista para almacenar viajes filtrados
        List<Viaje> viajesFiltrados = new ArrayList<>();

        // Obtener historial de viajes
        List<Viaje> viajes = pasajero.ver_historial_viajes(st);
        List<Boleto> boletos = new BoletoRepository(st).listar();

        // Filtrar viajes
        for (Viaje viaje : viajes) {
            boolean cumpleFiltro = true;

            // Filtro por boleto
            if (idBoleto != null) {
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

            // Filtro por origen
            if (origen != null) {
                cumpleFiltro = cumpleFiltro && viaje.get_ruta().get_origen().equals(origen);
            }

            // Filtro por destino
            if (destino != null) {
                cumpleFiltro = cumpleFiltro && viaje.get_ruta().get_destino().equals(destino);
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

            if (boletoAsociado != null) {
                String estadoTexto = viaje.get_estado() ? "Disponible" : "Terminado";

                // Llenar tabla de viajes
                modeloViaje.addRow(new Object[]{
                    boletoAsociado.get_id_boleto(), // ID Boleto
                    viaje.get_id_viaje(),   // ID Viaje
                    viaje.get_fecha_salida(),
                    viaje.get_ruta().get_origen(),
                    viaje.get_ruta().get_destino(),
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

    private void box_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_box_destinoActionPerformed

    private void box_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_box_origenActionPerformed

    private void fecha_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_buscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_viaje;
    private javax.swing.JComboBox<String> box_boletos;
    private javax.swing.JComboBox<String> box_destino;
    private javax.swing.JComboBox<String> box_origen;
    private javax.swing.JTable datos_conductor;
    private javax.swing.JButton fecha_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabla_viajes;
    private javax.swing.JTable tabla_viajes1;
    // End of variables declaration//GEN-END:variables
}
