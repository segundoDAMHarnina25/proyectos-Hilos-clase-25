package synchro01;

import java.util.ArrayDeque;
import java.util.Optional;

import presentacion.Cliente;


public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();

	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> clientes.push(cliente));
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}

	public synchronized Cliente get() throws InterruptedException {
		while (clientes.isEmpty()) {
			wait();
		}
		return clientes.pop();
	}

	public synchronized void put(Optional<Cliente> optional) {
		push(optional);
		notify();
	}
}
