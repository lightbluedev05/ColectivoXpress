package Views2;

import Models.Admin;
import Models.Conductor;
import Models.Ruta;
import Repository.ConductorRepository;
import Repository.RutaRepository;
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


public class AdminRutasPanel2 extends javax.swing.JPanel {
    
    Admin admin;
    private Statement st;
    public AdminRutasPanel2(Admin admin, Statement st) {
        this.st = st;
        this.admin = admin;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){
        id_ruta_input.setBackground(new Color(240,245,247));
        origen_ruta_input.setBackground(new Color(240,245,247));
        destino_ruta_input.setBackground(new Color(240,245,247));
        
        //tabla_rutas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla_rutas.setBackground(new Color(230, 230, 230));
        
        //AJUSTE DE COLUMNA AL DARLE CLICK A LA ETIQUETA
        tabla_rutas.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int column = tabla_rutas.columnAtPoint(e.getPoint());
                
                setColumnWidthToFitContent(tabla_rutas, column);
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
    
    
    private void listar_rutas(){
        DefaultTableModel modelo = (DefaultTableModel)tabla_rutas.getModel();
        modelo.setRowCount(0);
        
        String id_ruta = id_ruta_input.getText();
        String origen_ruta = origen_ruta_input.getText();
        String destino_ruta = destino_ruta_input.getText();
        
        List<Ruta> rutas = new RutaRepository(st).buscar_pro(id_ruta, origen_ruta, destino_ruta);
        for(Ruta ruta:rutas){
            
            long horas = ruta.get_tiempo_aproximado().toHours();
            long minutos = ruta.get_tiempo_aproximado().toMinutesPart();
            String tiempo_aproximado = String.format("%02d:%02d", horas, minutos);
            
            modelo.addRow(new Object[]{
                ruta.get_id_ruta(),
                ruta.get_origen(),
                ruta.get_destino(),
                tiempo_aproximado,
                ruta.get_precio(),
            });
        }
        
        tabla_rutas.setModel(modelo);
    }
    
    private void eliminar_ruta(){
        int fila_seleccionada = tabla_rutas.getSelectedRow();
        
        if(fila_seleccionada == -1){
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id_ruta = tabla_rutas.getValueAt(1, 0).toString();
        
        boolean exito = admin.eliminar_ruta(id_ruta,  st);
        if(!exito){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ((DefaultTableModel) tabla_rutas.getModel()).removeRow(fila_seleccionada);
        JOptionPane.showMessageDialog(null, "Ruta Eliminada", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void editar_ruta(){
        int fila_seleccionada = tabla_rutas.getSelectedRow();
        
        if(fila_seleccionada == -1){
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id_ruta = tabla_rutas.getValueAt(fila_seleccionada, 0).toString();
        
        AdminRutasEditar editar_ruta = new AdminRutasEditar(admin, id_ruta, st);
        editar_ruta.setVisible(true);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eliminar_ruta_button = new javax.swing.JButton();
        agregar_ruta_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_rutas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_ruta_input = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        origen_ruta_input = new javax.swing.JTextField();
        destino_ruta_input = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        listar_rutas_button = new javax.swing.JButton();
        limpiar_filtros_button = new javax.swing.JButton();
        editar_ruta_button = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        eliminar_ruta_button.setBackground(new java.awt.Color(41, 82, 85));
        eliminar_ruta_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        eliminar_ruta_button.setForeground(new java.awt.Color(240, 245, 247));
        eliminar_ruta_button.setText("Eliminar Ruta");
        eliminar_ruta_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar_ruta_button.setMaximumSize(new java.awt.Dimension(161, 40));
        eliminar_ruta_button.setMinimumSize(new java.awt.Dimension(161, 40));
        eliminar_ruta_button.setPreferredSize(new java.awt.Dimension(161, 40));
        eliminar_ruta_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_ruta_buttonActionPerformed(evt);
            }
        });

        agregar_ruta_button.setBackground(new java.awt.Color(41, 82, 85));
        agregar_ruta_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        agregar_ruta_button.setForeground(new java.awt.Color(240, 245, 247));
        agregar_ruta_button.setText("Agregar Ruta");
        agregar_ruta_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar_ruta_button.setMaximumSize(new java.awt.Dimension(161, 40));
        agregar_ruta_button.setMinimumSize(new java.awt.Dimension(161, 40));
        agregar_ruta_button.setPreferredSize(new java.awt.Dimension(161, 40));
        agregar_ruta_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_ruta_buttonActionPerformed(evt);
            }
        });

        tabla_rutas.setBackground(new java.awt.Color(240, 245, 247));
        tabla_rutas.setForeground(new java.awt.Color(22, 38, 35));
        tabla_rutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ruta", "Origen", "Destino", "Tiempo Aproximado", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_rutas);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("MÓDULO DE RUTAS");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID Ruta");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Origen");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Destino");

        listar_rutas_button.setBackground(new java.awt.Color(41, 82, 85));
        listar_rutas_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listar_rutas_button.setForeground(new java.awt.Color(240, 245, 247));
        listar_rutas_button.setText("Listar Rutas");
        listar_rutas_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar_rutas_button.setMaximumSize(new java.awt.Dimension(170, 40));
        listar_rutas_button.setPreferredSize(new java.awt.Dimension(120, 20));
        listar_rutas_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar_rutas_buttonActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id_ruta_input)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(origen_ruta_input, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(destino_ruta_input, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(listar_rutas_button, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(limpiar_filtros_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listar_rutas_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(limpiar_filtros_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(destino_ruta_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(origen_ruta_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(id_ruta_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );

        editar_ruta_button.setBackground(new java.awt.Color(41, 82, 85));
        editar_ruta_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        editar_ruta_button.setForeground(new java.awt.Color(240, 245, 247));
        editar_ruta_button.setText("Editar Ruta");
        editar_ruta_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar_ruta_button.setMaximumSize(new java.awt.Dimension(161, 40));
        editar_ruta_button.setMinimumSize(new java.awt.Dimension(161, 40));
        editar_ruta_button.setPreferredSize(new java.awt.Dimension(161, 40));
        editar_ruta_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_ruta_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(agregar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(eliminar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(editar_ruta_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listar_rutas_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar_rutas_buttonActionPerformed
        listar_rutas();
    }//GEN-LAST:event_listar_rutas_buttonActionPerformed

    private void eliminar_ruta_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_ruta_buttonActionPerformed
        eliminar_ruta();
    }//GEN-LAST:event_eliminar_ruta_buttonActionPerformed

    private void agregar_ruta_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_ruta_buttonActionPerformed
        AdminRutasCrear crear_ruta = new AdminRutasCrear(admin, st);
        crear_ruta.setVisible(true);
    }//GEN-LAST:event_agregar_ruta_buttonActionPerformed

    private void limpiar_filtros_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_filtros_buttonActionPerformed
        
        id_ruta_input.setText("");
        origen_ruta_input.setText("");
        destino_ruta_input.setText("");
        
    }//GEN-LAST:event_limpiar_filtros_buttonActionPerformed

    private void editar_ruta_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_ruta_buttonActionPerformed
        
        editar_ruta();
    }//GEN-LAST:event_editar_ruta_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_ruta_button;
    private javax.swing.JTextField destino_ruta_input;
    private javax.swing.JButton editar_ruta_button;
    private javax.swing.JButton eliminar_ruta_button;
    private javax.swing.JTextField id_ruta_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limpiar_filtros_button;
    private javax.swing.JButton listar_rutas_button;
    private javax.swing.JTextField origen_ruta_input;
    private javax.swing.JTable tabla_rutas;
    // End of variables declaration//GEN-END:variables
}
