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
import java.text.DecimalFormat;
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
	private JTextField startingRangeNumberInput, endRangeNumberInput, amountOfRandomNumbersInput, amountOfSetsInput;
	private JLabel startingRangeNumberDescription, endRangeNumberDescription, amountOfRandomNumbersDescription, amountOfSetsDescription;
	private JButton buttonExit, buttonSystem, buttonRandom, buttonSort, buttonKeno, buttonLotto, buttonMiniLotto;
	private String text = "";
	private JTextArea textAreaResults, textAreaInfo;
	private int startingRangeNumberValue, endRangeNumberValue, amountOfRandomNumbersValue, amountOfSetsValue;
	private double betPrice;
	private boolean systemSet;
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
		
	

		startingRangeNumberDescription = new JLabel("Jaki zakres liczb? od", JLabel.RIGHT);
		startingRangeNumberDescription.setBounds(0, 10, 140, 20);
		startingRangeNumberDescription.setForeground (Color.white);
		startingRangeNumberDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		startingRangeNumberInput = new JFormattedTextField(NumberFormat.getInstance());
		startingRangeNumberInput.setBounds(160, 10, 50, 25);
		startingRangeNumberInput.setBackground(Color.GRAY);
		startingRangeNumberInput.setFont(new Font("Verdana", Font.BOLD, 12));
		add(startingRangeNumberInput);
		add(startingRangeNumberDescription);

		endRangeNumberDescription = new JLabel("do", JLabel.RIGHT);
		endRangeNumberDescription.setBounds(0, 10, 235, 20);
		endRangeNumberDescription.setForeground (Color.white);
		endRangeNumberDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		endRangeNumberInput = new JFormattedTextField(NumberFormat.getInstance());
		endRangeNumberInput.setBounds(250, 10, 50, 25);
		endRangeNumberInput.setBackground(Color.GRAY);
		endRangeNumberInput.setFont(new Font("Verdana", Font.BOLD, 12));
		add(endRangeNumberInput);
		add(endRangeNumberDescription);
		
		buttonKeno = new JButton("KENO");
		buttonKeno.setBounds(305, 10, 60, 25);
		buttonKeno.addActionListener(this);
		buttonKeno.setBackground (Color.yellow);
		buttonKeno.setFont(new Font("Verdana", Font.BOLD, 8));
		add(buttonKeno);
		
		buttonLotto = new JButton("LOTTO");
		buttonLotto.setBounds(370, 10, 60, 25);
		buttonLotto.addActionListener(this);
		buttonLotto.setBackground (Color.yellow);
		buttonLotto.setFont(new Font("Verdana", Font.BOLD, 8));
		add(buttonLotto);
		
		buttonMiniLotto = new JButton("MINI");
		buttonMiniLotto.setBounds(435, 10, 60, 25);
		buttonMiniLotto.addActionListener(this);
		buttonMiniLotto.setBackground (Color.yellow);
		buttonMiniLotto.setFont(new Font("Verdana", Font.BOLD, 8));
		add(buttonMiniLotto);

		amountOfRandomNumbersDescription = new JLabel("Ile liczb wylosowaæ?", JLabel.RIGHT);
		amountOfRandomNumbersDescription.setBounds(0, 40, 140, 20);
		amountOfRandomNumbersDescription.setForeground (Color.white);
		amountOfRandomNumbersDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		amountOfRandomNumbersInput = new JFormattedTextField(NumberFormat.getInstance());
		amountOfRandomNumbersInput.setBounds(205, 40, 50, 25);
		amountOfRandomNumbersInput.setBackground(Color.GRAY);
		amountOfRandomNumbersInput.setFont(new Font("Verdana", Font.BOLD, 12));
		add(amountOfRandomNumbersInput);
		add(amountOfRandomNumbersDescription);

		amountOfSetsDescription = new JLabel("Ile zestawów utworzyæ?", JLabel.RIGHT);
		amountOfSetsDescription.setBounds(0, 70, 165, 20);
		amountOfSetsDescription.setForeground (Color.white);
		amountOfSetsDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		amountOfSetsInput = new JFormattedTextField(NumberFormat.getIntegerInstance());
		amountOfSetsInput.setBounds(205, 70, 50, 25);
		amountOfSetsInput.setBackground(Color.GRAY);
		amountOfSetsInput.setFont(new Font("Verdana", Font.BOLD, 12));
		add(amountOfSetsInput);
		add(amountOfSetsDescription);

		buttonSystem = new JButton("Zestawy systemowe");
		buttonSystem.setBounds(5, 105, 170, 30);
		buttonSystem.addActionListener(this);
		buttonSystem.setBackground(Color.yellow);
		buttonSystem.setFont(new Font("Verdana", Font.BOLD, 12));
		add(buttonSystem);

		buttonRandom = new JButton("Zestawy losowe");
		buttonRandom.setBounds(200, 105, 150, 30);
		buttonRandom.addActionListener(this);
		buttonRandom.setBackground(Color.yellow);
		buttonRandom.setFont(new Font("Verdana", Font.BOLD, 12));
		add(buttonRandom);
		
		buttonExit = new JButton("Wyjœcie");
		buttonExit.setBounds(365, 105, 85, 30);
		buttonExit.addActionListener(this);
		buttonExit.setForeground (Color.red);
		buttonExit.setFont(new Font("Verdana", Font.BOLD, 12));
		add(buttonExit);
		
		buttonSort = new JButton("Sortuj");
		buttonSort.setBounds(365, 70, 85, 30);
		buttonSort.addActionListener(this);
		buttonSort.setBackground (Color.yellow);
		buttonSort.setFont(new Font("Verdana", Font.BOLD, 12));
		add(buttonSort);

		textAreaResults = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(textAreaResults);
		textAreaResults.setEditable(false);
		textAreaResults.setBackground(Color.GRAY);
		textAreaResults.setFont(new Font("Verdana", Font.BOLD, 12));
		scrollPane.setBounds(10, 300, 450, 250);
		add(scrollPane);
		
		textAreaInfo = new JTextArea(text);
		textAreaInfo.setEditable(false);
		textAreaInfo.setBackground(Color.darkGray);
		textAreaInfo.setFont(new Font("Verdana", Font.BOLD, 12));
		textAreaInfo.setForeground (Color.white);
		textAreaInfo.setBounds(10, 140, 450, 150);
		add(textAreaInfo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonExit) {
			dispose();
		}

		if (source == buttonSystem) {
			lists.clear();
			createSystemSets();
		}
		
		if (source == buttonRandom) {
			lists.clear();
			createRandomSets();
		}
		
		if (source == buttonSort) {
			
			try {
				sortuj();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (source == buttonKeno) {
			startingRangeNumberInput.setText("1");
			endRangeNumberInput.setText("70");
			amountOfRandomNumbersInput.setText("10");
			amountOfSetsInput.setText("10");
			betPrice = 2;
		}
		
		if (source == buttonLotto) {
			startingRangeNumberInput.setText("1");
			endRangeNumberInput.setText("49");
			amountOfRandomNumbersInput.setText("6");
			amountOfSetsInput.setText("10");
			betPrice = 4;
		}
		
		if (source == buttonMiniLotto) {
			startingRangeNumberInput.setText("1");
			endRangeNumberInput.setText("42");
			amountOfRandomNumbersInput.setText("5");
			amountOfSetsInput.setText("10");
			betPrice = 1.5;
		}

	}

	
	public void sortuj() throws IOException {
		textAreaResults.setText("");
		for (int i = 0; i < lists.size(); i++) {
			Collections.sort(lists.get(i));
			text = lists.get(i).toString().replace("[", "").replace("]", "");
			if (systemSet == true && i > 1 && i % 3 == 0) {
					textAreaResults.append("\n");
			}
			textAreaResults.append(text + "\n");
		}
	}
	
	public void createSystemSets() {
		systemSet = true;
		if (startingRangeNumberInput.getText().isEmpty() || amountOfRandomNumbersInput.getText().isEmpty() || amountOfSetsInput.getText().isEmpty() || endRangeNumberInput.getText().isEmpty()) {
			textAreaInfo.setText("1. Wype³nij wszystkie pola liczbami." + "\n");
		} else {
			startingRangeNumberValue = Integer.parseInt(startingRangeNumberInput.getText());
			endRangeNumberValue = Integer.parseInt(endRangeNumberInput.getText());
			amountOfRandomNumbersValue = Integer.parseInt(amountOfRandomNumbersInput.getText());
			amountOfSetsValue = Integer.parseInt(amountOfSetsInput.getText());
			

			textAreaResults.setText("");
			textAreaInfo.setText("");
			
			if (startingRangeNumberValue == 0) {
				startingRangeNumberValue = 1;
				startingRangeNumberInput.setText(String.valueOf(startingRangeNumberValue));
				textAreaInfo.append("2. Pocz¹tek zakresu musi byæ wiêkszy od 0." + "\n");
				
			}
			
			if (endRangeNumberValue == 0 || endRangeNumberValue < startingRangeNumberValue) {
				endRangeNumberValue = startingRangeNumberValue + 1;
				endRangeNumberInput.setText(String.valueOf(endRangeNumberValue));
				textAreaInfo.append("3. Koniec zakresu nie mo¿e byæ mniejszy ni¿ pocz¹tek." + "\n");
			}
			
			if (amountOfRandomNumbersValue == 0) {
				amountOfRandomNumbersValue = 1;
				amountOfRandomNumbersInput.setText(String.valueOf(amountOfRandomNumbersValue));
				textAreaInfo.append("4. Iloœæ liczb musi byæ wiêksza od 0." + "\n");
			}
			
			if (amountOfRandomNumbersValue >(endRangeNumberValue - startingRangeNumberValue)) {
				amountOfRandomNumbersValue = (endRangeNumberValue - startingRangeNumberValue);
				amountOfRandomNumbersInput.setText(String.valueOf(amountOfRandomNumbersValue));
				textAreaInfo.append("5. Iloœæ liczb nie mo¿e byæ wiêksza od zakresu liczb." + "\n");
			}
			
			if (amountOfSetsValue == 0) {
				amountOfSetsValue = 1;
				amountOfSetsInput.setText(String.valueOf(amountOfSetsValue));
				textAreaInfo.append("6. Iloœæ zestawów musi byæ wiêksza od 0." + "\n");
				
			}
			
			
			for (int i = 0; i < amountOfSetsValue; i++) {

				while (set.size() < amountOfRandomNumbersValue) {
					set.add(random.nextInt(endRangeNumberValue - startingRangeNumberValue) + startingRangeNumberValue);
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
				textAreaResults.append(text + "\n");
				lists.add(list3);

				text = list1.toString().replace("[", "").replace("]", "");
				textAreaResults.append(text + "\n");
				lists.add(list1);

				for (Integer number : list1) {
					if (number < endRangeNumberValue) {
						number = number + 1;
					} else {
						number = endRangeNumberValue;
					}
					list2.add(number);
				}
				text = list2.toString().replace("[", "").replace("]", "");
				textAreaResults.append(text + "\n" + "\n");
				lists.add(list2);
			}
			
			textAreaInfo.append("\n" + "Wygenerowano " + 3 * (amountOfSetsValue) + " zak³adów po " + betPrice + " z³ = " + (3 * (amountOfSetsValue)) * betPrice + " z³");
		}

	}
	
	public void createRandomSets() {
		systemSet = false;
		if (startingRangeNumberInput.getText().isEmpty() || amountOfRandomNumbersInput.getText().isEmpty() || amountOfSetsInput.getText().isEmpty() || endRangeNumberInput.getText().isEmpty()) {
			textAreaInfo.setText("Wype³nij wszystkie pola liczbami");
		} else {
			
			startingRangeNumberValue = Integer.parseInt(startingRangeNumberInput.getText());
			endRangeNumberValue = Integer.parseInt(endRangeNumberInput.getText());
			amountOfRandomNumbersValue = Integer.parseInt(amountOfRandomNumbersInput.getText());
			amountOfSetsValue = Integer.parseInt(amountOfSetsInput.getText());
			
			textAreaResults.setText("");
			textAreaInfo.setText("");
			
			if (startingRangeNumberValue == 0) {
				startingRangeNumberValue = 1;
				startingRangeNumberInput.setText(String.valueOf(startingRangeNumberValue));
				textAreaInfo.append("2. Pocz¹tek zakresu musi byæ wiêkszy od 0." + "\n");
				
			}
			
			if (endRangeNumberValue == 0 || endRangeNumberValue < startingRangeNumberValue) {
				endRangeNumberValue = startingRangeNumberValue + 1;
				endRangeNumberInput.setText(String.valueOf(endRangeNumberValue));
				textAreaInfo.append("3. Koniec zakresu nie mo¿e byæ mniejszy ni¿ pocz¹tek." + "\n");
			}
			
			if (amountOfRandomNumbersValue == 0) {
				amountOfRandomNumbersValue = 1;
				amountOfRandomNumbersInput.setText(String.valueOf(amountOfRandomNumbersValue));
				textAreaInfo.append("4. Iloœæ liczb musi byæ wiêksza od 0." + "\n");
			}
			
			if (amountOfRandomNumbersValue >(endRangeNumberValue - startingRangeNumberValue)) {
				amountOfRandomNumbersValue = (endRangeNumberValue - startingRangeNumberValue);
				amountOfRandomNumbersInput.setText(String.valueOf(amountOfRandomNumbersValue));
				textAreaInfo.append("5. Iloœæ liczb nie mo¿e byæ wiêksza od zakresu liczb." + "\n");
			}
			
			if (amountOfSetsValue == 0) {
				amountOfSetsValue = 1;
				amountOfSetsInput.setText(String.valueOf(amountOfSetsValue));
				textAreaInfo.append("6. Iloœæ zestawów musi byæ wiêksza od 0." + "\n");
				
			}
			
			
			for (int i = 0; i < amountOfSetsValue; i++) {
				while (set.size() < amountOfRandomNumbersValue) {
					set.add(random.nextInt(endRangeNumberValue)+1);
				}
				List<Integer> list = new ArrayList<>(set);
				lists.add(list);
				set.removeAll(set);
			}
			text = lists.toString().replace("], ", "\n").replace("[", "").replace("]", "");
			textAreaResults.setText(text);
			
			textAreaInfo.append("\n" + "Wygenerowano " + amountOfSetsValue + " zak³adów po " + betPrice + " z³ = " + (betPrice * amountOfSetsValue) + " z³");
		}
		
	}

}
