package v1tontunatipoprimitivos;
import java.util.ArrayList;
import java.util.Iterator;

public class Pit {
	ArrayList<Seat> seats;

	public boolean isTaken(char row, int colum) {
		int i = 0;
		do {
			Seat seat = seats.get(i);
			if (seat.columSeat == colum && row == seat.rowSeat) {
				return seat.isTaken();
			}
		} while (i < seats.size());
		return false;
	}
}
