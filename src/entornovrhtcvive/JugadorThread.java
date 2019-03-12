/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class JugadorThread extends Thread {

    private final long TIEMPO_DE_JUEGO = 5000;//600000; //10 Minutos
    private int player;
    private boolean jugando;
    private boolean jugar;
   
    public void jugarOtraVez(boolean jugar) {
        this.jugar = jugar;
        if(jugar){
            temporizar();
        }
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public PlayerCover getCover() {
        return cover;
    }

    public void setCover(PlayerCover cover) {
        this.cover = cover;
    }
    private PlayerCover cover;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
        System.out.println("Status: Thread Jugador " + player + " inicializada");
    }

    public JugadorThread(int player) {
        this.setPlayer(player);
        this.setCover(new PlayerCover(this.player));
        cover.ShowPnlBlqPlayers(this.player);
    }

    
    @Override
    public void run() {        
        temporizar();
        try {
            Thread.currentThread().wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(JugadorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void temporizar(){
        try {
            System.out.println("Status: Thread Jugador " + player + " ejecutandose");
            iniciarJuego();
            System.out.println("en 10 segundos " + player + " corta jugada");
            final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    try {                        
                        finalizarJuego();
                    } catch (InterruptedException | AWTException ex) {
                        Logger.getLogger(JugadorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 10, TimeUnit.SECONDS);
        } catch (InterruptedException | AWTException ex) {
            Logger.getLogger(JugadorThread.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    public void iniciarJuego() throws InterruptedException, AWTException {
        this.setJugando(true);
        System.out.println("El jugador '" + player + "' inicia juego de: '" + TIEMPO_DE_JUEGO / 60000 + " minutos'");
        cover.HidePnlBlqPlayer(player);
        ClickBot.clickReadyOf(player);
        ClickBot.clickStart();
        cover.ShowPnlBlqPlayers(player);
    }

    public void finalizarJuego() throws InterruptedException, AWTException {
        cover.HidePnlBlqPlayer(player);
        ClickBot.stopPlayOf(player);
        cover.ShowPnlBlqPlayers(player);
        System.out.println("El jugador '" + player + "' completo su sesion de juego");
        this.setJugando(false);
    }
}
