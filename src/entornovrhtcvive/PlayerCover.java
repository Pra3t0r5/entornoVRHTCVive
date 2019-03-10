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
import static entornovrhtcvive.EntornoVRHTCVive.PANTALLAS_CONECTADAS;

/**
 *
 * @author ernes
 */
public class PlayerCover extends javax.swing.JFrame {

    private int player;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public PlayerCover(int player) {
        initComponents();
        this.setPlayer(player);
        blqReadyOfPlayer();
    }

    public void HidePnlBlqPlayer(int player) {
        if (this.getPlayer() == player) {
            this.setVisible(false);
        }
    }

    public void ShowPnlBlqPlayers(int player) {
        if (this.getPlayer() == player) {
            this.setVisible(true);
        }
    }

    //TODO: revisar formas dinamicas
    public void blqReadyOfPlayer() {
        switch (player) {
            case 0: //cover for all players (initial)
                this.showOnScreenPnlBlq(PANTALLAS_CONECTADAS, this);
                break;
            case 1: //cover for player 1
                this.showOnScreenPnlBlq(PANTALLAS_CONECTADAS, this);
                break;
            case 2: //cover for player 2
                this.showOnScreenPnlBlq(PANTALLAS_CONECTADAS, this);
                break;
            case 3: //cover for player 3
                this.showOnScreenPnlBlq(PANTALLAS_CONECTADAS, this);
                break;
            case 4: //cover for player 4
                this.showOnScreenPnlBlq(PANTALLAS_CONECTADAS, this);
                break;
        }
    }

    //Inicializa 1 panel de bloqueo de READY PLAYER para un hilo Jugador
    public void showOnScreenPnlBlq(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

        switch (player) {
            case 0: // Dimensions&Bounds of big pannel
                x0 = screenSize.width;
                y0 = 0;
                x1 = screenSize.width / 4;
                y1 = screenSize.height;
                break;
            case 1: //Dimensions&Bounds of READYPLAYER1 blqPanel
                jLabel1.setText("Jugador 1");
                break;
            case 2: //Dimensions&Bounds of READYPLAYER2 blqPanel
                jLabel1.setText("Jugador 2");
                break;
            case 3: //Dimensions&Bounds of READYPLAYER3 blqPanel
                jLabel1.setText("Jugador 3");
                break;
            case 4: //Dimensions&Bounds of READYPLAYER4 blqPanel
                jLabel1.setText("Jugador 4");
                break;
            default:
                System.out.println("Error: Numero de jugadores incorrecto. Numero ingresado: " + player);
                break;
        }

        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(200, 100); //FIXME: valores estimativos

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
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
