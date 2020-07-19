import java.awt.Color;
import java.awt.Robot;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		GUI app = new GUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		app.setLocationRelativeTo(null);
		app.setSize(520, 600);
		app.setResizable(false);
		app.setTitle("GENERATOR ZESTAWÓW LICZBOWYCH BY JAROSLAW BABECKI");
		app.getContentPane().setBackground(Color.darkGray);
	}
}
