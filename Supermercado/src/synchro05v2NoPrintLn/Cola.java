package synchro05v2NoPrintLn;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	private boolean cerrada = false;

	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> {
			clientes.push(cliente);
		});
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}

	public synchronized Optional<Cliente> get() throws InterruptedException {
		while (!isCerrada() && isEmpty()) {
			wait();
		}
		if (isEmpty())
			return Optional.empty();
		return Optional.of(clientes.pop());
	}

	public synchronized void put(Optional<Cliente> optional) {
		if (optional.isEmpty())
			System.err.println("vacio");
		push(optional);
		notify();
	}

	public synchronized void freeCola() {
		notify();
	}

	public boolean isEmpty() {
		return clientes.isEmpty();
	}

	public int size() {
		return clientes.size();
	}

	public boolean isCerrada() {
		return cerrada;
	}

	public void cierraCola() {
		cerrada = true;

	}
}
