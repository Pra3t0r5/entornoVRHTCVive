/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import static entornovrhtcvive.EntornoVRHTCVive.pantallaInterfaz;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author ernes
 */
public class pnlBlqPlayers extends javax.swing.JFrame {

    public pnlBlqPlayers() {
        initComponents();

    }
    
    //TODO: revisar formas dinamicas
    public void mode(int mode) {
        switch (mode) {
            case 0:
                this.showOnScreenPnlBlq(pantallaInterfaz, this);
                break;
            case 1: //resize to cover 3 bottom players;
                break;
            case 2: //resize to cover 2 bottom players;
                break;
            case 3: //resize to cover 1 bottom player;
                break;
            case 4: //resize to nothing;
                break;
        }
    }

    //WAS on entorno
    public static void showOnScreenPnlBlq(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x0 = screenSize.width;
        int y0 = 0;
        int x1 = screenSize.width / 4;
        int y1 = screenSize.height;

        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize((screenSize.width / 4) / gd.length, screenSize.height);

        } else if (gd.length > 0) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(screenSize.width / 4, screenSize.height);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        jLabel1.setText("JUGADORES BLOQUEADOS");

        jLabel2.setText("INGRESE COINS PARA HABILITAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 188, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(467, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
