package synchro05v2NoSleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunnable implements Runnable {
	Cola cola = new Cola();
	Caja cajero1 = new Caja("Cajero 1", cola);
	Caja cajero2 = new Caja("Cajero 2", cola);

	public static void main(String[] args) {
		MainRunnable superMercado = new MainRunnable();
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
		boolean finalize=false;
		Utiles utiles=new Utiles();
		do {
			utiles.processingTime(100);
			if (!cola.isBigger(10)) {
				cola.put(ClientesOM.getRandomClientNotOptional());
			}else {
				finalize=true;
			}
		} while (!finalize);
	}

	

}
