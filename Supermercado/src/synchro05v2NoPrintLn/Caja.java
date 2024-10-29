package synchro05v2NoPrintLn;

import java.util.Optional;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;
	private Cola cola;
	Utiles utiles = new Utiles();
	int clientesProcesados=0;

	public Caja(String nombre, Cola cola) {
		this.nombre = nombre;
		this.cola = cola;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void procesarCompra(Cliente cliente, long timeStamp) {
		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			utiles.processingTime(100000);
		}
		clientesProcesados++;
	}

	@Override
	public void run() {
		Optional<Cliente> cliente = null;
		do {
			try {
				cliente = cola.get();
				if (cliente.isPresent()) {
					procesarCompra(cliente.get(), 1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!cola.isCerrada()||!cola.isEmpty());
		cola.freeCola();
		System.out.println("clientes procesados "+clientesProcesados+" cajero "+getNombre());
	}

}