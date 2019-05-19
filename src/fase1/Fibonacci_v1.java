package fase1;

import java.util.ArrayList;

//prima implentazione sequenza fibonacci,
//Classe non utilizzata

public class Fibonacci_v1 {
	static int f0 = 0;
	static int f1 = 1;

	int limite_superiore;
	ArrayList<Integer> sequenza;

	Fibonacci_v1(int max) {
		limite_superiore = max;
		sequenza = new ArrayList<Integer>();

		calcolaSequenza();
	}

	private void calcolaSequenza() {
		sequenza.add(f0);
		sequenza.add(f1);
		int i = 1;
		while (sequenza.get(i) < limite_superiore) {
			i++;
			sequenza.add(i, sequenza.get(i - 1) + sequenza.get(i - 2));
		}
	}

	public int getLastElement() {
		return sequenza.get(sequenza.size() - 1);
	}

	public int getLunghezzaSequenza() {
		return sequenza.size() - 1;
	}

	public void printSequenza() {
		for (int i = 0; i < sequenza.size(); i++) {
			System.out.print(String.format("[%s]=%s ", i, sequenza.get(i)));
		}
	}

}
