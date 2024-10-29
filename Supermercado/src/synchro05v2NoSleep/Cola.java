package synchro05v2NoSleep;

import java.util.ArrayDeque;
import java.util.Optional;


public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	
	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> {
			clientes.push(cliente);
			});
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}
	
	
	public synchronized Optional<Cliente> get() throws InterruptedException {
		while (clientes.isEmpty()) {
			wait();
		}
		return Optional.of(clientes.pop()); 
	}

	public synchronized void put(Optional<Cliente> optional) {
		if(optional.isEmpty()) System.err.println("vacio");
		push(optional);
		notify();
	}

	public  boolean isEmpty() {
		return clientes.isEmpty();
	}

	public int size() {
		return clientes.size();
	}
}
