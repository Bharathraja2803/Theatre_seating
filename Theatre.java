package challenge_theatre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
		Seat reserved = seats.floor(reservingSeat);
		if(reserved!=null && reserved.equals(reservingSeat)) {
			if(!reserved.getReservationStatus()) {
				reserved.reserveSeat();
				System.out.println("%s is reserved successfully!".formatted(reserved));
			}else {
				System.out.println("%s is already reserved".formatted(reservingSeat));
			}
		}else {
			System.out.println("The seat %s is invalid \nSeats ranges between %s - %s".formatted(reservingSeat, seats.first(), seats.last()));
		}
		
	} 
	
	public void reserveSeat(List<String> multiSeatNumbers) {
		NavigableSet<Seat> multiReservingSeats = new TreeSet<>();
		for(String seatNumber: multiSeatNumbers) {
			multiReservingSeats.add(new Seat(seatNumber));
		}
		
		if(seats.containsAll(multiReservingSeats)) {
			boolean isAllUnReserved = true;
			for (Seat reservingSeat : multiReservingSeats) {
				Seat reserved = seats.floor(reservingSeat);
				if(reserved.getReservationStatus()) {
					isAllUnReserved = false;
					break;
				}
			}
			if(isAllUnReserved) {
				multiReservingSeats.forEach(Seat::reserveSeat);
				seats.removeAll(multiReservingSeats);
				seats.addAll(multiReservingSeats);
				System.out.println(multiReservingSeats+" seats were reserved successfully!");
			}else {
				System.out.println("Try with another List of seats \nbecause some of the seats mentioned is already booked!");
			}
		}else {
			System.out.println("Entered seat numbers invalid");
		}
	}
	
	public void printSeats() {
		String lineSeperator = "-".repeat(90);
		System.out.println("%s%n%s%n%s%n%s".formatted(lineSeperator,this.theatreName,lineSeperator,"Seat map"));
		int count=0;
		for(Seat s: seats) {
			System.out.print("%8s(%s)".formatted(s,s.getReservationStatus()? "R":"U"));
			if(++count%noOfSeatsInRow ==0) {
				System.out.println();
			}
			
		}
		
		System.out.println(lineSeperator);
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
			return getSeatNumber();
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
