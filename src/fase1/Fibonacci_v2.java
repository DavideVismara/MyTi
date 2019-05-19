package fase1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fibonacci_v2 {

	private BigDecimal firstNumber;
	private int positionofFirstNumber;
	private int n_digits;

	public Fibonacci_v2(int n_digits) {
		this.positionofFirstNumber = getFirstNumberWithDigits(n_digits);
		this.firstNumber = getNFibonacci(positionofFirstNumber);
	}

	public BigDecimal getNFibonacci(int n) {

		// formula di Binet per calcolare F(n) senza dover sapere F(n-1) e F(n-2)

		BigDecimal A = ((sqrt(5).add(BigDecimal.valueOf(1))).divide(BigDecimal.valueOf(2))).pow(n);
		BigDecimal B = ((sqrt(5).subtract(BigDecimal.valueOf(1))).divide(BigDecimal.valueOf(2))).pow(n);
		BigDecimal F = (A.subtract(B)).divide(sqrt(5), 30, RoundingMode.HALF_UP);
		F = F.setScale(0, RoundingMode.HALF_DOWN);
		return F;
	}

	public int getFirstNumberWithDigits(int n_digits_request) {

		// ricerca del numero partendo da F(numerocifre_richiesta)
		// se non va a buon fine mi avvicino al numero richiesto
		// guardando quante cifre ha
		// F(numerocifre_richiesta + (numerocifre_richiesta-numero cifre_attuali) / 2)

		int n_try = n_digits_request;
		BigDecimal f_n_try = getNFibonacci(n_try);
		int n_digits_actual = numberDigits(f_n_try);

		while (n_digits_actual < n_digits_request) {
			n_try = n_try + (int) Math.floor(((n_digits_request - n_digits_actual) / 2)) + 1;
			f_n_try = getNFibonacci(n_try);
			n_digits_actual = numberDigits(f_n_try);
		}

		setN_digits(n_digits_actual);
		return n_try;
	}

	public int numberDigits(BigDecimal number) {

		// Per contare il numero di cifre basterebbe fare il Log (base10) del numero
		// Per BigDecimal non c'è la funzione logaritmo, bisognerebbe implementarla
		// Opto per questa soluzione più semplice

		int digits = 0;
		while (number.compareTo(BigDecimal.valueOf(1)) == 1) {
			number = number.divide(BigDecimal.valueOf(10));
			digits++;
		}
		return digits;
	}

	public static BigDecimal sqrt(int x) {

		// Per BigDecimal è stata implmentata la funzione radice
		return BigDecimal.valueOf(StrictMath.sqrt(x));
	}

	public String getFibonacciAnswer() {
		return String.format("Result: f(%s) = %s ha %s cifre", getPositionofFirstNumber(), getFirstNumber(),
				getN_digits());
	}

	public int getN_digits() {
		return n_digits;
	}

	public void setN_digits(int n_digits) {
		this.n_digits = n_digits;
	}

	public BigDecimal getFirstNumber() {
		return firstNumber;
	}

	public int getPositionofFirstNumber() {
		return positionofFirstNumber;
	}
}
