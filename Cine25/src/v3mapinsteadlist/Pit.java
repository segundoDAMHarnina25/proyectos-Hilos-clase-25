package v3mapinsteadlist;
import java.util.Map;

public class Pit {
	Map<Reference,Boolean> seats; 

	public boolean isTaken(Reference reference) {
		return seats.get(reference);
	}
	
	public boolean reserveSeat(Reference reference) {
		if(seats.containsKey(reference)) {
			if(seats.get(reference)) {
				return false;
			}else {
				seats.put(reference,true);
				return true;
			}
		}
		return false;
	}
}
