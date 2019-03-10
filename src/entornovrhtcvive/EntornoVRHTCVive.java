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

    public static final int pantallaInterfaz = 0; //{0,1,2...}

    public static void main(String[] args) {
        System.out.println("//////////////////Iniciado Servidor VR//////////////////");
        System.out.println("/-----------Interfaz seteada a 2da pantalla-------------");

        //Inicio el entorno grafico
        pnlCoin panelCoin = new pnlCoin();
        panelCoin.setLocationRelativeTo(null);
        panelCoin.showOnScreen(pantallaInterfaz, panelCoin);
        panelCoin.setVisible(true);

        //Instancio el Manager del Concentrador de la Placa de Tarjetas
        ConcentradorManager concentradorManager = new ConcentradorManager();
        Thread threadConcentrador = new Thread(concentradorManager);
        threadConcentrador.start();

        //Instancio Jugadores para controlar sus tiempos de juego por separado
    }

    

}
