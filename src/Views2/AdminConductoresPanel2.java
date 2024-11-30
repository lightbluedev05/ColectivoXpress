package Views2;

import Models.Admin;
import Models.Conductor;
import Repository.ConductorRepository;
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


public class AdminConductoresPanel2 extends javax.swing.JPanel {
    
    Admin admin;
    private Statement st;
    public AdminConductoresPanel2(Admin admin, Statement st) {
        this.st = st;
        this.admin = admin;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){
        dni_input.setBackground(new Color(240,245,247));
        nombre_input.setBackground(new Color(240,245,247));
        telefono_input.setBackground(new Color(240,245,247));
        distrito_input.setBackground(new Color(240,245,247));
        provincia_input.setBackground(new Color(240,245,247));
        departamento_input.setBackground(new Color(240,245,247));
        placa_vehiculo_input.setBackground(new Color(240,245,247));
        modelo_vehiculo_input.setBackground(new Color(240,245,247));
        
        tabla_conductores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla_conductores.setBackground(new Color(230, 230, 230));
        
        //AJUSTE DE COLUMNA AL DARLE CLICK A LA ETIQUETA
        tabla_conductores.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int column = tabla_conductores.columnAtPoint(e.getPoint());
                
                setColumnWidthToFitContent(tabla_conductores, column);
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
    
    
    private void listar_conductores(){
        DefaultTableModel modelo = (DefaultTableModel)tabla_conductores.getModel();
        modelo.setRowCount(0);
        
        String dni = dni_input.getText();
        String nombre = nombre_input.getText();
        String telefono = telefono_input.getText();
        String distrito = distrito_input.getText();
        String provincia = provincia_input.getText();
        String departamento = departamento_input.getText();
        String placa_vehiculo = placa_vehiculo_input.getText();
        String modelo_vehiculo = modelo_vehiculo_input.getText();
        
        List<Conductor> conductores = new ConductorRepository(st).buscar_pro(dni, nombre, distrito, provincia, departamento,
                telefono, placa_vehiculo, modelo_vehiculo);
        for(Conductor conductor:conductores){
            modelo.addRow(new Object[]{
                conductor.get_dni(),
                conductor.get_nombre(),
                conductor.get_correo(),
                conductor.get_fecha_nacimiento(),
                conductor.get_distrito(),
                conductor.get_provincia(),
                conductor.get_departamento(),
                conductor.get_dias_descanso(),
                conductor.get_capacidad_vehiculo(),
                conductor.get_telefono(),
                conductor.get_placa_vehiculo(),
                conductor.get_modelo_vehiculo()
            });
        }
        
        tabla_conductores.setModel(modelo);
    }
    
    private void eliminar_conductor(){
        int fila_seleccionada = tabla_conductores.getSelectedRow();
        
        if(fila_seleccionada == -1){
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String dni = tabla_conductores.getValueAt(1, 0).toString();
        
        boolean exito = admin.eliminar_conductor(dni,  st);
        if(!exito){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Conductor Eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
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

        eliminar_conductor_button = new javax.swing.JButton();
        agregar_conductor_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_conductores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        listar_conductores_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dni_input = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombre_input = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        distrito_input = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        provincia_input = new javax.swing.JTextField();
        telefono_input = new javax.swing.JTextField();
        placa_vehiculo_input = new javax.swing.JTextField();
        departamento_input = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        modelo_vehiculo_input = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        limpiar_filtros_button = new javax.swing.JButton();
        editar_conductor_button = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        eliminar_conductor_button.setBackground(new java.awt.Color(41, 82, 85));
        eliminar_conductor_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        eliminar_conductor_button.setForeground(new java.awt.Color(240, 245, 247));
        eliminar_conductor_button.setText("Eliminar Conductor");
        eliminar_conductor_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar_conductor_button.setMaximumSize(new java.awt.Dimension(161, 40));
        eliminar_conductor_button.setMinimumSize(new java.awt.Dimension(161, 40));
        eliminar_conductor_button.setPreferredSize(new java.awt.Dimension(161, 40));
        eliminar_conductor_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_conductor_buttonActionPerformed(evt);
            }
        });

        agregar_conductor_button.setBackground(new java.awt.Color(41, 82, 85));
        agregar_conductor_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        agregar_conductor_button.setForeground(new java.awt.Color(240, 245, 247));
        agregar_conductor_button.setText("Agregar Conductor");
        agregar_conductor_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar_conductor_button.setMaximumSize(new java.awt.Dimension(161, 40));
        agregar_conductor_button.setMinimumSize(new java.awt.Dimension(161, 40));
        agregar_conductor_button.setPreferredSize(new java.awt.Dimension(161, 40));
        agregar_conductor_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_conductor_buttonActionPerformed(evt);
            }
        });

        tabla_conductores.setBackground(new java.awt.Color(240, 245, 247));
        tabla_conductores.setForeground(new java.awt.Color(22, 38, 35));
        tabla_conductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Correo", "Fecha Nacimiento", "Distrito", "Provincia", "Departamento", "Dias Descanso", "Capacidad Vehiculo", "Telefono", "Placa Vehiculo", "Modelo Vehiculo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_conductores);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("MÓDULO DE CONDUCTORES");
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        listar_conductores_button.setBackground(new java.awt.Color(41, 82, 85));
        listar_conductores_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listar_conductores_button.setForeground(new java.awt.Color(240, 245, 247));
        listar_conductores_button.setText("Listar Conductores");
        listar_conductores_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar_conductores_button.setMaximumSize(new java.awt.Dimension(170, 40));
        listar_conductores_button.setPreferredSize(new java.awt.Dimension(120, 20));
        listar_conductores_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar_conductores_buttonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 49;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 19, 47, 6);
        jPanel3.add(listar_conductores_button, gridBagConstraints);

        jLabel1.setText("DNI");

        jLabel3.setText("Nombre");

        jLabel5.setText("Distrito");

        jLabel6.setText("Telefono");

        jLabel7.setText("Provincia");

        jLabel8.setText("Placa Vehiculo");

        jLabel9.setText("Departamento");

        jLabel10.setText("Modelo Vehiculo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(dni_input, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(nombre_input))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(distrito_input))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(72, 72, 72))
                            .addComponent(provincia_input)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 135, Short.MAX_VALUE))
                            .addComponent(telefono_input))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(departamento_input, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(placa_vehiculo_input, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(modelo_vehiculo_input))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departamento_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(provincia_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telefono_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dni_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(distrito_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(placa_vehiculo_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modelo_vehiculo_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        editar_conductor_button.setBackground(new java.awt.Color(41, 82, 85));
        editar_conductor_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editar_conductor_button.setForeground(new java.awt.Color(240, 245, 247));
        editar_conductor_button.setText("Editar Conductor");
        editar_conductor_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar_conductor_button.setMaximumSize(new java.awt.Dimension(161, 40));
        editar_conductor_button.setMinimumSize(new java.awt.Dimension(161, 40));
        editar_conductor_button.setPreferredSize(new java.awt.Dimension(161, 40));
        editar_conductor_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_conductor_buttonActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregar_conductor_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar_conductor_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminar_conductor_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
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
                        .addGap(257, 257, 257)
                        .addComponent(agregar_conductor_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(editar_conductor_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(eliminar_conductor_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listar_conductores_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar_conductores_buttonActionPerformed
        listar_conductores();
    }//GEN-LAST:event_listar_conductores_buttonActionPerformed

    private void eliminar_conductor_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_conductor_buttonActionPerformed
        eliminar_conductor();
    }//GEN-LAST:event_eliminar_conductor_buttonActionPerformed

    private void agregar_conductor_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_conductor_buttonActionPerformed

        AdminConductorCrear crear_conductor = new AdminConductorCrear(admin, st);
        crear_conductor.setVisible(true);
    }//GEN-LAST:event_agregar_conductor_buttonActionPerformed

    private void editar_conductor_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_conductor_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editar_conductor_buttonActionPerformed

    private void limpiar_filtros_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_filtros_buttonActionPerformed
        
        dni_input.setText("");
        nombre_input.setText("");
        telefono_input.setText("");
        distrito_input.setText("");
        provincia_input.setText("");
        departamento_input.setText("");
        placa_vehiculo_input.setText("");
        modelo_vehiculo_input.setText("");
        
    }//GEN-LAST:event_limpiar_filtros_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_conductor_button;
    private javax.swing.JTextField departamento_input;
    private javax.swing.JTextField distrito_input;
    private javax.swing.JTextField dni_input;
    private javax.swing.JButton editar_conductor_button;
    private javax.swing.JButton eliminar_conductor_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limpiar_filtros_button;
    private javax.swing.JButton listar_conductores_button;
    private javax.swing.JTextField modelo_vehiculo_input;
    private javax.swing.JTextField nombre_input;
    private javax.swing.JTextField placa_vehiculo_input;
    private javax.swing.JTextField provincia_input;
    private javax.swing.JTable tabla_conductores;
    private javax.swing.JTextField telefono_input;
    // End of variables declaration//GEN-END:variables
}
