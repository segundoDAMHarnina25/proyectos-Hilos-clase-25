package synchro06v7BlockinQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;
	private Cola cola;
	private boolean finalizo = false;
	private List<Cliente> clientesAtendidos = new ArrayList();
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
		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			utiles.wasteTime(1000);
		}
	}

	@Override
	public void run() {
		do {
			Optional<Cliente> cliente = java.util.Optional.empty();
			cliente = cola.get();
			cliente.ifPresent((cli) -> {
				clientesAtendidos.add(cli);
				procesarCompra(cli, 1);
			});
		} while (!cola.colaAcabada());
		System.out.println("mi jornada ha acabado " + getNombre() + " " + clientesAtendidos.size());
	}

	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

}