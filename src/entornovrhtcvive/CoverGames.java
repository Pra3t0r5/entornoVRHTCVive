/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Restringe el uso de la interfaz ocultada y muestra feedback de tiempos de las
 * 3 fases de ejecucion de partidas (mas explicidatemnte los botones de Ready to
 * Play de los jugadores)
 *
 * @author fernando
 */
public class CoverGames extends javax.swing.JFrame {

    public static final int[][] GAMEPORTRAITSAREA = {{88, 253}, {951, 440}};

    public CoverGames() {
        initComponents();
        JFrame frame = new JFrame("Testing");
        frame.setUndecorated(true);
        //frame.setBounds(GAMEPORTRAITSAREA[0][0], GAMEPORTRAITSAREA[0][1], GAMEPORTRAITSAREA[1][0], GAMEPORTRAITSAREA[1][1]);
        //frame.setSize(GAMEPORTRAITSAREA[1][0] - GAMEPORTRAITSAREA[0][0], GAMEPORTRAITSAREA[1][1] - GAMEPORTRAITSAREA[0][1]);
        //frame.setBackground(new Color(0, 0, 0, 0));
        frame.setBackground(Color.yellow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
