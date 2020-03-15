import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame implements ActionListener {
	private JTextField tA, tB, tC, tA2;
	private JLabel lA, lB, lC, lA2, lInfo;
	private JButton bWyjœcie, bGeneruj;
	private String text = "";
	private JTextArea tWynik;

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

		lInfo = new JLabel("");
		lInfo.setBounds(15, 175, 450, 20);
		lInfo.setForeground(Color.red);
		add(lInfo);

		lA = new JLabel("Jaki zakres liczb?    od", JLabel.RIGHT);
		lA.setBounds(0, 50, 150, 20);
		tA = new JTextField();
		tA.setBounds(160, 50, 50, 25);
		add(tA);
		add(lA);

		lA2 = new JLabel("do", JLabel.RIGHT);
		lA2.setBounds(0, 50, 235, 20);
		tA2 = new JTextField();
		tA2.setBounds(250, 50, 50, 25);
		add(tA2);
		add(lA2);

		lB = new JLabel("Ile liczb wylosowaæ?", JLabel.RIGHT);
		lB.setBounds(15, 80, 120, 20);
		tB = new JTextField();
		tB.setBounds(200, 80, 50, 25);
		add(tB);
		add(lB);

		lC = new JLabel("Ile zestawów wygenerowaæ?", JLabel.RIGHT);
		lC.setBounds(15, 110, 165, 20);
		tC = new JTextField();
		tC.setBounds(200, 110, 50, 25);
		add(tC);
		add(lC);

		bGeneruj = new JButton("Generuj zestawy");
		bGeneruj.setBounds(15, 150, 150, 20);
		bGeneruj.addActionListener(this);
		add(bGeneruj);

		bWyjœcie = new JButton("Wyjœcie");
		bWyjœcie.setBounds(200, 150, 150, 20);
		bWyjœcie.addActionListener(this);
		add(bWyjœcie);

		tWynik = new JTextArea(text);
		JScrollPane scrollPane = new JScrollPane(tWynik);
		tWynik.setEditable(false);
		scrollPane.setBounds(10, 200, 450, 250);
		add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object zród³o = e.getSource();
		if (zród³o == bWyjœcie) {
			dispose();
		}

		if (zród³o == bGeneruj) {

			generujZestawy();
		}

	}

	public void generujZestawy() {
		int first = Integer.parseInt(tA.getText());
		int second = Integer.parseInt(tB.getText());
		int third = Integer.parseInt(tC.getText());
		int fourth = Integer.parseInt(tA2.getText());

		tWynik.setText("");
		lInfo.setText("");
		
		for (int i = 0; i < third; i++) {

			while (set.size() < second) {
				set.add(random.nextInt(fourth - first) + first);
			}

			List<Integer> list1 = new ArrayList<>(set);
			List<Integer> list2 = new ArrayList<>();
			List<Integer> list3 = new ArrayList<>();

			set.removeAll(set);
			Collections.sort(list1);

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

			text = list1.toString().replace("[", "").replace("]", "");
			tWynik.append(text + "\n");

			for (Integer number : list1) {
				if (number < fourth) {
					number = number + 1;
				} else {
					number = fourth;
				}
				list2.add(number);
			}
			text = list2.toString().replace("[", "").replace("]", "");
			tWynik.append(text + "\n" + "\n");

		}

		lInfo.setText("Wygenerowano " + 3 * (third) + " zak³adów po 2 z³ = " + 6 * (third) + " z³");

	}

}
