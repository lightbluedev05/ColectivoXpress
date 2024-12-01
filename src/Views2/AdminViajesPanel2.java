package Views2;

import Models.Admin;
import Models.Conductor;
import Models.Viaje;
import Repository.ConductorRepository;
import Repository.ViajeRepository;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import kotlin.random.Random;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class AdminViajesPanel2 extends javax.swing.JPanel {
    
    Admin admin;
    private Statement st;
    public AdminViajesPanel2(Admin admin, Statement st) {
        this.st = st;
        this.admin = admin;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){
        id_viaje_input.setBackground(new Color(240,245,247));
        origen_input.setBackground(new Color(240,245,247));
        fecha_input.setBackground(new Color(240,245,247));
        destino_input.setBackground(new Color(240,245,247));
        dni_conductor_input.setBackground(new Color(240,245,247));
        
        //tabla_viajes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla_viajes.setBackground(new Color(230, 230, 230));
        
        //AJUSTE DE COLUMNA AL DARLE CLICK A LA ETIQUETA
        tabla_viajes.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int column = tabla_viajes.columnAtPoint(e.getPoint());
                
                setColumnWidthToFitContent(tabla_viajes, column);
            }
        });
        
    }
    
    private void setColumnWidthToFitContent(JTable table, int columnIndex) {
        int maxWidth = 0;

        // Obtiene el modelo de la tabla
        TableModel model = table.getModel();

        // Recorre todas las filas y calcula el ancho máximo de la columna
        for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, columnIndex);
            // Usa el renderizador de celdas para obtener el ancho preferido de cada celda
            int cellWidth = table.getCellRenderer(row, columnIndex)
                                   .getTableCellRendererComponent(table, value, false, false, row, columnIndex)
                                   .getPreferredSize().width;

            maxWidth = Math.max(maxWidth, cellWidth);
        }

        // Agrega un pequeño margen al ancho para mejorar la visualización
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(maxWidth + 5);
    }
    
    
    private void listar_viajes(){
        DefaultTableModel modelo = (DefaultTableModel)tabla_viajes.getModel();
        modelo.setRowCount(0);
        
        String id_viaje = id_viaje_input.getText();
        String origen = origen_input.getText();
        String destino = destino_input.getText();
        String fecha = fecha_input.getText();
        String dni_conductor = dni_conductor_input.getText();
        String estado = estado_input.getSelectedItem().toString();
        
        
        List<Viaje> viajes = new ViajeRepository(st).buscar_pro(id_viaje, origen, destino, fecha, dni_conductor, estado);
        
        for(Viaje viaje:viajes){
            String estado_str;
            if(viaje.get_estado()){
                estado_str = "Activo";
            } else {
                estado_str = "Terminado";
            }
            
            long horas = viaje.get_ruta().get_tiempo_aproximado().toHours();
            long minutos = viaje.get_ruta().get_tiempo_aproximado().toMinutesPart();
            String tiempo_aprox = String.format("%02d:%02d", horas, minutos);
            
            modelo.addRow(new Object[]{
                estado_str,
                viaje.get_id_viaje(),
                viaje.get_ruta().get_origen(),
                viaje.get_ruta().get_destino(),
                viaje.get_conductor().get_dni(),
                viaje.get_conductor().get_nombre(),
                tiempo_aprox,
                viaje.get_fecha_salida(),
                viaje.get_hora_salida(),
                viaje.get_ruta().get_precio()
            });
        }
        
        tabla_viajes.setModel(modelo);
    }
    
    private void eliminar_viaje(){
        int fila_seleccionada = tabla_viajes.getSelectedRow();
        
        if(fila_seleccionada == -1){
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id_viaje = tabla_viajes.getValueAt(1, 0).toString();
        
        boolean exito = admin.eliminar_viaje(id_viaje,  st);
        if(!exito){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((DefaultTableModel) tabla_viajes.getModel()).removeRow(fila_seleccionada);
        JOptionPane.showMessageDialog(null, "Viaje Eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        eliminar_viaje_button = new javax.swing.JButton();
        agregar_viaje_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        listar_viajes_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_viaje_input = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        origen_input = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        destino_input = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fecha_input = new javax.swing.JTextField();
        dni_conductor_input = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        estado_input = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        limpiar_filtros_button = new javax.swing.JButton();
        editar_viaje_button = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        eliminar_viaje_button.setBackground(new java.awt.Color(41, 82, 85));
        eliminar_viaje_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        eliminar_viaje_button.setForeground(new java.awt.Color(240, 245, 247));
        eliminar_viaje_button.setText("Eliminar Viaje");
        eliminar_viaje_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_viaje_buttonActionPerformed(evt);
            }
        });

        agregar_viaje_button.setBackground(new java.awt.Color(41, 82, 85));
        agregar_viaje_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        agregar_viaje_button.setForeground(new java.awt.Color(240, 245, 247));
        agregar_viaje_button.setText("Agregar Viaje");
        agregar_viaje_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_viaje_buttonActionPerformed(evt);
            }
        });

        tabla_viajes.setBackground(new java.awt.Color(240, 245, 247));
        tabla_viajes.setForeground(new java.awt.Color(22, 38, 35));
        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estado", "ID Viaje", "Origen", "Destino", "DNI Conductor", "Nombre Conductor", "Tiempo aproximado", "Fecha de Salida", "Hora de Salida", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_viajes);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("MÓDULO DE VIAJES");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(216, 22));
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        listar_viajes_button.setBackground(new java.awt.Color(41, 82, 85));
        listar_viajes_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listar_viajes_button.setForeground(new java.awt.Color(240, 245, 247));
        listar_viajes_button.setText("Listar Viajes");
        listar_viajes_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar_viajes_button.setMaximumSize(new java.awt.Dimension(170, 40));
        listar_viajes_button.setPreferredSize(new java.awt.Dimension(120, 20));
        listar_viajes_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar_viajes_buttonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 49;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 19, 47, 6);
        jPanel3.add(listar_viajes_button, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID Viaje");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Origen");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Destino");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha (aaaa-mm-dd)");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Estado");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DNI Conductor");

        estado_input.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Activo", "Terminado" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(origen_input)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id_viaje_input))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fecha_input, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addComponent(destino_input)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(dni_conductor_input)
                    .addComponent(estado_input, 0, 177, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dni_conductor_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_viaje_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(origen_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(destino_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        limpiar_filtros_button.setBackground(new java.awt.Color(41, 82, 85));
        limpiar_filtros_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        limpiar_filtros_button.setForeground(new java.awt.Color(240, 245, 247));
        limpiar_filtros_button.setText("Limpiar Filtros");
        limpiar_filtros_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar_filtros_button.setMaximumSize(new java.awt.Dimension(170, 40));
        limpiar_filtros_button.setPreferredSize(new java.awt.Dimension(120, 40));
        limpiar_filtros_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar_filtros_buttonActionPerformed(evt);
            }
        });
        jPanel5.add(limpiar_filtros_button, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editar_viaje_button.setBackground(new java.awt.Color(41, 82, 85));
        editar_viaje_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editar_viaje_button.setForeground(new java.awt.Color(240, 245, 247));
        editar_viaje_button.setText("Editar Viaje");
        editar_viaje_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        editar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        editar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        editar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_viaje_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(agregar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(eliminar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(editar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listar_viajes_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar_viajes_buttonActionPerformed
        listar_viajes();
    }//GEN-LAST:event_listar_viajes_buttonActionPerformed

    private void eliminar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_viaje_buttonActionPerformed
        eliminar_viaje();
    }//GEN-LAST:event_eliminar_viaje_buttonActionPerformed

    private void agregar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_viaje_buttonActionPerformed
        AdminViajesCrear crear_viaje = new AdminViajesCrear(admin, st);
        crear_viaje.setVisible(true);
    }//GEN-LAST:event_agregar_viaje_buttonActionPerformed

    private void limpiar_filtros_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_filtros_buttonActionPerformed

        id_viaje_input.setText("");
        origen_input.setText("");
        fecha_input.setText("");
        destino_input.setText("");
        dni_conductor_input.setText("");
        estado_input.setSelectedIndex(0);
    }//GEN-LAST:event_limpiar_filtros_buttonActionPerformed

    private void editar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_viaje_buttonActionPerformed
        int fila_seleccionada = tabla_viajes.getSelectedRow();
        
        if(fila_seleccionada == -1){
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id_viaje = tabla_viajes.getValueAt(1, 1).toString();
        
        AdminViajesEditar editar_viaje = new AdminViajesEditar(admin, id_viaje, st);
        editar_viaje.setVisible(true);
    }//GEN-LAST:event_editar_viaje_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_viaje_button;
    private javax.swing.JTextField destino_input;
    private javax.swing.JTextField dni_conductor_input;
    private javax.swing.JButton editar_viaje_button;
    private javax.swing.JButton eliminar_viaje_button;
    private javax.swing.JComboBox<String> estado_input;
    private javax.swing.JTextField fecha_input;
    private javax.swing.JTextField id_viaje_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limpiar_filtros_button;
    private javax.swing.JButton listar_viajes_button;
    private javax.swing.JTextField origen_input;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
