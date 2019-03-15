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
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_JUEGO_MINUTOS;
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_PREPARACION_SEGUNDOS;
import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author fernando
 */
public class Cover extends javax.swing.JFrame {

    //Estimado basado en botones ready y dimensiones de pantalla original, formato: x0, y0, x1, y1
    public static final int[] READYPLAYER1_COVER = {1015, 213, 1174, 288};
    public static final int[] READYPLAYER2_COVER = {1015, 293, 1174, 369};
    public static final int[] READYPLAYER3_COVER = {1015, 375, 1174, 451};
    public static final int[] READYPLAYER4_COVER = {1015, 456, 1174, 533};

    final private int player;

    public boolean running;

    Timer contador;
    Timer contadorPreparacion;

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
        contadorPreparacion.stop();
        System.out.println("El jugador '" + player + "' inicia juego de: '" + TIEMPO_DE_JUEGO_MINUTOS + " minutos'");
        this.HidePnlBlqPlayer();
        this.actualizarEstadoTexto();//COUNTDOWN
        pausaPreventiva();
        ClickBot.clickReadyOf(player);
        pausaPreventiva();
        this.ShowPnlBlqPlayer();

        ActionListener listener = new ActionListener() {
            java.util.Timer my = new java.util.Timer();

            LocalTime time = LocalTime.of(0, TIEMPO_DE_JUEGO_MINUTOS, 0);// 

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(time);
                time = time.minusSeconds(1);
                int minutos = time.getMinute();
                int segundos = time.getSecond();
                lblTemporizador.setText(minutos + ":" + segundos);
            }
        };
        contador = new Timer(1000, listener);
        contador.start();
    }

    public void pausaPreventiva() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEnded() throws InterruptedException, AWTException {
        contador.stop();
        this.HidePnlBlqPlayer();
        ClickBot.clickReadyOf(player);
        this.setRunning(false);
        this.actualizarEstadoTexto();
        this.ShowPnlBlqPlayer();
        System.out.println("El jugador '" + player + "' completo su sesion de juego");
    }

    public void actualizarEstadoTexto() {
        if (this.isRunning()) {
            this.lblEstadoJugador.setText("JUGADOR " + this.getPlayer() + ": Jugando");

        } else {
            this.lblEstadoJugador.setText("JUGADOR " + this.getPlayer() + ": Listo");
            this.lblTemporizador.setText("Pase tarjeta para jugar.");

        }
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

    public void mostrarTiempoPreparacion() {

        this.lblEstadoJugador.setText("Lanzamiento en:");
        ActionListener listener = new ActionListener() {
            java.util.Timer my = new java.util.Timer();
            LocalTime time = LocalTime.of(0, 0, TIEMPO_DE_PREPARACION_SEGUNDOS);

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(time);
                time = time.minusSeconds(1);
                int minutos = time.getMinute();
                int segundos = time.getSecond();
                lblTemporizador.setText(minutos + ":" + segundos);
            }
        };
        contadorPreparacion = new Timer(1000, listener);
        contadorPreparacion.start();
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
        int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

        switch (player) {
            case 0: // Dimensions&Bounds of big pannel               
                break;
            case 1: //Dimensions&Bounds of READYPLAYER1 blqPanel
                lblEstadoJugador.setText("JUGADOR 1");
                x0 = READYPLAYER1_COVER[0];
                y0 = READYPLAYER1_COVER[1];
                x1 = READYPLAYER1_COVER[2];
                y1 = READYPLAYER1_COVER[3];
                break;
            case 2: //Dimensions&Bounds of READYPLAYER2 blqPanel
                lblEstadoJugador.setText("JUGADOR 2");
                x0 = READYPLAYER2_COVER[0];
                y0 = READYPLAYER2_COVER[1];
                x1 = READYPLAYER2_COVER[2];
                y1 = READYPLAYER2_COVER[3];
                break;
            case 3: //Dimensions&Bounds of READYPLAYER3 blqPanel
                lblEstadoJugador.setText("JUGADOR 3");
                x0 = READYPLAYER3_COVER[0];
                y0 = READYPLAYER3_COVER[1];
                x1 = READYPLAYER3_COVER[2];
                y1 = READYPLAYER3_COVER[3];
                break;
            case 4: //Dimensions&Bounds of READYPLAYER4 blqPanel
                lblEstadoJugador.setText("JUGADOR 4");
                x0 = READYPLAYER4_COVER[0];
                y0 = READYPLAYER4_COVER[1];
                x1 = READYPLAYER4_COVER[2];
                y1 = READYPLAYER4_COVER[3];
                break;
            default:
                System.out.println("Error: Numero de jugadores incorrecto. Numero ingresado: " + player);
                break;
        }

        frame.setBounds(x0, y0, x1, y1);
        frame.setSize(159, 75); //FIXME: valores estimativos

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEstadoJugador = new javax.swing.JLabel();
        lblTemporizador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        lblEstadoJugador.setFont(new java.awt.Font("Liberation Sans Narrow", 1, 14)); // NOI18N
        lblEstadoJugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstadoJugador.setText("JUGADORES BLOQUEADOS");

        lblTemporizador.setFont(new java.awt.Font("Liberation Sans", 1, 10)); // NOI18N
        lblTemporizador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTemporizador.setText("Pase tarjeta para jugar.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTemporizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstadoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstadoJugador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTemporizador)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEstadoJugador;
    private javax.swing.JLabel lblTemporizador;
    // End of variables declaration//GEN-END:variables
}
