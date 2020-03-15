import java.awt.Robot;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

//		Generator generator = new Generator();
//			generator.generateSet();
//		
		GUI app = new GUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		app.setLocationRelativeTo(null);
		app.setSize(500, 500);
		app.setResizable(false);
		app.setTitle("GENEROWANIE ZESTAWÓW LICZBOWYCH");
//		app.add(Box.createVerticalGlue());
//		
	}
}
