/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

/**
 * utiliza un archivo externo para persistir la cantidad de partidas lanzadas
 * entre reinicios del sistema y estrictamente solo durante una jornada laboral,
 * caso contrario de lo ultimo, reinicia el conteo total de dichas partidas
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
        String linea = "";
        try {
            FileReader lector = new FileReader("juegosLanzados.txt");
            BufferedReader contenido = new BufferedReader(lector);
            while ((linea = contenido.readLine()) != null) {
                String[] cadena = linea.split(" ");
                Date fechaGuardada = new SimpleDateFormat("yyyy-MM-dd").parse(cadena[1]);
                if (hoy.equals(fechaGuardada)) {
                    return cadena[0];
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer");
        }
        return "0";
    }

}
