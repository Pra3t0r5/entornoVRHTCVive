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
import static entornovrhtcvive.EntornoVRHTCVive.PANTALLA_SELECCIONADA;
import java.awt.AWTException;

/**
 *
 * @author ernes
 */
public class Cover extends javax.swing.JFrame {

    //Estimado basado en botones ready y dimensiones de pantalla original, formato: x0, y0, x1, y1
    public static final int[] READYPLAYER1_COVER = {1000, 205, 1200, 285};
    public static final int[] READYPLAYER2_COVER = {1000, 276, 1200, 356};
    public static final int[] READYPLAYER3_COVER = {1000, 355, 1200, 435};
    public static final int[] READYPLAYER4_COVER = {1000, 433, 1200, 513};
    
    private long TIEMPO_DE_JUEGO = 5000;//600000; //10 Minutos
    
    final private int player;

    public boolean running;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPlayer() {
        return player;
    }    

    public Cover(int player) {
        initComponents();
        this.player = player;
        blqReadyOfPlayer();
    }
    
    public void setReady() throws InterruptedException, AWTException {
        this.setRunning(true);
        System.out.println("El jugador '" + player + "' inicia juego de: '" + TIEMPO_DE_JUEGO / 60000 + " minutos'");
        this.HidePnlBlqPlayer();
        ClickBot.clickReadyOf(player);
        ClickBot.clickStart();
        this.ShowPnlBlqPlayer();
    }

    public void setEnded() throws InterruptedException, AWTException {
        this.HidePnlBlqPlayer();
        ClickBot.clickReadyOf(player);
        this.ShowPnlBlqPlayer();
        System.out.println("El jugador '" + player + "' completo su sesion de juego");
        this.setRunning(false);
    }

    public void HidePnlBlqPlayer() {
        if (this.getPlayer() == player) {
            this.setVisible(false);
        }
    }

    public void ShowPnlBlqPlayer() {
        if (this.getPlayer() == player) {
            this.setVisible(true);
        }
    }

    //TODO: revisar formas dinamicas
    public void blqReadyOfPlayer() {
        switch (player) {
            case 0: //cover for all players (initial)
                this.showOnScreenPnlBlq(PANTALLA_SELECCIONADA, this);
                break;
            case 1: //cover for player 1
                this.showOnScreenPnlBlq(PANTALLA_SELECCIONADA, this);
                break;
            case 2: //cover for player 2
                this.showOnScreenPnlBlq(PANTALLA_SELECCIONADA, this);
                break;
            case 3: //cover for player 3
                this.showOnScreenPnlBlq(PANTALLA_SELECCIONADA, this);
                break;
            case 4: //cover for player 4
                this.showOnScreenPnlBlq(PANTALLA_SELECCIONADA, this);
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
                break;
            case 1: //Dimensions&Bounds of READYPLAYER1 blqPanel
                jLabel1.setText("Jugador 1");
                x0 = READYPLAYER1_COVER[0];
                y0 = READYPLAYER1_COVER[1];
                x1 = READYPLAYER1_COVER[2];
                y1 = READYPLAYER1_COVER[3];
                break;
            case 2: //Dimensions&Bounds of READYPLAYER2 blqPanel
                jLabel1.setText("Jugador 2");
                x0 = READYPLAYER2_COVER[0];
                y0 = READYPLAYER2_COVER[1];
                x1 = READYPLAYER2_COVER[2];
                y1 = READYPLAYER2_COVER[3];
                break;
            case 3: //Dimensions&Bounds of READYPLAYER3 blqPanel
                jLabel1.setText("Jugador 3");
                x0 = READYPLAYER3_COVER[0];
                y0 = READYPLAYER3_COVER[1];
                x1 = READYPLAYER3_COVER[2];
                y1 = READYPLAYER3_COVER[3];
                break;
            case 4: //Dimensions&Bounds of READYPLAYER4 blqPanel
                jLabel1.setText("Jugador 4");
                x0 = READYPLAYER4_COVER[0];
                y0 = READYPLAYER4_COVER[1];
                x1 = READYPLAYER4_COVER[2];
                y1 = READYPLAYER4_COVER[3];
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

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel2.setText("INGRESE COINS PARA HABILITAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(512, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
