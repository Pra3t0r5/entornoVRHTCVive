/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author fernando
 */
public class EntornoVRHTCVive {

    public static final int PANTALLA_SELECCIONADA = 0; //{0,1,2...}
    public static int NUMERO_JUGADORES = 4;
    public static final int TIEMPO_DE_JUEGO_MINUTOS = 10;
    public static final int TIEMPO_DE_PREPARACION_SEGUNDOS = 20;

    public static void main(String[] args) {
        

        System.out.println("//////////////////Iniciado Servidor VR//////////////////");
        System.out.println("/-----------Interfaz seteada a pantalla n°" + PANTALLA_SELECCIONADA + "-------------");
        System.out.println("/----------Programa seteado para " + NUMERO_JUGADORES + " Jugadores-------------");

        //Inicio el entorno grafico
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

   

}
