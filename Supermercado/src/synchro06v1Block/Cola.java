package synchro06v1Block;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	boolean finalizado=false;
	
	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> clientes.push(cliente));
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}

	public Optional<Cliente> get() throws InterruptedException {
		if(clientes.isEmpty()) return Optional.empty();
		return Optional.of(clientes.pop());
	}

	private void put(Optional<Cliente> optional) {
		push(optional);
	}

	public void turno() {
		notifyAll();
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public  boolean colaAcabada() {
		return isFinalizado()&&clientes.isEmpty();
	}

	public boolean isEmpty() {
		return clientes.isEmpty();
	}
}
