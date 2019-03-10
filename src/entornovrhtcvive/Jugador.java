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
public class Jugador extends Thread {
    
    private final long TIEMPO_DE_JUEGO = 600000; //10 Minutos
    private int player;

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
    }
    
    public Jugador(int player){
        this.setPlayer(player);
        this.setCover(new PlayerCover(this.player));
        cover.ShowPnlBlqPlayers(this.player);
    }
    
    public void temporizarJuego() throws InterruptedException, AWTException{
        
        System.out.println("El jugador '"+player+"' inicia juego de: '"+TIEMPO_DE_JUEGO/60000+" minutos'");
        cover.HidePnlBlqPlayer(player);        
        ClickBot.unTickReadyOf(player);
        cover.ShowPnlBlqPlayers(player);
        
        Thread.sleep(TIEMPO_DE_JUEGO);
        
        cover.HidePnlBlqPlayer(player); 
        ClickBot.stopPlayOf(player);
        cover.ShowPnlBlqPlayers(player);
        System.out.println("El jugador '"+player+"' completo su sesion de juego");
    }
    
    
}
