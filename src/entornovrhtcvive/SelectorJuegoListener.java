/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Fernando
 */
public class SelectorJuegoListener {

    public static final int[][] GAMEPORTRAIT1 = {{0, 0}, {99, 100}}; //x0,y0 - x1,y1
    public static final int[][] GAMEPORTRAIT2 = {{100, 0}, {199, 100}};
    public static final int[][] GAMEPORTRAIT3 = {{200, 0}, {299, 100}};
    public static final int[][] GAMEPORTRAIT4 = {{300, 0}, {399, 100}};
    public static final int[][] GAMEPORTRAIT5 = {{400, 0}, {499, 100}};
    public static final int[][] GAMEPORTRAIT6 = {{500, 0}, {599, 100}};
    public static final int[][] GAMEPORTRAIT7 = {{600, 0}, {799, 100}};
    public static JFrame pnlPpal;

    public SelectorJuegoListener(JFrame frame) {
        Toolkit.getDefaultToolkit().addAWTEventListener(new SelectorJuegoListener.mouseClickListener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
        pnlPpal = frame;
    }

    public static class mouseClickListener implements AWTEventListener {

        @Override
        public void eventDispatched(AWTEvent event) {
            int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
            int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
            switch (getClickCoordinates(x, y)) {
                case 1:
                    System.out.println("TESTING: Zona del juego 1 clickeada, Coordenadas: " + x + "x" + y);
                    //change time to 1 launch
                    break;
                case 2:
                    System.out.println("TESTING: Zona del juego 2 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                case 3:
                    System.out.println("TESTING: Zona del juego 3 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                case 4:
                    System.out.println("TESTING: Zona del juego 4 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                case 5:
                    System.out.println("TESTING: Zona del juego 5 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                case 6:
                    System.out.println("TESTING: Zona del juego 6 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                case 7:
                    System.out.println("TESTING: Zona del juego 7 clickeada, Coordenadas: " + x + "x" + y);
                    break;
                default:
                    break;
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
