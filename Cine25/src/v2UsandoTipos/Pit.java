package v2UsandoTipos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Pit {
	ArrayList<Seat> seats;

	public boolean isTaken(Reference reference) {
		Optional<Seat> first = seats.stream()
				.filter((seat)->seat.isEqualReference(reference))
				.findFirst();
		if(first.isPresent()) {
			return first.get().isTaken();
		}
		return false;
	}
}
