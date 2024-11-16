
package Vista;

import Models.Pasajero;
import Views2.Destinos;
import Views2.IniciarSesionPrincipal;
import Views2.Inicio;
import Views2.Nosotros;
import Views2.PasajeroPerfil;
import Views2.PasajeroCompraViaje;
import Views2.PasajeroHistorial;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class DashboardPasajero extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private Pasajero pasajero;
    public DashboardPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;          
        initComponents();
        setDate();
        initContent();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void setDate(){
        LocalDate now= LocalDate.now();
        Locale spanishLocale = new Locale ( "es", "ES");
        fecha.setText(now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));
    }
    
    private void initContent(){
        ShowJPanel(new Nosotros());
    }
    public void ShowJPanel(JPanel in){
        in.setSize(1010, 580);
        in.setLocation(0, 0);
        content.removeAll();
        content.add(in, BorderLayout.CENTER);
        content.revalidate();
        content.repaint(); 
    }
    
    /*private void ShowJPanel(JPanel n){
        n.setSize(1010, 580);
        n.setLocation(0, 0);
        content.removeAll();
        content.add(n, BorderLayout.CENTER);
        content.revalidate();
        content.repaint(); 
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        jPanelConEncabezadoColectivo1 = new Vista.JPanelConEncabezadoColectivo();
        menu = new javax.swing.JPanel();
        buttonPerfil = new javax.swing.JButton();
        buttonComprar = new javax.swing.JButton();
        buttonHistorial = new javax.swing.JButton();
        buttonNosotros = new javax.swing.JButton();
        buttonInicio = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 620));

        jPanelConEncabezadoColectivo1.setPreferredSize(new java.awt.Dimension(1280, 90));

        javax.swing.GroupLayout jPanelConEncabezadoColectivo1Layout = new javax.swing.GroupLayout(jPanelConEncabezadoColectivo1);
        jPanelConEncabezadoColectivo1.setLayout(jPanelConEncabezadoColectivo1Layout);
        jPanelConEncabezadoColectivo1Layout.setHorizontalGroup(
            jPanelConEncabezadoColectivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelConEncabezadoColectivo1Layout.setVerticalGroup(
            jPanelConEncabezadoColectivo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        menu.setBackground(new java.awt.Color(0, 0, 204));

        buttonPerfil.setBackground(new java.awt.Color(0,0,0,0));
        buttonPerfil.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        buttonPerfil.setForeground(new java.awt.Color(255, 255, 255));
        buttonPerfil.setText("PERFIL");
        buttonPerfil.setBorder(null);
        buttonPerfil.setBorderPainted(false);
        buttonPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPerfilActionPerformed(evt);
            }
        });

        buttonComprar.setBackground(new java.awt.Color(0,0,0,0));
        buttonComprar.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        buttonComprar.setForeground(new java.awt.Color(255, 255, 255));
        buttonComprar.setText("COMPRAR");
        buttonComprar.setBorder(null);
        buttonComprar.setBorderPainted(false);
        buttonComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonComprarActionPerformed(evt);
            }
        });

        buttonHistorial.setBackground(new java.awt.Color(0,0,0,0));
        buttonHistorial.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        buttonHistorial.setForeground(new java.awt.Color(255, 255, 255));
        buttonHistorial.setText("HISTORIAL");
        buttonHistorial.setBorder(null);
        buttonHistorial.setBorderPainted(false);
        buttonHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistorialActionPerformed(evt);
            }
        });

        buttonNosotros.setBackground(new java.awt.Color(0,0,0,0));
        buttonNosotros.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        buttonNosotros.setForeground(new java.awt.Color(255, 255, 255));
        buttonNosotros.setText("NOSOTROS");
        buttonNosotros.setBorder(null);
        buttonNosotros.setBorderPainted(false);
        buttonNosotros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNosotros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNosotrosActionPerformed(evt);
            }
        });

        buttonInicio.setBackground(new java.awt.Color(0,0,0,0));
        buttonInicio.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        buttonInicio.setForeground(new java.awt.Color(255, 255, 255));
        buttonInicio.setText("INICIO");
        buttonInicio.setBorder(null);
        buttonInicio.setBorderPainted(false);
        buttonInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonNosotros, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(buttonPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonNosotros, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        header.setBackground(new java.awt.Color(0, 153, 255));

        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setText("Hoy es {dayname} {day} de {month} del {year}");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fecha)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(fecha)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1010, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConEncabezadoColectivo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(jPanelConEncabezadoColectivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInicioActionPerformed
        ShowJPanel(new Inicio());
    }//GEN-LAST:event_buttonInicioActionPerformed

    private void buttonComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonComprarActionPerformed
        ShowJPanel(new PasajeroCompraViaje(pasajero));
    }//GEN-LAST:event_buttonComprarActionPerformed

    private void buttonPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPerfilActionPerformed
    ShowJPanel(new PasajeroPerfil(pasajero));
    }//GEN-LAST:event_buttonPerfilActionPerformed

    private void buttonNosotrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNosotrosActionPerformed
        ShowJPanel(new Nosotros());
    }//GEN-LAST:event_buttonNosotrosActionPerformed

    private void buttonHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistorialActionPerformed
        ShowJPanel(new PasajeroHistorial(pasajero));
    }//GEN-LAST:event_buttonHistorialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DashboardPasajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton buttonComprar;
    private javax.swing.JButton buttonHistorial;
    private javax.swing.JButton buttonInicio;
    private javax.swing.JButton buttonNosotros;
    private javax.swing.JButton buttonPerfil;
    private javax.swing.JPanel content;
    private javax.swing.JLabel fecha;
    private javax.swing.JPanel header;
    private Vista.JPanelConEncabezadoColectivo jPanelConEncabezadoColectivo1;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
