package challenge_theatre;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Theatre viswanath = new Theatre("Vishwanath", 10, 100);
		viswanath.printSeats();
		viswanath.reserveSeats("D004"); //booking a single unreserved seat
		viswanath.printSeats();
		viswanath.reserveSeats("D004"); //booking a single reserved seat
		viswanath.printSeats();
		viswanath.reserveSeats("A011"); //booking a single invalid seat
		viswanath.printSeats();
		List<String> bulkReservationNumbers = new ArrayList<>(List.of(
				"A002","A003","A004","A005","A006","A007","A008"
				));// all are unreserved previously
		
		viswanath.reserveSeat(bulkReservationNumbers);
		viswanath.printSeats();
		List<String> bulkReservationNumbers2 = new ArrayList<>(List.of(
				"A002","B003","B004","B005","B006"
				)); //A002 is already booked
		viswanath.reserveSeat(bulkReservationNumbers2);
		viswanath.printSeats();
	}
	
}
