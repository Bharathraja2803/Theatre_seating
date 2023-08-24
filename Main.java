package challenge_theatre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import challenge_theatre.Theatre.Seat;



public class Main {
	public static final String lineSeperator = "=".repeat(90);
	public static void main(String[] args) {
		Theatre viswanath = new Theatre("Vishwanath", 10, 100);
		viswanath.printSeats();
		
		bookSeat(viswanath, "D004");
		bookSeat(viswanath, "D004");
		
		bookSeat(viswanath, 'A',10);
		
		bookSeat(viswanath, 6, 'C', 'F', 3,10);
		bookSeat(viswanath, 6, 'C', 'F', 1,7);
		
	}
	
	public static void bookSeat(Theatre theatre, String seatNumber) {
		boolean isReserved = theatre.reserveSeats(seatNumber);
		if(isReserved) {
			theatre.printSeats();
			System.out.println("Congradulations %s seat reserved!".formatted(seatNumber));
			System.out.println(lineSeperator);
		}else {
			System.out.println("Sorry %s seat reservation failed".formatted(seatNumber));
		}
	}
	
	public static void bookSeat(Theatre theatre, char rowLetter, int seatNum) {
		bookSeat(theatre, "%c%03d".formatted(rowLetter, seatNum));
	}
	
	public static void bookSeat(Theatre theatre, List<String> seatNumbers) {
		boolean isReserved = theatre.reserveSeats(seatNumbers);
		if(isReserved) {
			theatre.printSeats();
			System.out.println("Congradulation-s %s seats reserved!".formatted(seatNumbers));
			System.out.println(lineSeperator);
			
		}else {
			System.out.println("Sorry %s seats reservation failed".formatted(seatNumbers));
		}
	}
	
	public static void bookSeat(Theatre theatre, int count, char minRow, char maxRow, int minSeat, int maxSeat) {
		Set<Seat> reservedSeat = theatre.reserveSeats(count, minRow, maxRow, minSeat, maxSeat);
		if(reservedSeat!=null) {
			theatre.printSeats();
			System.out.println("Congradulations %s seats reserved!".formatted(reservedSeat));
			System.out.println(lineSeperator);
		}else {
			
			System.out.println("Sorry seat reservation failed");
		}
	}
	
	public static void bookSeat(Theatre theatre, int count, char rowLetter, int minSeat, int maxSeat) {
		bookSeat(theatre, count, rowLetter, rowLetter, minSeat, maxSeat);
	}
}
