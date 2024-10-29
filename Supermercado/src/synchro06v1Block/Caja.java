package synchro06v1Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;
	private Cola cola;
	private boolean finalizo = false;
	private List<Cliente> clientesAtendidos = new ArrayList();
	Utiles utiles=new Utiles();

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
			utiles.wasteTime(1000 );
		}
	}

	@Override
	public void run() {
		boolean empty = true;
		do {
			synchronized (cola) {
				empty = cola.clientes.isEmpty();
				while (empty&&!finalizo) { // Espera mientras la cola esté vacía
					try {
						cola.wait(); // Espera hasta que se notifique un cambio en cola
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						System.out.println("Caja interrumpida");
						return;
					}
					try {
						Optional<Cliente> cliente = cola.get();
						cliente.ifPresent((cli) -> {
							clientesAtendidos.add(cli);
							procesarCompra(cli, 1);
//							System.out.println("cliente procesado "+getNombre()+"+"+cli);
							cola.turno();
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} while (!cola.colaAcabada());
		System.out.println("mi jornada ha acabado " + getNombre() + " " + clientesAtendidos.size());
	}

	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

}