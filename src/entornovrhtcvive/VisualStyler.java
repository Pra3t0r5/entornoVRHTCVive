/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

/**
 *
 * @author fernando
 */
public class VisualStyler {

    public static Float textSizeBig;
    public static Float textSizeMedium;
    public static Float textSizeSmall;
    public static Font robotoFont;
    public static Font RobotoBig;
    public static Font RobotoMedium;
    public static Font RobotoSmall;
    public static String colorBackground;
    public static String colorNav;
    public static String colorPrimary;
    public static String colorSecondary;
    public static String colorText;
    public static String colorAccentPrimary;
    public static String colorAccentSecondary;
    public static String colorAccentTernary;
    public static String colorTextInverted;
    public static String transparent;
    public static String colorText2;
    public static float textSizeTiny;
    public static Font RobotoTiny;

    public VisualStyler() {
        try {
            //create the font to use. Specify the size!
            robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/Roboto-Black.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(robotoFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("EX: cargando MaterialLookAndFeel = " + ex);
        }
        textSizeBig = 40f;
        textSizeMedium = 24f;
        textSizeSmall = 12f;
        textSizeTiny = 10f;
        RobotoBig = robotoFont.deriveFont(textSizeBig);
        RobotoMedium = robotoFont.deriveFont(textSizeMedium);
        RobotoSmall = robotoFont.deriveFont(textSizeSmall);
        RobotoTiny = robotoFont.deriveFont(textSizeTiny);
        colorBackground = "#003c8f";
        transparent = "#00000000";
        colorNav = "#002171";
        colorPrimary = "#0d47a1";
        colorSecondary = "#5472d3";
        colorText = "#ffffff";
        colorText2 = "#80d6ff";
        colorAccentPrimary = "#5cce28";
        colorAccentSecondary = "#169c00";
        colorAccentTernary = "#93ff5e";
        colorTextInverted = "#261903";
        /*colorBackground = "#eeeeee";
        transparent = "#00000000";
        colorNav = "#002171";
        colorPrimary = "#0d47a1";
        colorSecondary = "#5472d3";
        colorText = "#ffffff";
        colorAccentPrimary = "#5cce28";
        colorAccentSecondary = "#169c00";
        colorAccentTernary = "#93ff5e";
        colorTextInverted = "#261903";*/
    }

    public static class mdButton extends JButton {

        public mdButton(String type) {
            switch (type) {
                case "pri":
                    this.setFont(RobotoMedium);
                    this.setBackground(Color.decode(colorAccentPrimary));
                    this.setForeground(Color.decode(colorText));
                    break;
                case "sec":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(colorAccentSecondary));
                    this.setForeground(Color.decode(colorText));
                    break;
                case "ter":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(colorAccentTernary));
                    this.setForeground(Color.decode(colorTextInverted));
                    break;
                case "inv-pri":
                    this.setFont(RobotoMedium);
                    this.setBackground(Color.decode(colorAccentPrimary));
                    this.setBackground(Color.decode(transparent));
                    break;
                case "inv-sec":
                    this.setFont(RobotoSmall);
                    this.setForeground(Color.decode(colorAccentSecondary));
                    this.setBackground(Color.decode(transparent));
                    break;
                case "inv-ter":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(transparent));
                    this.setForeground(Color.decode(colorAccentTernary));
                    break;
            }
            MaterialUIMovement.add(this, MaterialColors.GREEN_A400);
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setForeground(Color fg) {
            super.setForeground(fg); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setFont(Font font) {
            super.setFont(font); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static class mdLabel extends JLabel {

        public mdLabel(String type) {
            switch (type) {
                case "nav":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(colorBackground));
                    this.setForeground(Color.decode(colorText2));
                    break;
                case "pri":
                    this.setFont(RobotoBig);
                    this.setBackground(Color.decode(colorBackground));
                    this.setForeground(Color.decode(colorText2));
                    break;
                case "sec":
                    this.setFont(RobotoMedium);
                    this.setBackground(Color.decode(colorBackground));
                    this.setForeground(Color.decode(colorText2));
                    break;
                case "ter":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(colorBackground));
                    this.setForeground(Color.decode(colorText2));
                    break;
                case "con":
                    this.setFont(RobotoSmall);
                    this.setBackground(Color.decode(colorBackground));
                    this.setForeground(Color.decode(colorText2));
                    break;
            }
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setForeground(Color fg) {
            super.setForeground(fg); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setFont(Font font) {
            super.setFont(font); //To change body of generated methods, choose Tools | Templates.
        }
    }
    /*
    public static class BackgroundImageJFrame extends JFrame {

        JButton b1;
        JLabel l1;

        public BackgroundImageJFrame() {

            setUndecorated(true);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setLocationRelativeTo(null);
            setSize(1280, 133);
            setBounds(0, 600, 1280, 133);
            setVisible(true);

            setLayout(new BorderLayout());

            Icon icon = new ImageIcon(getClass().getResource("mainbg.jpg"));
            JLabel background = new JLabel(icon);

            add(background);

            background.setLayout(new FlowLayout());

        }        
    }*/
}
