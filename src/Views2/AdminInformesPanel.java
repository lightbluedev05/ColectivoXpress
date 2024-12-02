package Views2;

import Models.Admin;
import Models.Conductor;
import Models.Pasajero;
import Models.Ruta;
import Models.Viaje;
import Repository.BoletoRepository;
import Repository.ConductorRepository;
import Repository.ViajeRepository;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import kotlin.random.Random;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class AdminInformesPanel extends javax.swing.JPanel {
    
    public Admin admin;
    private Statement st;
    
    private List<List<Object>> conductores_mas_viajes;
    private List<List<Object>> pasajeros_mas_boletos;
    private List<List<Object>> rutas_mas_compradas;
    
    private int lista_elegida = 0;
    
    private InformesLista informeLista = new InformesLista();
    private InformesBarras informeBarras = new InformesBarras();
    
    
    public AdminInformesPanel(Admin admin, Statement st) {
        this.st = st;
        this.admin = admin;
        initComponents();
        correcciones_iniciales();
    }
    
    private void correcciones_iniciales(){
        
    }
    
    
    private void conductores(){
        if(conductores_mas_viajes == null || conductores_mas_viajes.isEmpty()){
            conductores_mas_viajes = new ArrayList<>();
            List<ViajeRepository.ViajeDTO> viajes = new ViajeRepository(st).listar_dto();
            
            if(viajes == null || viajes.isEmpty()){
                return;
            }
            
            HashMap<String, Integer> top = new HashMap<>();
            
            for(int i=0; i<viajes.size(); i++){
                String dni_conductor = viajes.get(i).dni_conductor;
                if(top.containsKey(dni_conductor)){
                    int actual = top.get(dni_conductor);
                    top.put(dni_conductor, actual+1);
                } else {
                    top.put(dni_conductor, 1);
                }
            }
            
            List<List<Object>> lista_aux = top.entrySet()
            .stream()
            .map(entry -> {
                List<Object> sublist = new ArrayList<>();
                sublist.add(entry.getKey());   // Agregar clave
                sublist.add(entry.getValue()); // Agregar valor
                return sublist;
            })
            .collect(Collectors.toList());

            List<List<Object>> conductores_mas_viajes_str = lista_aux.stream()
                    .sorted((sublist1, sublist2) -> Integer.compare((Integer) sublist2.get(1), (Integer) sublist1.get(1)))
                    .collect(Collectors.toList());
            
            
            for(List<Object> conductor_valor:conductores_mas_viajes_str){
                List<Object> lista = new ArrayList<>();
                lista.add(admin.buscar_conductor((String) conductor_valor.get(0), st));
                lista.add(conductor_valor.get(1));
                conductores_mas_viajes.add(lista);
            }
        }
        
    }
    
    private void pasajeros(){
        if(pasajeros_mas_boletos == null || pasajeros_mas_boletos.isEmpty()){
            pasajeros_mas_boletos = new ArrayList<>();
            List<BoletoRepository.BoletoDTO> boletos = new BoletoRepository(st).listar_dto();
            
            if(boletos == null || boletos.isEmpty()){
                return;
            }
            
            HashMap<String, Integer> top = new HashMap<>();
            
            for(int i=0; i<boletos.size(); i++){
                String dni_pasajero = boletos.get(i).dni_pasajero;
                if(top.containsKey(dni_pasajero)){
                    int actual = top.get(dni_pasajero);
                    top.put(dni_pasajero, actual+1);
                } else {
                    top.put(dni_pasajero, 1);
                }
            }
            
            List<List<Object>> lista_aux = top.entrySet()
            .stream()
            .map(entry -> {
                List<Object> sublist = new ArrayList<>();
                sublist.add(entry.getKey());   // Agregar clave
                sublist.add(entry.getValue()); // Agregar valor
                return sublist;
            })
            .collect(Collectors.toList());

            List<List<Object>> pasajeros_mas_boletos_str = lista_aux.stream()
                    .sorted((sublist1, sublist2) -> Integer.compare((Integer) sublist2.get(1), (Integer) sublist1.get(1)))
                    .collect(Collectors.toList());
            
            
            for(List<Object> pasajero_valor:pasajeros_mas_boletos_str){
                List<Object> lista = new ArrayList<>();
                lista.add(admin.buscar_pasajero((String) pasajero_valor.get(0), st));
                lista.add(pasajero_valor.get(1));
                pasajeros_mas_boletos.add(lista);
            }
        }
        
    }
    
    private void rutas(){
        if(rutas_mas_compradas == null || rutas_mas_compradas.isEmpty()){
            rutas_mas_compradas = new ArrayList<>();
            List<BoletoRepository.BoletoDTO> boletos = new BoletoRepository(st).listar_dto();
            
            if(boletos == null || boletos.isEmpty()){
                return;
            }
            
            HashMap<String, Integer> top = new HashMap<>();
            
            for(int i=0; i<boletos.size(); i++){
                Viaje viaje = admin.buscar_viaje(boletos.get(i).id_viaje, st);
                String ruta = viaje.get_ruta().get_id_ruta();
                if(top.containsKey(ruta)){
                    int actual = top.get(ruta);
                    top.put(ruta, actual+1);
                } else {
                    top.put(ruta, 1);
                }
            }
            
            List<List<Object>> lista_aux = top.entrySet()
            .stream()
            .map(entry -> {
                List<Object> sublist = new ArrayList<>();
                sublist.add(entry.getKey());   // Agregar clave
                sublist.add(entry.getValue()); // Agregar valor
                return sublist;
            })
            .collect(Collectors.toList());

            List<List<Object>> rutas_mas_compradas_str = lista_aux.stream()
                    .sorted((sublist1, sublist2) -> Integer.compare((Integer) sublist2.get(1), (Integer) sublist1.get(1)))
                    .collect(Collectors.toList());
            
            
            for(List<Object> ruta_valor:rutas_mas_compradas_str){
                List<Object> lista = new ArrayList<>();
                lista.add(admin.buscar_ruta((String) ruta_valor.get(0), st));
                lista.add(ruta_valor.get(1));
                rutas_mas_compradas.add(lista);
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        conductores_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        pasajeros_button = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rutas_button = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        mostrar_top_3_button = new javax.swing.JButton();
        mostrar_top_diez_button = new javax.swing.JButton();
        exportar_button = new javax.swing.JButton();
        panel_plantilla = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 246, 240));
        setMaximumSize(new java.awt.Dimension(1010, 580));
        setPreferredSize(new java.awt.Dimension(1010, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(23, 23, 23));
        jLabel2.setText("MÓDULO DE INFORMES");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(216, 22));
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(250, 246, 240));
        jPanel2.setLayout(new java.awt.GridLayout());

        jPanel3.setBackground(new java.awt.Color(250, 246, 240));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        conductores_button.setBackground(new java.awt.Color(41, 82, 85));
        conductores_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        conductores_button.setForeground(new java.awt.Color(240, 245, 247));
        conductores_button.setText("Conductores con mas Viajes");
        conductores_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        conductores_button.setMaximumSize(new java.awt.Dimension(161, 40));
        conductores_button.setMinimumSize(new java.awt.Dimension(161, 40));
        conductores_button.setPreferredSize(new java.awt.Dimension(220, 40));
        conductores_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conductores_buttonActionPerformed(evt);
            }
        });
        jPanel3.add(conductores_button, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(250, 246, 240));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        pasajeros_button.setBackground(new java.awt.Color(41, 82, 85));
        pasajeros_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pasajeros_button.setForeground(new java.awt.Color(240, 245, 247));
        pasajeros_button.setText("Pasajeros con mas Boletos");
        pasajeros_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pasajeros_button.setMaximumSize(new java.awt.Dimension(161, 40));
        pasajeros_button.setMinimumSize(new java.awt.Dimension(161, 40));
        pasajeros_button.setPreferredSize(new java.awt.Dimension(220, 40));
        pasajeros_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasajeros_buttonActionPerformed(evt);
            }
        });
        jPanel4.add(pasajeros_button, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(250, 246, 240));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        rutas_button.setBackground(new java.awt.Color(41, 82, 85));
        rutas_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rutas_button.setForeground(new java.awt.Color(240, 245, 247));
        rutas_button.setText("Rutas mas compradas");
        rutas_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rutas_button.setMaximumSize(new java.awt.Dimension(161, 40));
        rutas_button.setMinimumSize(new java.awt.Dimension(161, 40));
        rutas_button.setPreferredSize(new java.awt.Dimension(220, 40));
        rutas_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutas_buttonActionPerformed(evt);
            }
        });
        jPanel5.add(rutas_button, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(250, 246, 240));

        jPanel7.setBackground(new java.awt.Color(250, 246, 240));

        mostrar_top_3_button.setBackground(new java.awt.Color(41, 82, 85));
        mostrar_top_3_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mostrar_top_3_button.setForeground(new java.awt.Color(240, 245, 247));
        mostrar_top_3_button.setText("Mostrar TOP 3");
        mostrar_top_3_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mostrar_top_3_button.setMaximumSize(new java.awt.Dimension(161, 40));
        mostrar_top_3_button.setMinimumSize(new java.awt.Dimension(161, 40));
        mostrar_top_3_button.setPreferredSize(new java.awt.Dimension(161, 40));
        mostrar_top_3_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrar_top_3_buttonActionPerformed(evt);
            }
        });

        mostrar_top_diez_button.setBackground(new java.awt.Color(41, 82, 85));
        mostrar_top_diez_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mostrar_top_diez_button.setForeground(new java.awt.Color(240, 245, 247));
        mostrar_top_diez_button.setText("Mostrar TOP 10");
        mostrar_top_diez_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mostrar_top_diez_button.setMaximumSize(new java.awt.Dimension(161, 40));
        mostrar_top_diez_button.setMinimumSize(new java.awt.Dimension(161, 40));
        mostrar_top_diez_button.setPreferredSize(new java.awt.Dimension(161, 40));
        mostrar_top_diez_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrar_top_diez_buttonActionPerformed(evt);
            }
        });

        exportar_button.setBackground(new java.awt.Color(41, 82, 85));
        exportar_button.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        exportar_button.setForeground(new java.awt.Color(240, 245, 247));
        exportar_button.setText("Exportar");
        exportar_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportar_button.setMaximumSize(new java.awt.Dimension(161, 40));
        exportar_button.setMinimumSize(new java.awt.Dimension(161, 40));
        exportar_button.setPreferredSize(new java.awt.Dimension(161, 40));
        exportar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportar_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exportar_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mostrar_top_3_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mostrar_top_diez_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(mostrar_top_diez_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(mostrar_top_3_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(exportar_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );

        panel_plantilla.setBackground(new java.awt.Color(250, 246, 240));
        panel_plantilla.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(panel_plantilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_plantilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mostrar_top_diez_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrar_top_diez_buttonActionPerformed
        switch (lista_elegida) {
            case 0:
                {
                    String[] columnas = {"DNI", "Nombre", "Departamento", "Cantidad Viajes"};
                    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                    for(List<Object> conductor_valor:conductores_mas_viajes){
                        Conductor conductor = (Conductor) conductor_valor.get(0);
                        modelo.addRow(new Object[]{conductor.get_dni(), conductor.get_nombre(), conductor.get_departamento(), conductor_valor.get(1)});
                    }       informeLista.cambiar_modelo(modelo);
                    panel_plantilla.removeAll();                    
                    panel_plantilla.add(informeLista);
                    panel_plantilla.revalidate();
                    panel_plantilla.repaint();
                    break;
                }
            case 1:
                {
                    String[] columnas = {"DNI", "Nombre", "Departamento", "Cantidad Boletos"};
                    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                    for(List<Object> pasajero_valor:pasajeros_mas_boletos){
                        Pasajero pasajero = (Pasajero) pasajero_valor.get(0);
                        modelo.addRow(new Object[]{pasajero.get_dni(), pasajero.get_nombre(), pasajero.get_departamento(), pasajero_valor.get(1)});
                    }       informeLista.cambiar_modelo(modelo);
                    panel_plantilla.removeAll();
                    panel_plantilla.add(informeLista);
                    panel_plantilla.revalidate();
                    panel_plantilla.repaint();
                    break;
                }
            default:
                {
                    String[] columnas = {"ID Ruta", "Origen", "Destino", "Cantidad Boletos"};
                    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                    for(List<Object> ruta_valor:rutas_mas_compradas){
                        Ruta ruta = (Ruta) ruta_valor.get(0);
                        modelo.addRow(new Object[]{ruta.get_id_ruta(), ruta.get_origen(), ruta.get_destino(), ruta_valor.get(1)});
                    }       informeLista.cambiar_modelo(modelo);
                    panel_plantilla.removeAll();
                    panel_plantilla.add(informeLista);
                    panel_plantilla.revalidate();
                    panel_plantilla.repaint();
                    break;
                }
        }
    }//GEN-LAST:event_mostrar_top_diez_buttonActionPerformed

    private void exportar_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportar_buttonActionPerformed
        
    }//GEN-LAST:event_exportar_buttonActionPerformed

    private void mostrar_top_3_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrar_top_3_buttonActionPerformed
        switch (lista_elegida) {
            case 0:
                {   
                    String primer_label;
                    String segundo_label;
                    String tercer_label;

                    try {
                        primer_label = "<html><body style='text-align: center; padding-left: 0px;'>"
                                + ((Conductor) conductores_mas_viajes.get(0).get(0)).get_nombre() + "<br>"
                                + ((int) conductores_mas_viajes.get(0).get(1)) + "</body></html>";
                    } catch (Exception e) {
                        primer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                    }

                    try {
                        segundo_label = "<html><body style='text-align: center; padding-left: 0px;'>"
                                + ((Conductor) conductores_mas_viajes.get(1).get(0)).get_nombre() + "<br>"
                                + ((int) conductores_mas_viajes.get(1).get(1)) + "</body></html>";
                    } catch (Exception e) {
                        segundo_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                    }

                    try {
                        tercer_label = "<html><body style='text-align: center; padding-left: 0px;'>"
                                + ((Conductor) conductores_mas_viajes.get(2).get(0)).get_nombre() + "<br>"
                                + ((int) conductores_mas_viajes.get(2).get(1)) + "</body></html>";
                    } catch (Exception e) {
                        tercer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                    }

                    informeBarras.cambiar_modelo(primer_label, segundo_label, tercer_label);

                    panel_plantilla.removeAll();
                    panel_plantilla.add(informeBarras);
                    panel_plantilla.revalidate();
                    panel_plantilla.repaint();
                    break;
                }
            case 1: {
                String primer_label, segundo_label, tercer_label;

                try {
                    primer_label = "<html><body style='text-align: center;'>"
                            + ((Pasajero) pasajeros_mas_boletos.get(0).get(0)).get_nombre() + "<br>"
                            + ((int) pasajeros_mas_boletos.get(0).get(1)) + "</body></html>";
                } catch (Exception e) {
                    primer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                try {
                    segundo_label = "<html><body style='text-align: center;'>"
                            + ((Pasajero) pasajeros_mas_boletos.get(1).get(0)).get_nombre() + "<br>"
                            + ((int) pasajeros_mas_boletos.get(1).get(1)) + "</body></html>";
                } catch (Exception e) {
                    segundo_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                try {
                    tercer_label = "<html><body style='text-align: center;'>"
                            + ((Pasajero) pasajeros_mas_boletos.get(2).get(0)).get_nombre() + "<br>"
                            + ((int) pasajeros_mas_boletos.get(2).get(1)) + "</body></html>";
                } catch (Exception e) {
                    tercer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                informeBarras.cambiar_modelo(primer_label, segundo_label, tercer_label);
                panel_plantilla.removeAll();
                panel_plantilla.add(informeBarras);
                panel_plantilla.revalidate();
                panel_plantilla.repaint();
                break;
            }

            default: {
                String primer_label, segundo_label, tercer_label;

                try {
                    primer_label = "<html><body style='text-align: center;'>"
                            + ((Ruta) rutas_mas_compradas.get(0).get(0)).get_origen() + "->"
                            + ((Ruta) rutas_mas_compradas.get(0).get(0)).get_destino() + "<br>"
                            + ((int) rutas_mas_compradas.get(0).get(1)) + "</body></html>";
                } catch (Exception e) {
                    primer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                try {
                    segundo_label = "<html><body style='text-align: center;'>"
                            + ((Ruta) rutas_mas_compradas.get(1).get(0)).get_origen() + "->"
                            + ((Ruta) rutas_mas_compradas.get(1).get(0)).get_destino() + "<br>"
                            + ((int) rutas_mas_compradas.get(1).get(1)) + "</body></html>";
                } catch (Exception e) {
                    segundo_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                try {
                    tercer_label = "<html><body style='text-align: center;'>"
                            + ((Ruta) rutas_mas_compradas.get(2).get(0)).get_origen() + "->"
                            + ((Ruta) rutas_mas_compradas.get(2).get(0)).get_destino() + "<br>"
                            + ((int) rutas_mas_compradas.get(2).get(1)) + "</body></html>";
                } catch (Exception e) {
                    tercer_label = "<html><body style='text-align: center;'>Nadie</body></html>";
                }

                informeBarras.cambiar_modelo(primer_label, segundo_label, tercer_label);
                panel_plantilla.removeAll();
                panel_plantilla.add(informeBarras);
                panel_plantilla.revalidate();
                panel_plantilla.repaint();
                break;
            }
        }
    }//GEN-LAST:event_mostrar_top_3_buttonActionPerformed

    private void conductores_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conductores_buttonActionPerformed
        conductores();
        
        
        String[] columnas = {"DNI", "Nombre", "Departamento", "Cantidad Viajes"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        for(List<Object> conductor_valor:conductores_mas_viajes){
            Conductor conductor = (Conductor) conductor_valor.get(0);
            modelo.addRow(new Object[]{conductor.get_dni(), conductor.get_nombre(), conductor.get_departamento(), conductor_valor.get(1)});
        }
        
        informeLista.cambiar_modelo(modelo);
        panel_plantilla.removeAll();
        panel_plantilla.add(informeLista);
        panel_plantilla.revalidate();
        panel_plantilla.repaint();
        
        lista_elegida = 0;
    }//GEN-LAST:event_conductores_buttonActionPerformed

    private void pasajeros_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasajeros_buttonActionPerformed
        pasajeros();
        
        String[] columnas = {"DNI", "Nombre", "Departamento", "Cantidad Boletos"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        for(List<Object> pasajero_valor:pasajeros_mas_boletos){
            Pasajero pasajero = (Pasajero) pasajero_valor.get(0);
            modelo.addRow(new Object[]{pasajero.get_dni(), pasajero.get_nombre(), pasajero.get_departamento(), pasajero_valor.get(1)});
        }
        
        informeLista.cambiar_modelo(modelo);
        panel_plantilla.removeAll();
        panel_plantilla.add(informeLista);
        panel_plantilla.revalidate();
        panel_plantilla.repaint();
        
        lista_elegida = 1;
    }//GEN-LAST:event_pasajeros_buttonActionPerformed

    private void rutas_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutas_buttonActionPerformed
        rutas();
        
        String[] columnas = {"ID Ruta", "Origen", "Destino", "Cantidad Boletos"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        for(List<Object> ruta_valor:rutas_mas_compradas){
            Ruta ruta = (Ruta) ruta_valor.get(0);
            modelo.addRow(new Object[]{ruta.get_id_ruta(), ruta.get_origen(), ruta.get_destino(), ruta_valor.get(1)});
        }
        
        informeLista.cambiar_modelo(modelo);
        panel_plantilla.removeAll();
        panel_plantilla.add(informeLista);
        panel_plantilla.revalidate();
        panel_plantilla.repaint();
        
        lista_elegida = 2;
    }//GEN-LAST:event_rutas_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conductores_button;
    private javax.swing.JButton exportar_button;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton mostrar_top_3_button;
    private javax.swing.JButton mostrar_top_diez_button;
    private javax.swing.JPanel panel_plantilla;
    private javax.swing.JButton pasajeros_button;
    private javax.swing.JButton rutas_button;
    // End of variables declaration//GEN-END:variables
}
