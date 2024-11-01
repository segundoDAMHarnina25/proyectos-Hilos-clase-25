package synchro06v7BlockinQueue;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

public class Cola {
	public LinkedBlockingQueue<Cliente> clientes = new LinkedBlockingQueue<Cliente>();
	boolean finalizado=false;
	
	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> {
				clientes.add(cliente);
		});
	}

	public boolean isBigger(int i) {
		return clientes.size() > 10;
	}

	public Optional<Cliente> get(){
		if(clientes.isEmpty()) return Optional.empty();
		return Optional.ofNullable(clientes.poll());
	}

	private void put(Optional<Cliente> optional) {
		push(optional);
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
