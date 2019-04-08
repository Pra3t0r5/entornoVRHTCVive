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
 * genera creditos disponibles a partir de los coins traducidos por la plaqueta
 * de hernan que a su vez fueron detectados del lector de tarjetas
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
            PanelPrincipal.lblPaseTarjeta.setText("SISTEMA ONLINE - POR FAVOR PASE LA TARJETA");
        } catch (IOException ex) {
            System.out.println("EXCEPTION: Ocurrio un error al conectarse al Socket de la placa de tarjetas: " + ex);
            System.out.println("STATUS: Reintentando en 10 segundos...");
            conectadoPlacaDeTarjetas = false;
            PanelPrincipal.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            PanelPrincipal.lblPaseTarjeta.setForeground(Color.RED);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex1) {
                System.out.println("EXCEPCION CONTROLADA: Ocurrio un error al generar la espera de 10 segundos: " + ex1);
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
                    System.out.println("STATUS: Pase de tarjeta detectado.");

                    if (PanelPrincipal.coinListener.isSelected()) {
                        PanelPrincipal.coinListener.setSelected(false);
                    } else {
                        PanelPrincipal.coinListener.setSelected(true);
                    }

                }
            }
        } catch (SocketException se) {
            System.out.println("WARNING: La conexion fue reseteada! Se intentara una reconexion: " + se);
            conectadoPlacaDeTarjetas = false;
            PanelPrincipal.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            conectarSocketPlacaTarjetas();
        } catch (Exception ex) {
            System.out.println("WARNING: Ocurrio un error no contemplado durante el pase de tarjetas: " + ex);
            conectadoPlacaDeTarjetas = false;
            PanelPrincipal.lblPaseTarjeta.setText("SISTEMA FUERA DE SERVICIO - POR FAVOR ESPERE");
            conectarSocketPlacaTarjetas();
        }

    }

    @Override
    public void run() {
        paseTarjetaListener();
    }

}
