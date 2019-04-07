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
public class ClickListener {

    public static final int[][] GAMEPORTRAIT1 = {{0, 0}, {100, 100}};

    public ClickListener() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new ClickListener.mouseClickListener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
    }

    public static class mouseClickListener implements AWTEventListener {

        @Override
        public void eventDispatched(AWTEvent event) {
            double x = MouseInfo.getPointerInfo().getLocation().getX();
            double y = MouseInfo.getPointerInfo().getLocation().getY();
            if (getClickCoordinates(x, y)) {
                System.out.println("TESTING: Zona del juego 1 clickeada, Coordenadas: " + x + "x" + y);
                try {
                    ClickBot.syncMainThread();
                    ClickBot.clickReadyOf(1);
                } catch (AWTException ex) {
                    Logger.getLogger(ClickListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public static boolean getClickCoordinates(double x, double y) {
            return x > GAMEPORTRAIT1[0][0] && x < GAMEPORTRAIT1[1][0] && y > GAMEPORTRAIT1[0][1] && y < GAMEPORTRAIT1[1][1];
        }
    }
}
