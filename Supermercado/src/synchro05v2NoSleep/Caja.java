package synchro05v2NoSleep;

import java.util.Optional;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;
	private Cola cola;
	Utiles utiles = new Utiles();

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
		System.out.println(
				"El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre());

		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			utiles.processingTime(100);
			System.out.println("Procesado el producto " + (i + 1) + " ->Tiempo: ");
		}

		System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre());
	}

	@Override
	public void run() {
		Optional<Cliente> cliente = null;
		do {
			try {
				cliente = cola.get();
				if (cliente.isPresent()) {
					procesarCompra(cliente.get(), 1);
					System.out.println("cogiendo cliente " + getNombre());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!cola.isEmpty());
	}

}