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
 * Bot que realiza clicks en la interfaz ocultada, automatizandola. Toda accion
 * toma tiempo, por lo que el hilo primario usar esperas para sincronizarse a
 * dichas demoras.
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
    public static final int TIEMPO_ENTRE_CLICKS = 30;

    /**
     * Ejecuta un click completo en las coordenadas recibidas. La accion toma
     * tiempo y es lanzada en hilos paralelos al que lo llama.
     *
     * @param button
     * @throws AWTException
     */
    public static void click(int[] button) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(button[0], button[1]);

        final ScheduledThreadPoolExecutor clickPressExecutor = new ScheduledThreadPoolExecutor(1);
        clickPressExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                final ScheduledThreadPoolExecutor clickReleaseExecutor = new ScheduledThreadPoolExecutor(1);
                clickReleaseExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                }, 10, TimeUnit.MILLISECONDS);
                clickReleaseExecutor.shutdownNow();
                clickReleaseExecutor.purge();
            }

        }, 10, TimeUnit.MILLISECONDS);
        clickPressExecutor.shutdownNow();
        clickPressExecutor.purge();

        try {
            Thread.sleep(TIEMPO_ENTRE_CLICKS);
        } catch (InterruptedException ex) {
            System.out.println("EXCEPCION: " + ex);
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
                System.out.println("ERROR: numero de jugador invalido.");
                break;
        }
    }

    public static void clickStart() throws AWTException {
        click(START);
    }

    public static void clickStop() throws AWTException {
        click(STOP);
    }

    /**
     * Las acciones del ClickBot toman tiempo y se ejecutan de forma paralela al
     * hilo primario por lo que dicho hilo debe esperar a que las acciones del
     * bot se completen
     */
    public static void syncMainThread() {
        try {
            Thread.sleep(TIEMPO_ENTRE_CLICKS + 100);
        } catch (InterruptedException ex) {
            System.out.println("EXCEPCION: " + ex);
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
