/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import entornovrhtcvive.VisualStyler.*;
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

	public static Calendar calendario = Calendar.getInstance();
	public static final String NUMERO_VERSION = "20190308";
	public static final int PANTALLA_SELECCIONADA = 0; //{0,1,2...}
	public static int NUMERO_JUGADORES = 4;
	public static int TIEMPO_DE_JUEGO_MINUTOS = 8;
	public static int TIEMPO_DE_PREPARACION_SEGUNDOS = 20;
	public static String[] JUEGOS = {
		"Ninguno Seleccionado",
		"Battlefield Legends",
		"War Zombies",
		"Battle Alien 2",
		"Battle Alien",
		"A-10",
		"BellyBots",
		"Guns"};
	public static VisualStyler estilizadorInterfaz = null;

	public static void main(String[] args) {

		Logger logger = new Logger();
		logger.modoLogFile();

		System.out.println("//////////////////Iniciado Servidor VR v" + NUMERO_VERSION + "//////////////////");
		System.out.println("/----------------Interfaz seteada a pantalla n°" + PANTALLA_SELECCIONADA + "------------------");
		System.out.println("/---------------Programa seteado para " + NUMERO_JUGADORES + " Jugadores-----------------");
		System.out.println("CONFIG: " + NUMERO_JUGADORES + " Jugadores");

		//Inicio el entorno grafico
		configurarTiemposDeJuego();
		estilizadorInterfaz = new VisualStyler();
		PanelPrincipal pnlPpal = new PanelPrincipal();
		pnlPpal.setLocationRelativeTo(null);
		PanelPrincipal.showOnScreen(PANTALLA_SELECCIONADA, pnlPpal);
                /*BackgroundImageJFrame bg = new BackgroundImageJFrame();
                //bg.add(pnlPpal);
                
                bg.setVisible(true);*/
		pnlPpal.setVisible(true);

		//Instancio el Manager del Concentrador de la Placa de Tarjetas
		ConcentradorManager concentradorManager = new ConcentradorManager();
		Thread threadConcentrador = new Thread(concentradorManager);
		threadConcentrador.start();

		//Instancio Covers para controlar partidas y sus tiempos de juego por separado
		pnlPpal.inicializarCovers(NUMERO_JUGADORES);

		System.out.println("CONFIG: Tiempo de Juego = " + TIEMPO_DE_JUEGO_MINUTOS + "min");
		System.out.println("CONFIG: Tiempo de Preparacion = " + TIEMPO_DE_PREPARACION_SEGUNDOS + "seg");
	}

	/**
	 * Configura el sistema instanciado para trabajar con tiempos de juego
	 * dependientes de la demanda
	 */
	public static void configurarTiemposDeJuego() {
		if ((calendario.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) || (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			System.out.println("CONFIG: Seteados tiempos estandar de fin de semana");
			TIEMPO_DE_JUEGO_MINUTOS = 6;
			TIEMPO_DE_PREPARACION_SEGUNDOS = 20;
		} else {
			System.out.println("CONFIG: Seteados tiempos estandar de dia de semana");
			TIEMPO_DE_JUEGO_MINUTOS = 8;
			TIEMPO_DE_PREPARACION_SEGUNDOS = 10;
		}
	}

}
