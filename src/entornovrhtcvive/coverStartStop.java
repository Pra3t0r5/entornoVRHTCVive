/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

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

    private String proximoJugador;
   
    public coverStartStop(int screen) {
        initComponents();
        initialize(screen, this);
        this.lblTutoYSiguienteJugador.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\"");
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
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //estimated static placeholders
        int x0 = 0, x1 = screenSize.width, y0 = 700, y1 = screenSize.height;
     
        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(screenSize.width, 80); //FIXME: valores estimativos

        } else if (gd.length > 0) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(screenSize.width, 80);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTutoYSiguienteJugador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        jLabel1.setFont(new java.awt.Font("Lato Medium", 2, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA AUTOMATIZADO");

        lblTutoYSiguienteJugador.setFont(new java.awt.Font("Lato Medium", 2, 14)); // NOI18N
        lblTutoYSiguienteJugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTutoYSiguienteJugador.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTutoYSiguienteJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTutoYSiguienteJugador)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel lblTutoYSiguienteJugador;
    // End of variables declaration//GEN-END:variables
}
