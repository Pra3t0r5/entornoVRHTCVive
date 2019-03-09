/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author ernes
 */
public class EntornoVRHTCVive {

    public static final int pantallaInterfaz = 0; //{0,1,2...}

    public static void main(String[] args) {
        System.out.println("//////////////////Iniciado Servidor VR//////////////////");
        System.out.println("/-----------Interfaz seteada a 2da pantalla-------------");

        //Inicio el entorno grafico
        pnlCoin panelCoin = new pnlCoin();
        panelCoin.setLocationRelativeTo(null);
        showOnScreen(pantallaInterfaz, panelCoin);
        panelCoin.setVisible(true);

        //Instancio el Manager del Concentrador de la Placa de Tarjetas
        ConcentradorManager concentradorManager = new ConcentradorManager();
        Thread threadConcentrador = new Thread(concentradorManager);
        threadConcentrador.start();

        //Instancio Jugadores para controlar sus tiempos de juego por separado
    }

    /**
     * Permite elegir en que monitor mostrar la interfaz y ajusta
     * automaticamente las dimensiones en relacion a la cantidad de monitores
     * TESTING: Esta hardcodeado a segundo monitor
     *
     * @author fernando
     * @param screen
     * @param frame
     */
    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

        //TESTING: Obtiene tamaños de monitor
        for (GraphicsDevice curGs : gd) {
            DisplayMode dm = curGs.getDisplayMode();
            System.out.println("Detected Borders: " + dm.getWidth() + " x " + dm.getHeight());
            System.out.println("Detected Dimensions: " + screenSize);
        }

        switch (screen) {
            case 0:
                x0 = 0;
                y0 = screenSize.height - screenSize.height / 2;
                x1 = screenSize.width - screenSize.width / 4;
                y1 = y0 / 2;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            case 1:
                x0 = screenSize.width;
                y0 = screenSize.height - screenSize.height / 2;
                x1 = screenSize.width - screenSize.width / 4;
                y1 = y0 / 2;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            default: System.out.println("Warning: No hay una configuracion disponible para mas de dos pantallas!\nN° de pantallas seleccionadas: "+screen);
                break;
        }

        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0 / gd.length, y0 + y0 / 2, x1, y1);
            frame.setSize(x1 / gd.length, y1);
            //frame.setSize((screenSize.width / 4) / gd.length, screenSize.height);
        } else if (gd.length > 0) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(screenSize.width - screenSize.width / 4, screenSize.height);
            //frame.setSize(screenSize.width / 4, screenSize.height);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

}
