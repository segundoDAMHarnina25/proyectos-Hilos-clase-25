package v4whoistaken;
import java.util.Map;

public class Pit {
	Map<Reference,Seat> seats; 

	public boolean isTaken(Reference reference) {
		return seats.get(reference).isTaken();
	}
	
	public boolean reserveSeat(Reference reference,String customer) {
		if(seats.containsKey(reference)) {
			Seat seat = seats.get(reference);
			if(seat.isTaken()) {
				return false;
			}else {
				seat.replaceValues(true,customer);
				seats.replace(reference,seat);
				return true;
			}
		}
		return false;
	}

	
}
