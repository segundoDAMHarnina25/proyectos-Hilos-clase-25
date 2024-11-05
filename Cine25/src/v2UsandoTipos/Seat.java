package v2UsandoTipos;

import java.util.Objects;

public class Seat {
	private Reference reference;
	//voy a crear el patio de butacas
	boolean seatTaken=false;
	
	public Seat(Reference reference) {
		super();
		this.reference = reference;
	}

	public boolean isTaken() {
		return seatTaken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
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
		return Objects.equals(reference, other.reference);
	}

	public boolean isEqualReference(Reference reference2) {
		return this.reference.equals(reference2);
	}
	
}
