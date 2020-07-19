import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.NumberFormatter;

public class GUI extends JFrame implements ActionListener {
	private JTextField tA, tB, tC, tA2;
	private JLabel lA, lB, lC, lA2;
	private JButton bWyjœcie, bSystem, bLosowy, bSortuj, bKeno, bLotto, bMiniLotto;
	private String text = "";
	private JTextArea tWynik, tInfo;
	private int first, second, third, fourth;
	private double cenaZak³adu;
	private boolean system;
	List<List<Integer>> lists = new ArrayList<List<Integer>>();
	
	Random random = new Random();
	Set<Integer> set = new HashSet<>();
	Scanner scan = new Scanner(System.in);

	public GUI() {
		setLayout(null);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	

		lA = new JLabel("Jaki zakres liczb? od", JLabel.RIGHT);
		lA.setBounds(0, 10, 140, 20);
		lA.setForeground (Color.white);
		lA.setFont(new Font("Verdana", Font.BOLD, 12));
		tA = new JFormattedTextField(NumberFormat.getInstance());
		tA.setBounds(160, 10, 50, 25);
		tA.setBackground(Color.GRAY);
		tA.setFont(new Font("Verdana", Font.BOLD, 12));
		add(tA);
		add(lA);

		lA2 = new JLabel("do", JLabel.RIGHT);
		lA2.setBounds(0, 10, 235, 20);
		lA2.setForeground (Color.white);
		lA2.setFont(new Font("Verdana", Font.BOLD, 12));
		tA2 = new JFormattedTextField(NumberFormat.getInstance());
		tA2.setBounds(250, 10, 50, 25);
		tA2.setBackground(Color.GRAY);
		tA2.setFont(new Font("Verdana", Font.BOLD, 12));
		add(tA2);
		add(lA2);
		
		bKeno = new JButton("KENO");
		bKeno.setBounds(305, 10, 60, 25);
		bKeno.addActionListener(this);
		bKeno.setBackground (Color.yellow);
		bKeno.setFont(new Font("Verdana", Font.BOLD, 8));
		add(bKeno);
		
		bLotto = new JButton("LOTTO");
		bLotto.setBounds(370, 10, 60, 25);
		bLotto.addActionListener(this);
		bLotto.setBackground (Color.yellow);
		bLotto.setFont(new Font("Verdana", Font.BOLD, 8));
		add(bLotto);
		
		bMiniLotto = new JButton("MINI");
		bMiniLotto.setBounds(435, 10, 60, 25);
		bMiniLotto.addActionListener(this);
		bMiniLotto.setBackground (Color.yellow);
		bMiniLotto.setFont(new Font("Verdana", Font.BOLD, 8));
		add(bMiniLotto);

		lB = new JLabel("Ile liczb wylosowaæ?", JLabel.RIGHT);
		lB.setBounds(0, 40, 140, 20);
		lB.setForeground (Color.white);
		lB.setFont(new Font("Verdana", Font.BOLD, 12));
		tB = new JFormattedTextField(NumberFormat.getInstance());
		tB.setBounds(205, 40, 50, 25);
		tB.setBackground(Color.GRAY);
		tB.setFont(new Font("Verdana", Font.BOLD, 12));
		add(tB);
		add(lB);

		lC = new JLabel("Ile zestawów utworzyæ?", JLabel.RIGHT);
		lC.setBounds(0, 70, 165, 20);
		lC.setForeground (Color.white);
		lC.setFont(new Font("Verdana", Font.BOLD, 12));
		tC = new JFormattedTextField(NumberFormat.getInstance());
		tC.setBounds(205, 70, 50, 25);
		tC.setBackground(Color.GRAY);
		tC.setFont(new Font("Verdana", Font.BOLD, 12));
		add(tC);
		add(lC);

		bSystem = new JButton("Zestawy systemowe");
		bSystem.setBounds(5, 105, 170, 30);
		bSystem.addActionListener(this);
		bSystem.setBackground(Color.yellow);
		bSystem.setFont(new Font("Verdana", Font.BOLD, 12));
		add(bSystem);

		bLosowy = new JButton("Zestawy losowe");
		bLosowy.setBounds(200, 105, 150, 30);
		bLosowy.addActionListener(this);
		bLosowy.setBackground(Color.yellow);
		bLosowy.setFont(new Font("Verdana", Font.BOLD, 12));
		add(bLosowy);
		
		bWyjœcie = new JButton("Wyjœcie");
		bWyjœcie.setBounds(365, 105, 85, 30);
		bWyjœcie.addActionListener(this);
		bWyjœcie.setForeground (Color.red);
		bWyjœcie.setFont(new Font("Verdana", Font.BOLD, 12));
		add(bWyjœcie);
		
		bSortuj = new JButton("Sortuj");
		bSortuj.setBounds(365, 70, 85, 30);
		bSortuj.addActionListener(this);
		bSortuj.setBackground (Color.yellow);
		bSortuj.setFont(new Font("Verdana", Font.BOLD, 12));
		add(bSortuj);

		tWynik = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(tWynik);
		tWynik.setEditable(false);
		tWynik.setBackground(Color.GRAY);
		tWynik.setFont(new Font("Verdana", Font.BOLD, 12));
		scrollPane.setBounds(10, 300, 450, 250);
		add(scrollPane);
		
		tInfo = new JTextArea(text);
		tInfo.setEditable(false);
		tInfo.setBackground(Color.darkGray);
		tInfo.setFont(new Font("Verdana", Font.BOLD, 12));
		tInfo.setForeground (Color.white);
		tInfo.setBounds(10, 140, 450, 150);
		add(tInfo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object zród³o = e.getSource();
		if (zród³o == bWyjœcie) {
			dispose();
		}

		if (zród³o == bSystem) {
			lists.clear();
			generujZestawySystemowe();
		}
		
		if (zród³o == bLosowy) {
			lists.clear();
			generujZestawyLosowe();
		}
		
		if (zród³o == bSortuj) {
			
			try {
				sortuj();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (zród³o == bKeno) {
			tA.setText("1");
			tA2.setText("70");
			tB.setText("10");
			tC.setText("10");
			cenaZak³adu = 2;
		}
		
		if (zród³o == bLotto) {
			tA.setText("1");
			tA2.setText("49");
			tB.setText("6");
			tC.setText("10");
			cenaZak³adu = 4;
		}
		
		if (zród³o == bMiniLotto) {
			tA.setText("1");
			tA2.setText("42");
			tB.setText("5");
			tC.setText("10");
			cenaZak³adu = 1.5;
		}

	}

	
	public void sortuj() throws IOException {
		tWynik.setText("");
		for (int i = 0; i < lists.size(); i++) {
			Collections.sort(lists.get(i));
			text = lists.get(i).toString().replace("[", "").replace("]", "");
			if (system == true && i > 1 && i % 3 == 0) {
					tWynik.append("\n");
			}
			tWynik.append(text + "\n");
		}
	}
	
	public void generujZestawySystemowe() {
		system = true;
		if (tA.getText().isEmpty() || tB.getText().isEmpty() || tC.getText().isEmpty() || tA2.getText().isEmpty()) {
			tInfo.setText("1. Wype³nij wszystkie pola liczbami." + "\n");
		} else {
			first = Integer.parseInt(tA.getText());
			second = Integer.parseInt(tA2.getText());
			third = Integer.parseInt(tB.getText());
			fourth = Integer.parseInt(tC.getText());
			

			tWynik.setText("");
			tInfo.setText("");
			
			if (first == 0) {
				first = 1;
				tA.setText(String.valueOf(first));
				tInfo.append("2. Pocz¹tek zakresu musi byæ wiêkszy od 0." + "\n");
				
			}
			
			if (second == 0 || second < first) {
				second = first + 1;
				tA2.setText(String.valueOf(second));
				tInfo.append("3. Koniec zakresu nie mo¿e byæ mniejszy ni¿ pocz¹tek." + "\n");
			}
			
			if (third == 0) {
				third = 1;
				tB.setText(String.valueOf(third));
				tInfo.append("4. Iloœæ liczb musi byæ wiêksza od 0." + "\n");
			}
			
			if (third >(second - first)) {
				third = (second - first);
				tB.setText(String.valueOf(third));
				tInfo.append("5. Iloœæ liczb nie mo¿e byæ wiêksza od zakresu liczb." + "\n");
			}
			
			if (fourth == 0) {
				fourth = 1;
				tC.setText(String.valueOf(fourth));
				tInfo.append("6. Iloœæ zestawów musi byæ wiêksza od 0." + "\n");
				
			}
			
			
			for (int i = 0; i < fourth; i++) {

				while (set.size() < third) {
					set.add(random.nextInt(second - first) + first);
				}

				List<Integer> list1 = new ArrayList<>(set);
				List<Integer> list2 = new ArrayList<>();
				List<Integer> list3 = new ArrayList<>();

				set.removeAll(set);

				for (Integer number : list1) {
					if (number > 1) {
						number = number - 1;
					} else {
						number = 1;
					}
					list3.add(number);
				}

				text = list3.toString().replace("[", "").replace("]", "");
				tWynik.append(text + "\n");
				lists.add(list3);

				text = list1.toString().replace("[", "").replace("]", "");
				tWynik.append(text + "\n");
				lists.add(list1);

				for (Integer number : list1) {
					if (number < second) {
						number = number + 1;
					} else {
						number = second;
					}
					list2.add(number);
				}
				text = list2.toString().replace("[", "").replace("]", "");
				tWynik.append(text + "\n" + "\n");
				lists.add(list2);
			}
			
			tInfo.append("\n" + "Wygenerowano " + 3 * (fourth) + " zak³adów po " + cenaZak³adu + " z³ = " + (3 * (fourth)) * cenaZak³adu + " z³");
		}

	}
	
	public void generujZestawyLosowe() {
		system = false;
		if (tA.getText().isEmpty() || tB.getText().isEmpty() || tC.getText().isEmpty() || tA2.getText().isEmpty()) {
			tInfo.setText("Wype³nij wszystkie pola liczbami");
		} else {
			
			int first = Integer.parseInt(tA.getText());
			int second = Integer.parseInt(tB.getText());
			int third = Integer.parseInt(tC.getText());
			int fourth = Integer.parseInt(tA2.getText());

			tWynik.setText("");
			tInfo.setText("");
			
			if (first == 0) {
				first = 1;
				tA.setText(String.valueOf(first));
				tInfo.append("2. Pocz¹tek zakresu musi byæ wiêkszy od 0." + "\n");
				
			}
			
			if (fourth == 0 || fourth < first) {
				fourth = first + 1;
				tA2.setText(String.valueOf(fourth));
				tInfo.append("3. Koniec zakresu nie mo¿e byæ mniejszy ni¿ pocz¹tek." + "\n");
			}
			
			if (second == 0) {
				second = 1;
				tB.setText(String.valueOf(second));
				tInfo.append("4. Iloœæ liczb musi byæ wiêksza od 0." + "\n");
			}
			
			if (second >(fourth - first)) {
				second = (fourth - first);
				tB.setText(String.valueOf(second));
				tInfo.append("5. Iloœæ liczb nie mo¿e byæ wiêksza od zakresu liczb." + "\n");
			}
			
			if (third == 0) {
				third = 1;
				tC.setText(String.valueOf(third));
				tInfo.append("6. Iloœæ zestawów musi byæ wiêksza od 0." + "\n");
				
			}
			
			
			for (int i = 0; i < third; i++) {
				while (set.size() < second) {
					set.add(random.nextInt(fourth)+1);
				}
				List<Integer> list = new ArrayList<>(set);
				lists.add(list);
				set.removeAll(set);
			}
			text = lists.toString().replace("], ", "\n").replace("[", "").replace("]", "");
			tWynik.setText(text);
			
			tInfo.append("\n" + "Wygenerowano " + third + " zak³adów po " + cenaZak³adu + " z³ = " + (cenaZak³adu * third) + " z³");
		}
		
	}

}
