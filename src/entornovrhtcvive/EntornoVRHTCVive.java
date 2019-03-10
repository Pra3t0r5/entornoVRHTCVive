/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

/**
 *
 * @author ernes
 */
public class EntornoVRHTCVive {

    public static final int PANTALLA_SELECCIONADA = 0; //{0,1,2...}
    public static final int NUMERO_JUGADORES = 4;

    public static void main(String[] args) {
        System.out.println("//////////////////Iniciado Servidor VR//////////////////");
        System.out.println("/-----------Interfaz seteada a pantalla n°"+PANTALLA_SELECCIONADA+"-------------");
        System.out.println("/----------Programa seteado para "+NUMERO_JUGADORES+" Jugadores-------------");

        //Inicio el entorno grafico
        pnlCoin panelCoin = new pnlCoin();
        panelCoin.setLocationRelativeTo(null);
        pnlCoin.showOnScreen(PANTALLA_SELECCIONADA, panelCoin);
        panelCoin.setVisible(true);

        //Instancio el Manager del Concentrador de la Placa de Tarjetas
        ConcentradorManager concentradorManager = new ConcentradorManager();
        Thread threadConcentrador = new Thread(concentradorManager);
        threadConcentrador.start();

        //Instancio Jugadores para controlar sus tiempos de juego por separado
        panelCoin.inicializarJugadores(NUMERO_JUGADORES);

    }

}
