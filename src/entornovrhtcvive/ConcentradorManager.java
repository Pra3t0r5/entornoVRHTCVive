/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author ernes
 */
public class ConcentradorManager implements Runnable {

    private Socket clientSocket;
    public boolean conectadoPlacaDeTarjetas = false;

    public ConcentradorManager() {

    }

    private void conectarSocketPlacaTarjetas() {
        try {
            clientSocket = new Socket("localhost", 2000);
            conectadoPlacaDeTarjetas = true;
            pnlCoin.lblPaseTarjeta.setText("SISTEMA ONLINE - POR FAVOR PASE LA TARJETA");
        } catch (IOException ex) {
            System.out.println("Ocurrio un error al conectarse al Socket de la placa de tarjetas: " + ex);
            System.out.println("Reintentando en 10 segundos...");
            conectadoPlacaDeTarjetas = false;
            pnlCoin.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            pnlCoin.lblPaseTarjeta.setForeground(Color.RED);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex1) {
                System.out.println("Ocurrio un error al generar la espera de 10 segundos: " + ex1);
            }
            conectarSocketPlacaTarjetas();
        }
    }

    private void paseTarjetaListener() {
        try {
            String modifiedSentence;
            conectarSocketPlacaTarjetas();
            while (conectadoPlacaDeTarjetas) {
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                modifiedSentence = inFromServer.readLine();
                if (modifiedSentence.equals("COIN")) {
                    pnlCoin.lblValorJuego.setText("CREDITOS: " + pnlCoin.CREDITOS_DISPONIBLES);
                    System.out.println("Status: Pase de tarjeta detectado");
                    if (pnlCoin.coinListener.isSelected()) {
                        pnlCoin.coinListener.setSelected(false);
                    } else {
                        pnlCoin.coinListener.setSelected(true);
                    }
                }
            }
        } catch (SocketException se) {
            System.out.println("La conexion fue reseteada! Se intentara una reconexion: " + se);
            conectadoPlacaDeTarjetas = false;
            pnlCoin.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            conectarSocketPlacaTarjetas();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error no contemplado durante el pase de tarjetas: " + ex);
            conectadoPlacaDeTarjetas = false;
            pnlCoin.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            conectarSocketPlacaTarjetas();
        }

    }

    @Override
    public void run() {
        paseTarjetaListener();
    }

}
