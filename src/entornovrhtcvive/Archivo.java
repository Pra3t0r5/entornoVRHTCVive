/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

/**
 *
 * @author julieta
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Archivo {

    public static void escribir(String jugadas, Date hoy) {
        try {
            File archivo = new File("juegosLanzados.txt");
            FileWriter escribir = new FileWriter(archivo, false);
            String hoyString = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
            escribir.write(jugadas + " " + hoyString);
            escribir.close();
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }

    public static String leer(Date hoy) {
        String texto = "";
        String texto2 = "0";
        try {
            FileReader lector = new FileReader("juegosLanzados.txt");
            BufferedReader contenido = new BufferedReader(lector);
            while ((texto = contenido.readLine()) != null) {
                texto2 = texto;
                String line = texto;
                String[] cadena = line.split(" ");
                Date fechaGuardada = new SimpleDateFormat("yyyy-MM-dd").parse(cadena[1]);

                if (hoy.equals(fechaGuardada)) {
                    texto2 = cadena[0];
                } else {
                    texto2 = "0";
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer");
        }
        return texto2;
    }

}
