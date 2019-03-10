/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ernes
 */
public class pnlCoin extends javax.swing.JFrame {

    public static int CREDITOS_DISPONIBLES = 0;
    private final Date HORA_APAGADO;
    private final long MIN_DE_JUEGO_MILISEC = 600000; //10 Minutos
    private int CANT_VECES_PULSADO_APAGAR = 0;
    
    public int getCREDITOS_DISPONIBLES() {
        return CREDITOS_DISPONIBLES;
    }

    public static void addCREDITOS_DISPONIBLES() {
        CREDITOS_DISPONIBLES = CREDITOS_DISPONIBLES + 1;        
    }

    public pnlCoin() {
        initComponents();
        HORA_APAGADO = getFechaHoraApagado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblValorJuego = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblPaseTarjeta = new javax.swing.JLabel();
        btnApagarVR = new javax.swing.JButton();
        btnServicio = new javax.swing.JButton();
        txtFieldPasswordServicio = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        lblValorJuego.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblValorJuego.setText("1 CREDITO = 1 JUEGO");
        lblValorJuego.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblValorJuegoPropertyChange(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitulo.setText("SISTEMA DE REALIDAD VIRTUAL DE CRUCIJUEGOS");

        lblPaseTarjeta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFieldPasswordServicio)
                    .addComponent(btnApagarVR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaseTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo)
                    .addComponent(lblValorJuego))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPaseTarjeta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFieldPasswordServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnApagarVR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 339, Short.MAX_VALUE))
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
            this.lblValorJuego.setText("CREDITOS DISPONIBLES = "+CREDITOS_DISPONIBLES);
            this.addCREDITOS_DISPONIBLES();            
            this.jugar();
        }
    }//GEN-LAST:event_btnServicioActionPerformed

    private void lblValorJuegoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblValorJuegoPropertyChange
        System.out.println(this.lblValorJuego.getText());
//        this.jugar();
    }//GEN-LAST:event_lblValorJuegoPropertyChange

    private Date getFechaHoraApagado() {
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.set(Calendar.HOUR_OF_DAY, 22);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 0);

        return fechaActual.getTime();
    }

    private void jugar() {
        //FIXME: bandera para que no pueda pasar la tarjeta multiples veces y romper
        if (CREDITOS_DISPONIBLES <= 0) {
            CREDITOS_DISPONIBLES = 0;
            lblPaseTarjeta.setText("POR FAVOR PASE LA TARJETA");
            JOptionPane.showMessageDialog(this, "POR FAVOR PASE LA TARJETA PARA JUGAR", "NO HAY CREDITOS", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Runtime.getRuntime().exec("cmd.exe /K \"C:\\activarPlacaDeRed.bat\""); //Activa la placa de red
                this.setState(Frame.ICONIFIED);
                java.util.Timer timer = new java.util.Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            Runtime.getRuntime().exec("cmd.exe /K shutdown /f /r /t 00"); // Programa un reinicio
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                }, MIN_DE_JUEGO_MILISEC);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    
    
    /**
     * Permite elegir en que monitor mostrar la interfaz y ajusta
     * automaticamente las dimensiones en relacion a la cantidad de monitores
     * TESTING: Esta hardcodeado a segundo monitor
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

        //TESTING: Obtiene tamaños de monitor
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
                x1 = screenSize.width - screenSize.width / 4;
                y1 = y0 / 2;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            case 1:
                x0 = screenSize.width;
                y0 = screenSize.height - screenSize.height / 2;
                x1 = screenSize.width - screenSize.width / 4;
                y1 = y0 / 2;
                System.out.println("Selected Bounds: " + x0 + "x" + y0 + ", " + x1 + "x" + y1);
                break;
            default:
                System.out.println("Warning: No hay una configuracion disponible para mas de dos pantallas!\nN° de pantallas seleccionadas: " + screen);
                break;
        }
        
        //Fijacion final de las dimensiones de acuerdo a la configuracion de pantallas presente
        if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());

            frame.setBounds(x0 / gd.length, y0 + y0 / 2, x1, y1);
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
    public static javax.swing.JLabel lblPaseTarjeta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValorJuego;
    private javax.swing.JPasswordField txtFieldPasswordServicio;
    // End of variables declaration//GEN-END:variables

   
}
