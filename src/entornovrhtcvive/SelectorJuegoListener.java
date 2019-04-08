/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import javax.swing.JFrame;
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_JUEGO_MINUTOS;
import static entornovrhtcvive.EntornoVRHTCVive.JUEGOS;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class SelectorJuegoListener {

    public static final int[][] GAMEPORTRAIT1 = {{88, 253}, {202, 440}}; //x0,y0 - x1,y1
    public static final int[][] GAMEPORTRAIT2 = {{212, 253}, {328, 440}};
    public static final int[][] GAMEPORTRAIT3 = {{337, 253}, {452, 440}};
    public static final int[][] GAMEPORTRAIT4 = {{461, 253}, {577, 440}};
    public static final int[][] GAMEPORTRAIT5 = {{586, 253}, {702, 440}};
    public static final int[][] GAMEPORTRAIT6 = {{711, 253}, {826, 440}};
    public static final int[][] GAMEPORTRAIT7 = {{835, 253}, {951, 440}};
    public static CoverStartStop coverStartStop;
    private static int[] coordenadasUltimoJuegoSeleccionado = {0, 0};
    private static int ultimoJuegoSeleccionado = 0;

    public SelectorJuegoListener(CoverStartStop coverStarStop) {
        //Toolkit.getDefaultToolkit().addAWTEventListener(new SelectorJuegoListener.mouseClickListener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
        coverStartStop = coverStarStop;
    }

    public static class mouseClickListener implements AWTEventListener {

        @Override
        public void eventDispatched(AWTEvent event) {
            int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
            int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
            int juegoSeleccionado = getClickCoordinates(x, y);

            if (PanelPrincipal.btnJugar.isEnabled()) {
                if (juegoSeleccionado == 0) {
                } else {
                    switch (juegoSeleccionado) {
                        case 1:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, cambiado tiempo de juego a: " + TIEMPO_DE_JUEGO_MINUTOS + "min.");
                            TIEMPO_DE_JUEGO_MINUTOS = 12;
                            break;
                        case 2:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, Coordenadas: " + x + "x" + y);
                            TIEMPO_DE_JUEGO_MINUTOS = 10;
                            break;
                        case 3:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " Seleccionado, Coordenadas: " + x + "x" + y);
                            entornovrhtcvive.EntornoVRHTCVive.configurarTiemposDeJuego();
                            break;
                        case 4:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, Coordenadas: " + x + "x" + y);
                            entornovrhtcvive.EntornoVRHTCVive.configurarTiemposDeJuego();
                            break;
                        case 5:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, Coordenadas: " + x + "x" + y);
                            entornovrhtcvive.EntornoVRHTCVive.configurarTiemposDeJuego();
                            break;
                        case 6:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, Coordenadas: " + x + "x" + y);
                            entornovrhtcvive.EntornoVRHTCVive.configurarTiemposDeJuego();
                            break;
                        case 7:
                            System.out.println("CONFIG: " + JUEGOS[juegoSeleccionado] + " seleccionado, Coordenadas: " + x + "x" + y);
                            TIEMPO_DE_JUEGO_MINUTOS = 10;
                            break;
                        default:
                            System.out.println("ERROR: Valor de selector de juego invalido.");
                            break;
                    }
                    coverStartStop.showGametime(juegoSeleccionado);
                    coordenadasUltimoJuegoSeleccionado[0] = x;
                    coordenadasUltimoJuegoSeleccionado[1] = y;
                    ultimoJuegoSeleccionado = getClickCoordinates(coordenadasUltimoJuegoSeleccionado[0], coordenadasUltimoJuegoSeleccionado[1]);
                }
            } else {
                if (juegoSeleccionado != ultimoJuegoSeleccionado) {
                    try {
                        ClickBot.click(coordenadasUltimoJuegoSeleccionado);
                        coverStartStop.showGametime(ultimoJuegoSeleccionado);
                        ClickBot.syncMainThread();
                        System.out.println("WARNING: Se ha intentado realizar cambios durante un tiempo de preparacion.");
                    } catch (AWTException ex) {
                        Logger.getLogger(SelectorJuegoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        public static int getClickCoordinates(int x, int y) {
            if (x > GAMEPORTRAIT1[0][0] && x < GAMEPORTRAIT1[1][0] && y > GAMEPORTRAIT1[0][1] && y < GAMEPORTRAIT1[1][1]) {
                return 1;
            } else if (x > GAMEPORTRAIT2[0][0] && x < GAMEPORTRAIT2[1][0] && y > GAMEPORTRAIT2[0][1] && y < GAMEPORTRAIT2[1][1]) {
                return 2;
            } else if (x > GAMEPORTRAIT3[0][0] && x < GAMEPORTRAIT3[1][0] && y > GAMEPORTRAIT3[0][1] && y < GAMEPORTRAIT3[1][1]) {
                return 3;
            } else if (x > GAMEPORTRAIT4[0][0] && x < GAMEPORTRAIT4[1][0] && y > GAMEPORTRAIT4[0][1] && y < GAMEPORTRAIT4[1][1]) {
                return 4;
            } else if (x > GAMEPORTRAIT5[0][0] && x < GAMEPORTRAIT5[1][0] && y > GAMEPORTRAIT5[0][1] && y < GAMEPORTRAIT5[1][1]) {
                return 5;
            } else if (x > GAMEPORTRAIT6[0][0] && x < GAMEPORTRAIT6[1][0] && y > GAMEPORTRAIT6[0][1] && y < GAMEPORTRAIT6[1][1]) {
                return 6;
            } else if (x > GAMEPORTRAIT7[0][0] && x < GAMEPORTRAIT7[1][0] && y > GAMEPORTRAIT7[0][1] && y < GAMEPORTRAIT7[1][1]) {
                return 7;
            } else {
                return 0;
            }
        }
    }
}
