/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views2;

import Models.Admin;
import Models.Conductor;
import Models.Ruta;
import Models.Viaje;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import kotlin.random.Random;

/**
 *
 * @author Mihae
 */
public class AdminViajesPanel extends javax.swing.JPanel {

    /**
     * Creates new form ConductoresAdmin
     */
    Admin admin;
    public AdminViajesPanel(Admin admin) {
        this.admin = admin;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){
        id_buscar_input.setBackground(new Color(168, 168, 168));
        id_eliminar_input.setBackground(new Color(168, 168, 168));
        
        tabla_viajes.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_viajes.setBackground(new Color(230, 230, 230));
        tabla_datos_viaje.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla_datos_viaje.setBackground(new Color(230, 230, 230));
    }
    
    private void listar_viajes(){
        DefaultTableModel modelo = (DefaultTableModel)tabla_viajes.getModel();
        modelo.setRowCount(0);
        
        List<Viaje> viajes = admin.ver_viajes();
        for(Viaje viaje:viajes){
            modelo.addRow(new Object[]{viaje.get_id_viaje(), viaje.get_ruta().get_origen(), viaje.get_ruta().get_destino()});
        }
        
        tabla_viajes.setModel(modelo);
    }
    
    private void ver_datos_viaje(){
        DefaultTableModel modelo = (DefaultTableModel)tabla_datos_viaje.getModel();
        modelo.setRowCount(0);
        
        String id_viaje = id_buscar_input.getText();
        Viaje viaje = admin.buscar_viaje(id_viaje);
        
        if(viaje==null){
            modelo.addRow(new Object[]{"ERROR", "ID NO EXISTE"});
        } else {
            
            long horas = viaje.get_ruta().get_tiempo_aproximado().toHours();
            long minutos = viaje.get_ruta().get_tiempo_aproximado().toMinutesPart();
            
            modelo.addRow(new Object[]{"ID Viaje", viaje.get_id_viaje()});
            modelo.addRow(new Object[]{"Conductor", viaje.get_conductor().get_dni()});
            modelo.addRow(new Object[]{"Precio", viaje.get_ruta().get_precio()});
            modelo.addRow(new Object[]{"Origen", viaje.get_ruta().get_origen()});
            modelo.addRow(new Object[]{"Destino", viaje.get_ruta().get_destino()});
            modelo.addRow(new Object[]{"Tiempo Aproximado", String.format("%02d:%02d", horas, minutos)});
            modelo.addRow(new Object[]{"Fecha de Salida", viaje.get_fecha_salida()});
            modelo.addRow(new Object[]{"Hora de Salida", viaje.get_hora_salida()});
        }
        
        tabla_datos_viaje.setModel(modelo);
    }
    
    private void eliminar_viaje(){
        String id_viaje = id_eliminar_input.getText();
        boolean exito = admin.eliminar_viaje(id_viaje);
        if(!exito){
            System.out.println("se deberia borrar");
            resultado_eliminar.setText("No se pudo borrar");
            return;
        }
        System.out.println("paso todo");
        resultado_eliminar.setText("Viaje eliminado!!");
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
        buscar_viaje_button = new javax.swing.JButton();
        id_buscar_input = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        id_eliminar_input = new javax.swing.JTextField();
        eliminar_viaje_button = new javax.swing.JButton();
        resultado_eliminar = new javax.swing.JLabel();
        agregar_viaje_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_viajes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_datos_viaje = new javax.swing.JTable();
        editar_viaje_button = new javax.swing.JButton();

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
        jLabel2.setText("Buscar Viaje por ID");

        buscar_viaje_button.setBackground(new java.awt.Color(30, 30, 30));
        buscar_viaje_button.setForeground(new java.awt.Color(245, 245, 245));
        buscar_viaje_button.setText("Buscar Viaje");
        buscar_viaje_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        buscar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        buscar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        buscar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        buscar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_viaje_buttonActionPerformed(evt);
            }
        });

        id_buscar_input.setCaretColor(new java.awt.Color(168, 168, 168));

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(23, 23, 23));
        jLabel3.setText("Eliminar Viaje por ID");

        eliminar_viaje_button.setBackground(new java.awt.Color(30, 30, 30));
        eliminar_viaje_button.setForeground(new java.awt.Color(245, 245, 245));
        eliminar_viaje_button.setText("Eliminar Viaje");
        eliminar_viaje_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        eliminar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        eliminar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_viaje_buttonActionPerformed(evt);
            }
        });

        resultado_eliminar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        resultado_eliminar.setForeground(new java.awt.Color(44, 73, 168));

        agregar_viaje_button.setBackground(new java.awt.Color(30, 30, 30));
        agregar_viaje_button.setForeground(new java.awt.Color(245, 245, 245));
        agregar_viaje_button.setText("Agregar Viaje");
        agregar_viaje_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        agregar_viaje_button.setMaximumSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.setMinimumSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.setPreferredSize(new java.awt.Dimension(161, 40));
        agregar_viaje_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_viaje_buttonActionPerformed(evt);
            }
        });

        tabla_viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ruta", "Origen", "Destino"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_viajes);

        tabla_datos_viaje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dato", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_datos_viaje);

        editar_viaje_button.setBackground(new java.awt.Color(30, 30, 30));
        editar_viaje_button.setForeground(new java.awt.Color(255, 255, 255));
        editar_viaje_button.setText("Editar Viaje");
        editar_viaje_button.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(agregar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buscar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addComponent(editar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(id_buscar_input)
                                    .addComponent(jLabel3)
                                    .addComponent(id_eliminar_input)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(eliminar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(resultado_eliminar))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(listar_viajes_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(id_buscar_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_eliminar_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eliminar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultado_eliminar))
                        .addGap(46, 46, 46)
                        .addComponent(agregar_viaje_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(listar_viajes_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listar_viajes_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar_viajes_buttonActionPerformed
        listar_viajes();
    }//GEN-LAST:event_listar_viajes_buttonActionPerformed

    private void buscar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_viaje_buttonActionPerformed
       ver_datos_viaje();
    }//GEN-LAST:event_buscar_viaje_buttonActionPerformed

    private void eliminar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_viaje_buttonActionPerformed
        eliminar_viaje();
    }//GEN-LAST:event_eliminar_viaje_buttonActionPerformed

    private void agregar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_viaje_buttonActionPerformed
        AdminViajesCrear crear_viaje = new AdminViajesCrear(admin);
        crear_viaje.setVisible(true);
    }//GEN-LAST:event_agregar_viaje_buttonActionPerformed

    private void editar_viaje_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_viaje_buttonActionPerformed
        String id_ruta = id_buscar_input.getText();
        
        Ruta ruta = admin.buscar_ruta(id_ruta);
        if(ruta == null){
            DefaultTableModel modelo = (DefaultTableModel)tabla_datos_viaje.getModel();
            modelo.setRowCount(0);
            modelo.addRow(new Object[]{"ERROR", "ID NO EXISTE"});
            tabla_datos_viaje.setModel(modelo);
        }
        
        AdminRutasEditar editar_ruta = new AdminRutasEditar(admin, id_ruta);
        editar_ruta.setVisible(true);
    }//GEN-LAST:event_editar_viaje_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_viaje_button;
    private javax.swing.JButton buscar_viaje_button;
    private javax.swing.JButton editar_viaje_button;
    private javax.swing.JButton eliminar_viaje_button;
    private javax.swing.JTextField id_buscar_input;
    private javax.swing.JTextField id_eliminar_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton listar_viajes_button;
    private javax.swing.JLabel resultado_eliminar;
    private javax.swing.JTable tabla_datos_viaje;
    private javax.swing.JTable tabla_viajes;
    // End of variables declaration//GEN-END:variables
}
