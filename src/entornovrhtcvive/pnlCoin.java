/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import static entornovrhtcvive.EntornoVRHTCVive.NUMERO_JUGADORES;
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_JUEGO_MINUTOS;
import static entornovrhtcvive.EntornoVRHTCVive.TIEMPO_DE_PREPARACION_SEGUNDOS;
import java.awt.AWTException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static final long TIEMPO_DE_JUEGO_SEGUNDOS = TIEMPO_DE_JUEGO_MINUTOS * 60;
    public static final int TIEMPO_DE_DISTANCIAMIENTO_MILISEG = 650;
    private int proximoJugador;
    private int juegosLanzados = 0;
    private int juegosLanzadosTotal = 0;

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

        cmbJugadoresHabilitados.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    boolean jugadoresJugando = false;
                    for (Cover cover : covers) {
                        if (cover.isRunning()) {
                            jugadoresJugando = true;
                        }
                    }
                    if (!jugadoresJugando) {
                        int nroJugadoresHabilitados = Integer.valueOf((String) cmbJugadoresHabilitados.getSelectedItem());
                        NUMERO_JUGADORES = nroJugadoresHabilitados;
                        covers.stream().forEach((cover) -> {
                            if (cover.getPlayer() > nroJugadoresHabilitados) {
                                cover.setVisible(false);
                            } else {
                                cover.setVisible(true);
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "No pueden haber personas jugando.");
                        cmbJugadoresHabilitados.setSelectedIndex(NUMERO_JUGADORES - 1);
                    }
                }
            }
        });

    }

    void inicializarCovers(int jugadores) {
        for (int i = 0; i < jugadores; i++) {
            Cover cover = new Cover(i + 1);
            cover.ShowPnlBlqPlayer();
            try {
                covers.add(cover);
            } catch (Exception ex) {
                System.out.println("Excepcion:" + ex);
            }
        }
    }

    private void jugar() throws ParseException {

        if (CREDITOS_DISPONIBLES <= 0) {
            CREDITOS_DISPONIBLES = 0;
            lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");
            JOptionPane.showMessageDialog(this, "POR FAVOR PASE LA TARJETA PARA JUGAR", "NO HAY CREDITOS", JOptionPane.ERROR_MESSAGE);
        } else if (NUMERO_JUGADORES == juegosLanzados) {
            JOptionPane.showMessageDialog(null, "Ya estan todos los puestos ocupados, espere a que se desocupen para lanzar nuevas partidas.");
        } else if (CREDITOS_DISPONIBLES != 0) {
            juegosLanzados = 0;
            for (Cover cover : covers) {
                if (!cover.isRunning()) {
                    juegosLanzados++;
                    cover.setRunning(true);//extraida de setReady porque altera los schedulers
                    getJugadasHoy();

                    CREDITOS_DISPONIBLES--;
                    final int jugador = cover.getPlayer();
                    proximoJugador = getProximoJugador(jugador);

                    pnlCoin.lblValorJuego.setText("CREDITOS = " + CREDITOS_DISPONIBLES);
                    pnlCoin.lblCantJugadasTotal.setText("JUGADAS DE HOY: " + juegosLanzadosTotal);
                    coverStarStop.jLabel4.setText("Seleccione un Juego, Pase la tarjeta tantas veces como personas desean jugar y toque \"Jugar\". Proximo Jugador: " + proximoJugador);
                    System.out.println("Status: El Jugador " + jugador + " se esta preparando.");

                    cover.mostrarTiempoPreparacion();

                    final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                    executor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                cover.HidePnlBlqPlayer();
                                cover.setReady();
                                iniciarJuego();
                                cover.unTickReady();
                                cover.ShowPnlBlqPlayer();

                                System.out.println("Status: En " + TIEMPO_DE_JUEGO_MINUTOS + " minutos " + jugador + " corta jugada.");

                                final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                                executor.schedule(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            cover.HidePnlBlqPlayer();
                                            cover.setEnded();
                                            finalizarJuego();
                                            cover.unTickReady();
                                            cover.ShowPnlBlqPlayer();

                                        } catch (InterruptedException | AWTException ex) {
                                            Logger.getLogger(pnlCoin.class
                                                    .getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }, TIEMPO_DE_JUEGO_SEGUNDOS, TimeUnit.SECONDS);

                            } catch (InterruptedException | AWTException ex) {
                                Logger.getLogger(pnlCoin.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }, TIEMPO_DE_PREPARACION_SEGUNDOS, TimeUnit.SECONDS);
                }
                try {
                    Thread.sleep(TIEMPO_DE_DISTANCIAMIENTO_MILISEG);

                } catch (InterruptedException ex) {
                    Logger.getLogger(pnlCoin.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                if (CREDITOS_DISPONIBLES == 0 || NUMERO_JUGADORES == juegosLanzados) {
                    break;
                }
            }
        }
    }

    private void iniciarJuego() {
        coverStarStop.Hide();
        try {
            ClickBot.clickStart();

        } catch (AWTException ex) {
            Logger.getLogger(pnlCoin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ClickBot.syncMainThread();
        coverStarStop.Show();
    }

    private void finalizarJuego() {
        coverStarStop.Hide();
        try {
            ClickBot.clickStop();

        } catch (AWTException ex) {
            Logger.getLogger(pnlCoin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ClickBot.syncMainThread();
        coverStarStop.Show();
    }

    private int getProximoJugador(int ultimoJugador) {
        if (ultimoJugador != 4) {
            return ultimoJugador + 1;
        } else {
            return 1;
        }
    }

    private void getJugadasHoy() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date hoy = dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
            juegosLanzadosTotal = Integer.parseInt(Archivo.leer(hoy));
            juegosLanzadosTotal++;
            String jugadasString = Integer.toString(juegosLanzadosTotal);
            Archivo.escribir(jugadasString, hoy);
        } catch (NumberFormatException ep) {
            JOptionPane.showMessageDialog(null, "Inconsistencia en la base de datos");
            System.out.println("Inconsistencia en la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
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
        btnJugar = new javax.swing.JButton();
        lblCantJugadasTotal = new javax.swing.JLabel();
        lblJugadoresHabilitados = new javax.swing.JLabel();
        cmbJugadoresHabilitados = new javax.swing.JComboBox<>();
        chkVerInterfaz = new javax.swing.JCheckBox();

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

        btnJugar.setFont(new java.awt.Font("Lato Black", 1, 36)); // NOI18N
        btnJugar.setText("JUGAR");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        lblCantJugadasTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblJugadoresHabilitados.setFont(new java.awt.Font("Carlito", 1, 12)); // NOI18N
        lblJugadoresHabilitados.setText("Jugadores Habilitados");
        lblJugadoresHabilitados.setName("lblJugadoresHabilitados"); // NOI18N

        cmbJugadoresHabilitados.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        cmbJugadoresHabilitados.setMaximumRowCount(4);
        cmbJugadoresHabilitados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        cmbJugadoresHabilitados.setSelectedIndex(3);
        cmbJugadoresHabilitados.setSelectedItem(NUMERO_JUGADORES);

        chkVerInterfaz.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        chkVerInterfaz.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVerInterfazStateChanged(evt);
            }
        });
        chkVerInterfaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVerInterfazActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coinListener, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFieldPasswordServicio)
                            .addComponent(btnApagarVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblJugadoresHabilitados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbJugadoresHabilitados, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkVerInterfaz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                        .addComponent(lblPaseTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblValorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnJugar, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(lblCantJugadasTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPaseTarjeta))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnJugar)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorJuego)
                            .addComponent(lblCantJugadasTotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldPasswordServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(coinListener, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkVerInterfaz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApagarVR, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJugadoresHabilitados)
                            .addComponent(cmbJugadoresHabilitados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        try {
            jugar();
        } catch (ParseException ex) {
            Logger.getLogger(pnlCoin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnJugarActionPerformed

    private void chkVerInterfazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVerInterfazActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVerInterfazActionPerformed

    private void chkVerInterfazStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVerInterfazStateChanged
        if (pnlCoin.chkVerInterfaz.isSelected()) {
            covers.forEach((cover) -> {
                cover.setVisible(false);
            });
        } else {
            covers.forEach((cover) -> {
                cover.setVisible(true);
            });
        }    }//GEN-LAST:event_chkVerInterfazStateChanged

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
        int x0 = 0, x1 = 0, y0 = 0, y1 = 0;

        x0 = 0;
        y0 = 600;
        x1 = 1280;
        y1 = 133;
        System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
        //BOUNDS:0x600;1280x133

        frame.setBounds(x0, y0, x1, y1);
        frame.setSize(x1, y1);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagarVR;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnServicio;
    public static javax.swing.JCheckBox chkVerInterfaz;
    public static javax.swing.JComboBox<String> cmbJugadoresHabilitados;
    public static javax.swing.JCheckBox coinListener;
    private static javax.swing.JLabel lblCantJugadasTotal;
    private javax.swing.JLabel lblJugadoresHabilitados;
    public static javax.swing.JLabel lblPaseTarjeta;
    private javax.swing.JLabel lblTitulo;
    public static javax.swing.JLabel lblValorJuego;
    private javax.swing.JPasswordField txtFieldPasswordServicio;
    // End of variables declaration//GEN-END:variables

}
