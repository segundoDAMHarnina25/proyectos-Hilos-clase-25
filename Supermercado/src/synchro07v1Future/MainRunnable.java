package synchro07v1Future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MainRunnable implements Runnable {
	private Cola cola = new Cola();
	private Caja cajero1 = new Caja("Cajero 1", cola);
	private Caja cajero2 = new Caja("Cajero 2", cola);
	private List<Integer> clientesCajero = new ArrayList<Integer>();

	public MainRunnable(ExecutorService executorService) throws InterruptedException, ExecutionException {
		super();
		executorService.execute(this);
//		Future<Integer> submit = executorService.submit(cajero1);
//		Future<Integer> submit2 = executorService.submit(cajero2);
//		clientesCajero.add(submit.get());
//		clientesCajero.add(submit2.get());
		//En esta segunda version el submit.get hace que cajero2 espere a que acabe cajero1
		Future<Integer> submit = executorService.submit(cajero1);
		Future<Integer> submit2 = executorService.submit(cajero2);
		clientesCajero.add(submit.get());
		clientesCajero.add(submit2.get());
		executorService.shutdown();
		System.out.println("acaba el main");
		
	}

	@Override
	public void run() {
		do {
			if (!cola.totalClientesAlcanzado()) {
					cola.put(ClientesOM.getRandomClient());
			}
		} while (!cola.totalClientesAlcanzado());
		System.out.println("se acabo el run");
	}

	

	public int getTotalClientesCajero() {
		return clientesCajero.stream().mapToInt((valor) -> {
			return valor;
		}).sum();
	}

	public Cola getCola() {
		return cola;
	}

	public Caja getCajero1() {
		return cajero1;
	}

	public Caja getCajero2() {
		return cajero2;
	}

	public int getTotalClientes() {
		return cola.getTotalClientes();
	}

	public List<Integer> getClientesCajero() {
		return clientesCajero;
	}

}
