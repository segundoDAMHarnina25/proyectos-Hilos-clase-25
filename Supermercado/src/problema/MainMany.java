package problema;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import presentacion.Caja;
import presentacion.ClientProvider;
import synchro01.Cola;

public class MainMany {
	public static void main(String[] args) {
		Cola cola=new Cola();
		ClientProvider clientProvider=new ClientProvider(cola);
		Caja cajero1 = new Caja("Cajero 1",cola);
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.execute(cajero1);
		executorService.execute(clientProvider);
		executorService.shutdown();
	}

}
