package challenge_theatre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
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
//				String seatNumber = seatInteger < 10 ? rowAlpha+"00"+seatInteger :
//					seatInteger >99 ? rowAlpha+""+seatInteger : rowAlpha+"0"+seatInteger; 
				seats.add(new Seat("%c%03d".formatted(rowAlpha,seatInteger)));
			}
		}
	}
	
	public boolean reserveSeats(String seatNumber) {
		boolean isReserved = false;
		Seat reservingSeat = new Seat(seatNumber);
		Seat reserved = seats.floor(reservingSeat);
		if(reserved!=null && reserved.equals(reservingSeat)) {
			if(!reserved.reserved) {
				reserved.reserved = true;
				isReserved = true;
			}else {
				System.out.println("%s is already reserved".formatted(reservingSeat));
			}
		}else {
			System.out.println("The seat %s is invalid \nSeats ranges between %s - %s".formatted(reservingSeat, seats.first(), seats.last()));
		}
		return isReserved;
	} 
	
	public boolean reserveSeats(List<String> multiSeatNumbers) {
		boolean isReserved = false;
		NavigableSet<Seat> multiReservingSeats = new TreeSet<>();
		for(String seatNumber: multiSeatNumbers) {
			multiReservingSeats.add(new Seat(seatNumber));
		}
		
		if(seats.containsAll(multiReservingSeats)) {
			boolean isAllUnReserved = true;
			for (Seat reservingSeat : multiReservingSeats) {
				Seat reserved = seats.floor(reservingSeat);
				if(reserved.reserved) {
					isAllUnReserved = false;
					break;
				}
			}
			if(isAllUnReserved) {
				multiReservingSeats.forEach(s -> s.reserved = true);
				seats.removeAll(multiReservingSeats);
				seats.addAll(multiReservingSeats);
				isReserved = true;
			}else {
				System.out.println("Some of the seats were already books!");
			}
		}else {
			System.out.println("Entered seat numbers invalid");
		}
		return isReserved;
	}
	
	public void printSeats() {
		String lineSeperator = "-".repeat(90);
		System.out.println("%s%n%s%n%s%n%s".formatted(lineSeperator,this.theatreName,lineSeperator,"Seat map"));
		int count=0;
		for(Seat s: seats) {
			System.out.print("%8s%s".formatted(s,s.reserved? "(R)":"   "));
			if(++count%noOfSeatsInRow ==0) {
				System.out.println();
			}
			
		}
		
		System.out.println(lineSeperator);
	}
	
	private boolean validate(int count, char first, char last, int min, int max) {

        boolean result = (min > 0 || noOfSeatsInRow >= count || (max - min + 1) >= count);
        result = result && seats.contains(new Seat("%c%03d".formatted(first, min)));
        if (!result) {
            System.out.printf("Invalid! %1$d seats between " +
                            "%2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again",
                    count, first, min, max, last);
            System.out.printf(": Seat must be between %s and %s%n",
                    seats.first().seatNumber, seats.last().seatNumber);
        }

        return result;
    }

    public Set<Seat> reserveSeats(int count,  char minRow, char maxRow,
                                  int minSeat, int maxSeat) {

    	
        char lastValid = seats.last().seatNumber.charAt(0);
        maxRow = (maxRow < lastValid) ? maxRow : lastValid;

        if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
            return  null;
        }

        NavigableSet<Seat> selected = null;

        for (char letter = minRow; letter <= maxRow; letter++) {

            NavigableSet<Seat> contiguous = seats.subSet(
                    new Seat("%c%03d".formatted(letter, minSeat)), true,
                    new Seat("%c%03d".formatted(letter, maxSeat)), true);

            int index = 0;
            Seat first = null;
            for (Seat current : contiguous) {
                if (current.reserved) {
                    index = 0;
                    continue;
                }
                first = (index == 0) ? current : first;
                if (++index == count) {
                    selected = contiguous.subSet(first, true,
                            current, true);
                    break;
                }
            }
            if (selected != null) {
                break;
            }
        }

        Set<Seat> reservedSeat = null;
        if (selected != null) {
            selected.forEach(s -> s.reserved = true);
            reservedSeat = new TreeSet<>(selected);
        }
        return reservedSeat;
    }
	
	 class Seat implements Comparable<Seat>{

		private boolean reserved;
		private String seatNumber;
		
		Seat(String seatNumber){
			reserved = false;
			this.seatNumber = seatNumber;
		}
		
		
		
		
		@Override
		public String toString() {
			return seatNumber;
		}

		@Override
		public int compareTo(Seat o) {
			
			return this.seatNumber.compareTo(o.seatNumber);
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
