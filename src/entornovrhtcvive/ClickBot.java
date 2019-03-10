/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class ClickBot {
    //Coordenadas hardcodeadas para pantalla original
    public static final int[] START = {446 , 711};
    public static final int[] STOP = {594 , 712};
    public static final int[] READYPLAYER1 = {1091 , 245};
    public static final int[] READYPLAYER2 = {1091 , 316};
    public static final int[] READYPLAYER3 = {1091 , 395};
    public static final int[] READYPLAYER4 = {1091 , 473};

    public static void click(int[] button) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(button[0], button[1]);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClickBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void stopPlayOf(int player) throws AWTException{
        switch (player) {
            case 1: click(READYPLAYER1);  
                break;
            case 2: click(READYPLAYER2);  
                break;
            case 3: click(READYPLAYER3);  
                break;
            case 4: click(READYPLAYER4);  
                break;
            default: System.out.println("Error, numero de jugador invalido."); 
                break;
        }
        click(STOP);
    }
    public static void unTickReadyOf(int player) throws AWTException{
        switch (player) {
            case 1: click(READYPLAYER1);  
                break;
            case 2: click(READYPLAYER2);  
                break;
            case 3: click(READYPLAYER3);  
                break;
            case 4: click(READYPLAYER4);  
                break;
            default: System.out.println("Error, numero de jugador invalido."); 
                break;
        }
    }
}
