import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JTextField;

public class Generator {

	Random random = new Random();
	Set<Integer> set = new HashSet<>();
	Scanner scan = new Scanner(System.in);

	public void generateSet() {
		System.out.println("Podaj z jak du¿ego zestawu liczb losowaæ:");
		int first = scan.nextInt();
		System.out.println("Podaj ile liczb wylosowaæ z podanego zestawu:");
		int second = scan.nextInt();
		System.out.println("Podaj ile zestawów wygenerowaæ:");
		int third = scan.nextInt();
		System.out.println();
		for (int i = 0; i < third; i++) {

			while (set.size() < second) {
				set.add(random.nextInt((int) first) + 1);
			}
			List<Integer> list = new ArrayList<>(set);
			set.removeAll(set);
			Collections.sort(list);
			for (Integer number : list) {
				System.out.print(number + ",");
			}
			System.out.println();
			for (Integer number : list) {
				if (number < first) {
					System.out.print(number + 1 + ",");
				} else
					System.out.print(first + ",");
			}
			System.out.println();
			for (Integer number : list) {
				if (number > 1) {
					System.out.print(number - 1 + ",");
				} else
					System.out.print(1 + ",");
			}
			System.out.println();
			System.out.println();
		}
	}
}
