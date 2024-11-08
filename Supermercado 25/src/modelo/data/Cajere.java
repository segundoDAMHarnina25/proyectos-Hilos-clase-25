package modelo.data;

import java.util.ArrayDeque;
import java.util.Objects;

public class Cajere implements Runnable {
	private final String DNI;
	private int numeroCaja;
	private float lapso;

	public Cajere(String dNI, float lapso) {
		super();
		DNI = dNI;
		this.lapso = lapso;
	}

	private int getNumeroCaja() {
		return numeroCaja;
	}

	private void setNumeroCaja(int numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	private String getDNI() {
		return DNI;
	}

	private float getLapso() {
		return lapso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DNI);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cajere other = (Cajere) obj;
		return Objects.equals(DNI, other.DNI);
	}

	@Override
	public void run() {
		while(trabaja) {
			ArrayDeque cola=new ArrayDeque<>();
			Cliente cliente=cola.poll();
		}
		
	}

}
