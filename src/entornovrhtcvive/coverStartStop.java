/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import static entornovrhtcvive.EntornoVRHTCVive.NUMERO_VERSION;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author fernando
 */
public class coverStartStop extends javax.swing.JFrame {

    public coverStartStop(int screen) {
        initComponents();
        initialize(screen, this);
        this.jLabel1.setText("SISTEMA AUTOMATIZADO v"+NUMERO_VERSION);
        this.jLabel4.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\".");
        this.Show();
    }

    public void Hide() {
        this.setVisible(false);
    }

    public void Show() {
        this.setVisible(true);
    }

    //Inicializa 1 panel de bloqueo de READY PLAYER para un hilo Jugador
    public void initialize(int screen, JFrame frame) {

        //estimated static placeholders
        int x0 = 0, x1 = 1280, y0 = 733, y1 = 800;

        frame.setBounds(x0, y0, x1, y1);
        frame.setSize(x1, 67); //FIXME: valores estimativos      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        jLabel1.setFont(new java.awt.Font("Lato Medium", 2, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA AUTOMATIZADO");

        jLabel4.setFont(new java.awt.Font("Lato Medium", 2, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
