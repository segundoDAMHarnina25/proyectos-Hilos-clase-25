package synchro06v1Block;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunnable implements Runnable {
	Cola cola = new Cola();
	Caja cajero1 = new Caja("Cajero 1", cola);
	Caja cajero2 = new Caja("Cajero 2", cola);
	int contadorClientes = 0;
	int maxClientes = 10;
	Utiles utiles = new Utiles();

	public static void main(String[] args) {
		MainRunnable superMercado = new MainRunnable();
		// primero probamos sin arrancar el hilo del super
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(superMercado);
		executorService.shutdown();
	}

	public MainRunnable() {
		super();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(cajero1);
		executorService.execute(cajero2);
		executorService.shutdown();
	}

	@Override
	public void run() {
		do {
			synchronized (cola) {
				if (!cola.isFinalizado()) {
					if (!cola.isBigger(10) && contadorClientes < maxClientes) {
						cola.push(ClientesOM.getRandomClientNotOptional());
						contadorClientes++;
						cola.turno();
					} else {
						cola.setFinalizado(true);
					}
				}
			}
			utiles.wasteTime(15);			
		} while (!cola.colaAcabada());
		synchronized (cola) {
			cola.turno();
			System.out.println("main acabado");
			cajero1.setFinalizo(true);
			cajero2.setFinalizo(true);
		}
	}

}
