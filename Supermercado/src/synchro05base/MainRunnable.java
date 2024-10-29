package synchro05base;

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
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!cola.isBigger(10)) {
				cola.put(ClientesOM.getRandomClientNotOptional());
				System.out.println("size de la cola "+cola.size());
			}else {
				finalize=true;
			}
		} while (!finalize);
	}

	

}
