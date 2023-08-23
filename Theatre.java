package challenge_theatre;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Theatre {
	private String theatreName;
	private int noOfSeatsInRow;
	private int noOfRows;
	private NavigableSet<Seat> seats;
	
	public Theatre(String theatreName, int noOfRows, int noOfSeats) {
		this.theatreName = theatreName;
		this.noOfRows = noOfRows;
		this.noOfSeatsInRow = noOfSeats / noOfRows;
		this.seats = new TreeSet<>();
		settingUpTheSeats();
	}
	
	private void settingUpTheSeats() {
		for(int i =0; i<this.noOfRows; i++) {
			char rowAlpha = (char)(65+i);
			for(int j =0 ; j<this.noOfSeatsInRow; j++) {
				int seatInteger = j+1;
				seats.add(new Seat(rowAlpha, seatInteger));
			}
		}
	}
	
	private void reserveSeats(String seatNumber) {
		this.seats.contains()
	}
	
	private class Seat implements Comparable<Seat>{
		private char rowCharacter;
		private int seatInteger;
		private boolean reserved;
		private String seatNumber;
		
		private Seat(char rowCharacter, int seatInteger){
			this.rowCharacter = rowCharacter;
			this.seatInteger = seatInteger;
			reserved = false;
			this.seatNumber = seatInteger < 10 ? rowCharacter+"00"+seatInteger :
				seatInteger >99 ? rowCharacter+""+seatInteger : rowCharacter+"0"+seatInteger;
		}
		
		private String getSeatNumber() {
			return this.seatNumber;
		}
		
		private void reserveSeat() {
			this.reserved = true;
		}
		
		private void unReserved() {
			this.reserved = false;
		}
		
		
		
		@Override
		public String toString() {
			return this.getSeatNumber();
		}

		@Override
		public int compareTo(Seat o) {
			
			return this.getSeatNumber().compareTo(o.getSeatNumber());
		}
	}
}
