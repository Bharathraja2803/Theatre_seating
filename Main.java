package challenge_theatre;

public class Main {
	public static void main(String[] args) {
		Theatre viswanath = new Theatre("Vishwanath", 10, 100);
		viswanath.printSeats();
		viswanath.reserveSeats("D004");
		viswanath.printSeats();
	}
}
