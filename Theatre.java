package challenge_theatre;

import java.util.Collection;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.Objects;
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
				String seatNumber = seatInteger < 10 ? rowAlpha+"00"+seatInteger :
					seatInteger >99 ? rowAlpha+""+seatInteger : rowAlpha+"0"+seatInteger; 
				seats.add(new Seat(seatNumber));
			}
		}
	}
	
	public void reserveSeats(String seatNumber) {
		Seat reservingSeat = new Seat(seatNumber);
		if(seats.contains(reservingSeat) && !reservingSeat.getReservationStatus()) {
			reservingSeat.reserveSeat();
			seats.remove(reservingSeat);
			seats.add(reservingSeat);
		}
	} 
	
	public void printSeats() {
		String lineSeperator = "-".repeat(90);
		System.out.println(lineSeperator);
		int count=0;
		for(Seat s: seats) {
			System.out.print(s);
			if(++count%noOfSeatsInRow ==0) {
				System.out.println();
			}
			
		}
	}
	
	private class Seat implements Comparable<Seat>{

		private boolean reserved;
		private String seatNumber;
		
		private Seat(String seatNumber){
			reserved = false;
			this.seatNumber = seatNumber;
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
		
		private boolean getReservationStatus() {
			return this.reserved; 
		}
		
		@Override
		public String toString() {
			return "%s(%s)".formatted(seatNumber, reserved ? "R":"U");
		}

		@Override
		public int compareTo(Seat o) {
			
			return this.getSeatNumber().compareTo(o.getSeatNumber());
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(seatNumber);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Seat other = (Seat) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(seatNumber, other.seatNumber);
		}

		private Theatre getEnclosingInstance() {
			return Theatre.this;
		}
		
	}
}
