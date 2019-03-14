/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_JUEGO_MINUTOS;
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_PREPARACION_SEGUNDOS;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fernando
 */
public class pnlCoin extends javax.swing.JFrame {

    public static int CREDITOS_DISPONIBLES = 0;
    private final Date HORA_APAGADO;
    private int CANT_VECES_PULSADO_APAGAR = 0;
    private static final long TIEMPO_DE_JUEGO_MILISEGUNDOS = 600000; //10 Minutos    
    public static final long TIEMPO_DE_JUEGO_SEGUNDOS = TIEMPO_DE_JUEGO_MINUTOS * 60;
    private int proximoJugador;

    private final ArrayList<Cover> covers;
    private final coverStartStop coverStarStop;

    public int getCREDITOS_DISPONIBLES() {
        return CREDITOS_DISPONIBLES;
    }

    public void addCREDITOS_DISPONIBLES() {
        CREDITOS_DISPONIBLES = CREDITOS_DISPONIBLES + 1;
    }

    public pnlCoin() {
        initComponents();
        HORA_APAGADO = getFechaHoraApagado();
        covers = new ArrayList<Cover>();
        coverStarStop = new coverStartStop(EntornoVRHTCVive.PANTALLA_SELECCIONADA);
    }

    void inicializarCovers(int jugadores) {
        for (int i = 0; i < jugadores; i++) {
            Cover cover = new Cover(i + 1);
            cover.ShowPnlBlqPlayer();
            try {
                covers.add(cover);
            } catch (Exception ex) {
                System.out.println("ex:" + ex);
            }
        }
    }

    private void jugar() {
        //FIXME: bandera para que no pueda pasar la tarjeta multiples veces y romper
        if (CREDITOS_DISPONIBLES <= 0) {
            CREDITOS_DISPONIBLES = 0;
            lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");
            JOptionPane.showMessageDialog(this, "POR FAVOR PASE LA TARJETA PARA JUGAR", "NO HAY CREDITOS", JOptionPane.ERROR_MESSAGE);
        } else {
            if (CREDITOS_DISPONIBLES != 0) {
                int juegosLanzados = 0;
                for (Cover cover : covers) {
                    if (!cover.isRunning()) {
                        cover.setRunning(true);//lo saque de dentro de Set ready porque altera los schedulers
                        juegosLanzados++;
                        CREDITOS_DISPONIBLES--;
                        pnlCoin.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                        //I have no idea why this makes it work. But hey, it works!
                        final int jugador = cover.getPlayer();
                        proximoJugador = getProximoJugador(jugador);
                        System.out.println("Status: El Jugador " + jugador + " se esta preparando.");

                        coverStarStop.lblTutoYSiguienteJugador.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\". Proximo Jugador: " + proximoJugador);

                        cover.mostrarTiempoPreparacion();

                        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                        executor.schedule(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Status: Jugador " + jugador + " lanza partida");
                                    cover.setReady();
                                    //pausa preventiva para registrar clicks sin arrastrar
                                    pausaPreventiva();
                                    iniciarJuego();
                                    System.out.println("Status: En " + TIEMPO_DE_JUEGO_MINUTOS + " minutos " + jugador + " corta jugada.");

                                    final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                                    executor.schedule(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                cover.setEnded();
                                                pausaPreventiva();
                                                finalizarJuego();
                                            } catch (InterruptedException | AWTException ex) {
                                                Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }, TIEMPO_DE_JUEGO_SEGUNDOS, TimeUnit.SECONDS);
                                } catch (InterruptedException | AWTException ex) {
                                    Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }, TIEMPO_DE_PREPARACION_SEGUNDOS, TimeUnit.SECONDS);
                    }
                    if (CREDITOS_DISPONIBLES == 0) {
                        this.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                        break;
                    }
                    if (juegosLanzados == 4) {
                        this.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                        break;
                    }
                }
            }
        }
    }

    private void iniciarJuego() {
        coverStarStop.Hide();
        pausaPreventiva();
        try {
            ClickBot.clickStart();
        } catch (AWTException ex) {
            Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
        }
        pausaPreventiva();
        coverStarStop.Show();
    }

    private void finalizarJuego() {
        coverStarStop.Hide();
        try {
            ClickBot.clickStop();
        } catch (AWTException ex) {
            Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
        }
        coverStarStop.Show();
    }

    private int getProximoJugador(int ultimoJugador) {
        if (ultimoJugador != 4) {
            return ultimoJugador + 1;
        } else {
            return 1;
        }
    }

    public void pausaPreventiva() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblValorJuego = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblPaseTarjeta = new javax.swing.JLabel();
        btnApagarVR = new javax.swing.JButton();
        btnServicio = new javax.swing.JButton();
        txtFieldPasswordServicio = new javax.swing.JPasswordField();
        coinListener = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        lblValorJuego.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblValorJuego.setText("1 CREDITO = 1 JUEGO");
        lblValorJuego.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblValorJuegoPropertyChange(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitulo.setText("SISTEMA DE REALIDAD VIRTUAL DE CRUCIJUEGOS");

        lblPaseTarjeta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");

        btnApagarVR.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnApagarVR.setText("APAGAR VR");
        btnApagarVR.setMaximumSize(new java.awt.Dimension(150, 31));
        btnApagarVR.setMinimumSize(new java.awt.Dimension(150, 31));
        btnApagarVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarVRActionPerformed(evt);
            }
        });

        btnServicio.setText("Modo de Servicio");
        btnServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicioActionPerformed(evt);
            }
        });

        txtFieldPasswordServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFieldPasswordServicioKeyPressed(evt);
            }
        });

        coinListener.setBorder(null);
        coinListener.setEnabled(false);
        coinListener.setFocusable(false);
        coinListener.setMaximumSize(new java.awt.Dimension(0, 0));
        coinListener.setMinimumSize(new java.awt.Dimension(0, 0));
        coinListener.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                coinListenerStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Lato Black", 1, 36)); // NOI18N
        jButton1.setText("JUGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFieldPasswordServicio)
                            .addComponent(btnApagarVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(coinListener, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                            .addComponent(lblPaseTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblValorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(coinListener, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblPaseTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jButton1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValorJuego))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtFieldPasswordServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnApagarVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApagarVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarVRActionPerformed
        try {
            if (new Date().after(HORA_APAGADO)) {
                Runtime.getRuntime().exec("cmd.exe /K shutdown /f /s /t 00");
            } else if (CANT_VECES_PULSADO_APAGAR > 100) {
                JOptionPane.showMessageDialog(this, "SACA LA MANO DE AHI CARAJO!.", "ISABEL!", JOptionPane.ERROR_MESSAGE);
            } else {
                CANT_VECES_PULSADO_APAGAR++;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "OCURRIO UN ERROR AL INTENTAR APAGAR EL EQUIPO:\n" + ex + "\nPOR FAVOR DESCONECTE LA TERMINAL.", "ERROR EN APAGADO AUTOMATICO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApagarVRActionPerformed

    private void btnServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicioActionPerformed
        if (new String(txtFieldPasswordServicio.getPassword()).equals("luismi")) {
            System.out.println("Status: Credito de Servicio detectado.");
            this.addCREDITOS_DISPONIBLES();
            this.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
        }
    }//GEN-LAST:event_btnServicioActionPerformed

    private void lblValorJuegoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblValorJuegoPropertyChange
        System.out.println(this.lblValorJuego.getText());
    }//GEN-LAST:event_lblValorJuegoPropertyChange

    private void coinListenerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_coinListenerStateChanged
        System.out.println("Status: Credito recibido.");
        this.addCREDITOS_DISPONIBLES();
    }//GEN-LAST:event_coinListenerStateChanged

    private void txtFieldPasswordServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldPasswordServicioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_1) {
            addCREDITOS_DISPONIBLES();
        }
    }//GEN-LAST:event_txtFieldPasswordServicioKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jugar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private Date getFechaHoraApagado() {
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.set(Calendar.HOUR_OF_DAY, 22);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 0);

        return fechaActual.getTime();
    }

    /**
     * Permite elegir en que monitor mostrar la interfaz y ajusta
     * automaticamente las dimensiones en relacion a la cantidad de monitores
     *
     * @author fernando
     * @param screen
     * @param frame
     */
    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

        //TESTING: Muestra tamaños de monitor, usualmente sumatoria de todas las pantallas conectadas
        for (GraphicsDevice curGs : gd) {
            DisplayMode dm = curGs.getDisplayMode();
            System.out.println("Detected Borders: " + dm.getWidth() + " x " + dm.getHeight());
            System.out.println("Detected Dimensions: " + screenSize);
        }

        //Seleccion de dimensiones iniciales dependiente del monitor seleccionado
        switch (screen) {
            case 0:
                x0 = 0;
                y0 = screenSize.height - screenSize.height / 2;
                x1 = screenSize.width;
                y1 = 680;//y0 / 3;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            case 1:
                x0 = screenSize.width;
                y0 = screenSize.height - screenSize.height / 2;
                x1 = screenSize.width;
                y1 = 680;//y0 / 2;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            default:
                System.out.println("Warning: No hay una configuracion disponible para mas de dos pantallas!\nN° de pantallas seleccionadas: " + screen);
                break;
        }

        //Fijacion final de las dimensiones de acuerdo a la configuracion de pantallas presente
        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0 / gd.length, y0 + y0 / 2, x1, 600);
            frame.setSize(x1 / gd.length, y1);
        } else if (gd.length > 0) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0, y0, x1, y1);
            frame.setSize(screenSize.width - screenSize.width / 4, screenSize.height);
        } else {
            throw new RuntimeException("No se encontraron pantallas!");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagarVR;
    private javax.swing.JButton btnServicio;
    public static javax.swing.JCheckBox coinListener;
    private javax.swing.JButton jButton1;
    public static javax.swing.JLabel lblPaseTarjeta;
    private javax.swing.JLabel lblTitulo;
    public static javax.swing.JLabel lblValorJuego;
    private javax.swing.JPasswordField txtFieldPasswordServicio;
    // End of variables declaration//GEN-END:variables

    /*public void inicializarJugadores(int numeroDeJugadores) {
        int player = 0;
        while (true) {
            player++;
            JugadorThread jugador = new JugadorThread(player);
            jugadores.add(jugador);

            if (player == numeroDeJugadores) {
                break;
            }
        }
    }
     */
    /**
     * Instancia los threads y los añade a un listado para controlar su estado y
     * asignar automaticamente nuevas jugadas
     *
     * @author fernando
     */
    /*private void jugar() {
        //FIXME: bandera para que no pueda pasar la tarjeta multiples veces y romper
        if (CREDITOS_DISPONIBLES <= 0) {
            CREDITOS_DISPONIBLES = 0;
            lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");
            JOptionPane.showMessageDialog(this, "POR FAVOR PASE LA TARJETA PARA JUGAR", "NO HAY CREDITOS", JOptionPane.ERROR_MESSAGE);
        } else {
            while (CREDITOS_DISPONIBLES != 0) {

                boolean lanzado = false;
                explorarJugadoresListos(jugadores);
                
                if (!jugadoresListos.isEmpty()) {
                    for (JugadorThread jugador : jugadores) {
                        
                        //reviso si el jugador esta listo
                        if (jugadoresListos.contains(jugador)) {
                            CREDITOS_DISPONIBLES--;
                            this.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                            this.setVisible(false);
                            try {
                                //lanzo la partida y quito el jugador de la lista de listos
                                jugador.start();
                                jugadoresListos.remove(jugador);

                            } catch (IllegalThreadStateException e) {
                                //el thread ya fue usado asi que rescato los valores..
                                int nro = jugador.getPlayer();
                                JFrame cover = jugador.getCover();
                                //...e instancio una nueva thread con los valores que necesito para mantener siempre los 4 jugadores con sus respectivos paneles...
                                JugadorThread nuevoJugador = new JugadorThread(nro);
                                nuevoJugador.setCover((PlayerCover) cover);
                                //...y elimino duplicados
                                jugador.interrupt();
                                jugadoresListos.remove(jugador);                                
                                jugadoresListos.add(nuevoJugador);

                                System.out.println("Status: ThreadJugador " + nro + " Restarteado");
                                nuevoJugador.start();
                                JUGADORES_JUGANDO++;
                                jugadoresListos.remove(nuevoJugador);
                            }
                        }
                        this.setVisible(true);
                        lanzado = true;

                        if (CREDITOS_DISPONIBLES == 0) {
                            this.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                            break;
                        } 
                    }
                }
                if (!lanzado) {
                    System.out.println("Status: Todos los jugadores estan jugando, esperando 10 segundos antes de reintentar");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void explorarJugadoresListos(ArrayList<JugadorThread> jugadores) {
        for (JugadorThread jugador : jugadores) {
            if (!jugador.isAlive()) {
                jugadoresListos.add(jugador);                
            }
        }
    }*/
}
