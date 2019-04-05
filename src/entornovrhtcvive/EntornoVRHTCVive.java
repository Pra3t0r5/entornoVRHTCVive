/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.util.Calendar;

/**
 * Programa de automatizacion de lanzamiento de partidas a base de coins
 * detectados. Utiliza principios de simulacion de interaccion humana sobre una
 * interfaz ocultada. Esta clase almacena las variables que configuran el
 * comportamiento del resto del sistema
 *
 * @author fernando
 */
public class EntornoVRHTCVive {

    public static final String NUMERO_VERSION = "20190305";
    public static final int PANTALLA_SELECCIONADA = 0; //{0,1,2...}
    public static int NUMERO_JUGADORES = 4;
    public static int TIEMPO_DE_JUEGO_MINUTOS = 8;
    public static int TIEMPO_DE_PREPARACION_SEGUNDOS = 20;
    public static Calendar calendario = Calendar.getInstance();

    public static void main(String[] args) {

        System.out.println("//////////////////Iniciado Servidor VR v" + NUMERO_VERSION + "//////////////////");
        System.out.println("/-----------Interfaz seteada a pantalla n°" + PANTALLA_SELECCIONADA + "-------------");
        System.out.println("/----------Programa seteado para " + NUMERO_JUGADORES + " Jugadores-------------");

        //Inicio el entorno grafico
        preconfigurarSistema();
        pnlCoin panelCoin = new pnlCoin();
        panelCoin.setLocationRelativeTo(null);
        pnlCoin.showOnScreen(PANTALLA_SELECCIONADA, panelCoin);
        panelCoin.setVisible(true);

        //Instancio el Manager del Concentrador de la Placa de Tarjetas
        ConcentradorManager concentradorManager = new ConcentradorManager();
        Thread threadConcentrador = new Thread(concentradorManager);
        threadConcentrador.start();

        //Instancio Covers para controlar partidas y sus tiempos de juego por separado
        panelCoin.inicializarCovers(NUMERO_JUGADORES);
    }

    private static void preconfigurarSistema() {
        if ((calendario.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) || (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            System.out.println("Status: Preconfigurando para fin de semana");
            TIEMPO_DE_JUEGO_MINUTOS = 6;
            TIEMPO_DE_PREPARACION_SEGUNDOS = 20;

        } else {
            System.out.println("Status: Preconfigurando para dia de semana");
            TIEMPO_DE_JUEGO_MINUTOS = 8;
            TIEMPO_DE_PREPARACION_SEGUNDOS = 10;

        }
    }

}
