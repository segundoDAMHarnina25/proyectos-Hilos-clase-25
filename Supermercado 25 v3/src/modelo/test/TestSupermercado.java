package modelo.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import modelo.data.Cajere;
import modelo.data.Cart;
import modelo.data.Cliente;
import modelo.repos.ArticulosEnStrockRepositorio;

class TestSupermercado {

	@Ignore
	void testCompra() {
		ArticulosEnStrockRepositorio articulosEnStrockRepositorio = null;
		try {
			articulosEnStrockRepositorio = new ArticulosEnStrockRepositorio();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		ArrayBlockingQueue<Cliente> clientes = new ArrayBlockingQueue<Cliente>(10);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Cliente cliente = new Cliente("no", articulosEnStrockRepositorio, clientes);
		Cajere caja = new Cajere("1", 1, 10f, clientes, articulosEnStrockRepositorio);
		executorService.execute(caja);
		Future<Cart> submit = executorService.submit(cliente);
		executorService.shutdown();
		try {
			assertEquals(submit.get().getTotal(), articulosEnStrockRepositorio.getTotalVendido());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test() {
		ArticulosEnStrockRepositorio articulosEnStrockRepositorio = null;
		try {
			articulosEnStrockRepositorio = new ArticulosEnStrockRepositorio();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		ArrayBlockingQueue<Cliente> clientes = new ArrayBlockingQueue<Cliente>(10);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Cliente cliente = new Cliente("no", articulosEnStrockRepositorio, clientes);

		Cajere caja = new Cajere("1", 1, 10f, clientes, articulosEnStrockRepositorio);
		executorService.execute(caja);
		Future<Cart> submit = executorService.submit(cliente);
		executorService.shutdown();
		try {
			System.out.println(submit.get().toString());
			assertEquals(submit.get().getTotal(), articulosEnStrockRepositorio.getTotalVendido());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
