package v4whoistaken;

import java.util.Objects;
import java.util.Optional;

public class Seat {
	private Reference reference;
	//voy a crear el patio de butacas
	boolean taken=false;
	Optional<String> customer=Optional.empty();
	
	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public Optional<String> getCustomer() {
		return customer;
	}

	public void setCustomer(Optional<String> customer) {
		this.customer = customer;
	}

	public Seat(Reference reference) {
		super();
		this.reference = reference;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public boolean isTaken() {
		return taken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}
	
	public void replaceValues(boolean state,String customer) {
		setTaken(state);
		setCustomer(Optional.of(customer));
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
