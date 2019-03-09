/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Frame;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author ernes
 */
public class pnlCoin extends javax.swing.JFrame {

    public static int creditosDisponibles = 0;
    private final Date horaApagado;
    private final long minutosDeJuegoMilisec = 600000; //10 Minutos
    private int cantidadVecesPuslsadoBotonApagado = 0;

    public pnlCoin() {
        initComponents();
        horaApagado = getFechaHoraApagado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblValorJuego = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblPaseTarjeta = new javax.swing.JLabel();
        btnApagarVR = new javax.swing.JButton();
        btnServicio = new javax.swing.JButton();
        txtFieldPasswordServicio = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        lblValorJuego.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblValorJuego.setText("1 CREDITO = 1 JUEGO");
        lblValorJuego.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblValorJuegoPropertyChange(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitulo.setText("SISTEMA DE REALIDAD VIRTUAL DE CRUCIJUEGOS");

        lblPaseTarjeta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");

        btnApagarVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnApagarVR.setText("APAGAR VR");
        btnApagarVR.setMaximumSize(new java.awt.Dimension(150, 31));
        btnApagarVR.setMinimumSize(new java.awt.Dimension(150, 31));
        btnApagarVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarVRActionPerformed(evt);
            }
        });

        btnServicio.setText("Modo de Servicio");
        btnServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFieldPasswordServicio)
                    .addComponent(btnApagarVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaseTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo)
                    .addComponent(lblValorJuego))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPaseTarjeta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFieldPasswordServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnApagarVR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 339, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnApagarVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarVRActionPerformed
        try {
            if (new Date().after(horaApagado)) {
                Runtime.getRuntime().exec("cmd.exe /K shutdown /f /s /t 00");
            } else if (cantidadVecesPuslsadoBotonApagado > 100) {
                JOptionPane.showMessageDialog(this, "SACA LA MANO DE AHI CARAJO!.", "ISABEL!", JOptionPane.ERROR_MESSAGE);
            } else {
                cantidadVecesPuslsadoBotonApagado++;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "OCURRIO UN ERROR AL INTENTAR APAGAR EL EQUIPO:\n" + ex + "\nPOR FAVOR DESCONECTE LA TERMINAL.", "ERROR EN APAGADO AUTOMATICO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApagarVRActionPerformed

    private void btnServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicioActionPerformed
        if (new String(txtFieldPasswordServicio.getPassword()).equals("luismi")) {
            creditosDisponibles++;
        }
    }//GEN-LAST:event_btnServicioActionPerformed

    private void lblValorJuegoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblValorJuegoPropertyChange
        System.out.println(this.lblValorJuego.getText());
//        this.jugar();
    }//GEN-LAST:event_lblValorJuegoPropertyChange

    private Date getFechaHoraApagado() {
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.set(Calendar.HOUR_OF_DAY, 22);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 0);

        return fechaActual.getTime();
    }

    private void jugar() {
        //FIXME: bandera para que no pueda pasar la tarjeta multiples veces y romper
        if (creditosDisponibles <= 0) {
            creditosDisponibles = 0;
            lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");
            JOptionPane.showMessageDialog(this, "POR FAVOR PASE LA TARJETA PARA JUGAR", "NO HAY CREDITOS", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Runtime.getRuntime().exec("cmd.exe /K \"C:\\activarPlacaDeRed.bat\""); //Activa la placa de red
                this.setState(Frame.ICONIFIED);
                java.util.Timer timer = new java.util.Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            Runtime.getRuntime().exec("cmd.exe /K shutdown /f /r /t 00"); // Programa un reinicio
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                }, minutosDeJuegoMilisec);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagarVR;
    private javax.swing.JButton btnServicio;
    public static javax.swing.JLabel lblPaseTarjeta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValorJuego;
    private javax.swing.JPasswordField txtFieldPasswordServicio;
    // End of variables declaration//GEN-END:variables
}
