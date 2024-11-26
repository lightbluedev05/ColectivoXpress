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
    ID_boletos.removeAllItems();

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
            ID_boletos.addItem(boleto.get_id_boleto());
        }
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

        ID_boletos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_viajes1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        Buscar_viaje = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        datos_conductor = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        ID_boletos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ID_boletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_boletosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("Detalles del Viaje");

        tabla_viajes1.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes1.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_viajes1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabla_viajes1);
        if (tabla_viajes1.getColumnModel().getColumnCount() > 0) {
            tabla_viajes1.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_viajes1.getColumnModel().getColumn(2).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(3).setMaxWidth(80);
            tabla_viajes1.getColumnModel().getColumn(4).setMaxWidth(400);
            tabla_viajes1.getColumnModel().getColumn(5).setMaxWidth(280);
            tabla_viajes1.getColumnModel().getColumn(6).setMaxWidth(50);
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

        Buscar_viaje.setBackground(new java.awt.Color(41, 82, 85));
        Buscar_viaje.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Buscar_viaje.setForeground(new java.awt.Color(240, 245, 247));
        Buscar_viaje.setText("Buscar");
        Buscar_viaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_viajeActionPerformed(evt);
            }
        });

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
                "Nombre", "Correo", "Numero", "Placa", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            datos_conductor.getColumnModel().getColumn(0).setMaxWidth(1000);
            datos_conductor.getColumnModel().getColumn(1).setMaxWidth(500);
            datos_conductor.getColumnModel().getColumn(2).setMaxWidth(100);
            datos_conductor.getColumnModel().getColumn(3).setMaxWidth(100);
            datos_conductor.getColumnModel().getColumn(4).setMaxWidth(150);
        }

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
                        .addGap(257, 257, 257)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(ID_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID_boletos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar_viaje, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Buscar_viajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_viajeActionPerformed
            // Obtener el ID del boleto seleccionado
    String idBoleto = (String) ID_boletos.getSelectedItem();

    if (idBoleto == null) {
        JOptionPane.showMessageDialog(this, 
            "Seleccione un ID de boleto.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Buscar el boleto
    BoletoRepository boletoRepo = new BoletoRepository(st);
    Boleto boleto = boletoRepo.buscar(idBoleto);

    if (boleto == null) {
        JOptionPane.showMessageDialog(this, 
            "No se encontró el boleto.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Limpiar tablas
    DefaultTableModel modeloViaje = (DefaultTableModel) tabla_viajes1.getModel();
    DefaultTableModel modeloConductor = (DefaultTableModel) datos_conductor.getModel();
    modeloViaje.setRowCount(0);
    modeloConductor.setRowCount(0);

    // Obtener el viaje asociado al boleto
    Viaje viaje = boleto.get_viaje();

    if (viaje == null) {
        JOptionPane.showMessageDialog(this, 
            "No se encontró información del viaje.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Calcular duración del viaje
    Duration tiempoAprox = viaje.get_ruta().get_tiempo_aproximado(); 
    long horas = tiempoAprox.toHours();
    long minutos = tiempoAprox.toMinutesPart(); 
    String tiempo_string = String.format("%02d:%02d", horas, minutos);

    // Llenar tabla de viajes
    modeloViaje.addRow(new Object[]{
        viaje.get_id_viaje(),
        viaje.get_fecha_salida(),
        viaje.get_ruta().get_origen(),
        viaje.get_ruta().get_destino(),
        viaje.get_hora_salida().toString(),
        tiempo_string,
        viaje.get_ruta().get_precio()
    });

    // Llenar tabla de conductor
    if (viaje.get_conductor() != null) {
        modeloConductor.addRow(new Object[]{
            viaje.get_conductor().get_nombre() != null 
                ? viaje.get_conductor().get_nombre() 
                : "N/A",
            
            viaje.get_conductor().get_correo() != null 
                ? viaje.get_conductor().get_correo() 
                : "N/A",
            
            // Placeholders mientras se implementan los métodos
            "N/A", 
            "N/A", 
            "N/A"
        });
    }

    // Actualizar tablas
    tabla_viajes1.setModel(modeloViaje);
    datos_conductor.setModel(modeloConductor);
    }//GEN-LAST:event_Buscar_viajeActionPerformed

    private void ID_boletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_boletosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ID_boletosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_viaje;
    private javax.swing.JComboBox<String> ID_boletos;
    private javax.swing.JTable datos_conductor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla_viajes;
    private javax.swing.JTable tabla_viajes1;
    // End of variables declaration//GEN-END:variables
}
