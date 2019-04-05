/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Fernando
 */
public class Logger {

    private PrintStream console;
    private PrintStream logFile;

    /**
     * Se encarga excluisvamente de registrar toda consola a un log externo, puede revertirse a consola con un comando.
     */
    public Logger() {

        logFile = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date hoy;
        try {
            hoy = dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
            String hoyString = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
            try {
                logFile = new PrintStream(new File("Log" + hoyString + ".txt"));
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            }
            //para cambiar a modo consola
            console = System.out;
            //setea salida en logFile
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modoLogFile() {
        System.setOut(logFile);
    }

    public void modoConsola() {
        System.setOut(console);
    }

}
