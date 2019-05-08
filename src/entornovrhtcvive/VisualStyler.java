/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entornovrhtcvive;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
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
		RobotoBig = robotoFont.deriveFont(textSizeBig);
		RobotoMedium = robotoFont.deriveFont(textSizeMedium);
		RobotoSmall = robotoFont.deriveFont(textSizeSmall);
		colorBackground = "#eeeeee";
		transparent = "#00000000";
		colorNav = "#002171";
		colorPrimary = "#0d47a1";
		colorSecondary = "#5472d3";
		colorText = "#ffffff";
		colorAccentPrimary = "#5cce28";
		colorAccentSecondary = "#169c00";
		colorAccentTernary = "#93ff5e";
		colorTextInverted = "#261903";
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
					this.setBackground(Color.decode(colorNav));
					this.setForeground(Color.decode(colorText));
					break;
				case "pri":
					this.setFont(RobotoBig);
					this.setBackground(Color.decode(colorPrimary));
					this.setForeground(Color.decode(colorText));
					break;
				case "sec":
					this.setFont(RobotoMedium);
					this.setBackground(Color.decode(colorSecondary));
					this.setForeground(Color.decode(colorText));
					break;
				case "ter":
					this.setFont(RobotoSmall);
					this.setBackground(Color.decode(colorAccentTernary));
					this.setForeground(Color.decode(colorTextInverted));
					break;
				case "con":
					this.setFont(RobotoSmall);
					this.setBackground(Color.decode(colorBackground));
					this.setForeground(Color.decode(colorSecondary));
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
}
