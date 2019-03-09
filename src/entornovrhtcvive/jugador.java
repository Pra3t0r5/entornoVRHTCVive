/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTException;

/**
 *
 * @author fernando
 */
public class jugador extends Thread {
    
    private final long TIEMPO_DE_JUEGO = 600000; //10 Minutos
    private int player;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
    
    public jugador(int player){
        this.setPlayer(player);
    };
    
    public void temporizarJuego() throws InterruptedException, AWTException{
        System.out.println("El jugador '"+player+"' inicia juego de: '"+TIEMPO_DE_JUEGO/60000+" minutos'");
        
        clickBot.unTickReadyOf(player);
        Thread.sleep(TIEMPO_DE_JUEGO);
        clickBot.stopPlayOf(player);
        
        System.out.println("El jugador '"+player+"' completo su sesion de juego");
    }
    
    
}
