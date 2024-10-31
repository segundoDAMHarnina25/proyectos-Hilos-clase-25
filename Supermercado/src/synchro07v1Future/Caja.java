package synchro07v1Future;

import java.time.Instant;
import java.util.concurrent.Callable;

//solo hay una caja
public class Caja implements Callable<Integer> {

	private String nombre;
	private Cola cola;

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

	public void procesarCompra(Cliente cliente, int timeStamp) {
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public Integer call() {
		int procesamiento=0;
		Cliente cliente = null;
		do {
			try {
				cliente = cola.get();
				if (cliente != null) {
					procesarCompra(cliente, 1);
					procesamiento++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while (!cola.isEmpty()||!cola.totalClientesAlcanzado());
		System.out.println("cajero "+nombre+" termina");
		return procesamiento;
	}

}