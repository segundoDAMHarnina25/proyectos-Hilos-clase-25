package synchro07v1Future;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	private int totalClientes = 0;

	private boolean push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> {
			clientes.add(cliente);
			totalClientes++;
		});
		return randomClient.isPresent();
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}

	public synchronized Cliente get() throws InterruptedException {
		while (clientes.isEmpty() && !totalClientesAlcanzado()) {
			wait();
		}
		if (!clientes.isEmpty()) {
			Cliente poll = clientes.poll();
			return poll;
		}
		return null;
	}

	public synchronized void put(Optional<Cliente> optional) {
		if (push(optional)) {
			notify();
		}
		if(totalClientesAlcanzado()) notify();
	}

	public boolean totalClientesAlcanzado() {
		return totalClientes > 2000;
	}

	public int size() {
		return clientes.size();
	}

	public boolean isEmpty() {
		return clientes.isEmpty();
	}

	public int getTotalClientes() {
		return totalClientes;
	}
}
