/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class ClickBot {
    public static final int[] START = {446, 740};
    public static final int[] STOP = {590, 740};
    public static final int[] READYPLAYER1 = {1091, 255};
    public static final int[] READYPLAYER2 = {1091, 336};
    public static final int[] READYPLAYER3 = {1091, 411};
    public static final int[] READYPLAYER4 = {1091, 490};
    public static final int TIEMPO_ENTRE_CLICKS = 40;

    public static void click(int[] button) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(button[0], button[1]);

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                executor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                }, 10, TimeUnit.MILLISECONDS);
            }
        }, 10, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(TIEMPO_ENTRE_CLICKS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClickBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void clickReadyOf(int player) throws AWTException {
        switch (player) {
            case 1:
                click(READYPLAYER1);
                break;
            case 2:
                click(READYPLAYER2);
                break;
            case 3:
                click(READYPLAYER3);
                break;
            case 4:
                click(READYPLAYER4);
                break;
            default:
                System.out.println("Error, numero de jugador invalido.");
                break;
        }
    }

    public static void clickStart() throws AWTException {
        click(START);
    }

    public static void clickStop() throws AWTException {
        click(STOP);
    }
}
