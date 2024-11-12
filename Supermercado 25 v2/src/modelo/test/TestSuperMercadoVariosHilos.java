package modelo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import modelo.data.Cajere;
import modelo.data.Cart;
import modelo.data.Cliente;
import modelo.repos.ArticulosEnStrockRepositorio;

class TestSuperMercadoVariosHilos {

	@Test
	void test() {
		ArticulosEnStrockRepositorio articulosEnStrockRepositorio = null;
		try {
			articulosEnStrockRepositorio = new ArticulosEnStrockRepositorio();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		int j = 10;
		ArrayBlockingQueue<Cliente> clientes = new ArrayBlockingQueue<Cliente>(j);
		ExecutorService executorService = Executors.newCachedThreadPool();
		ArrayList<Cliente> clientesThread = new ArrayList();
		for (int i = 0; i < j; i++) {
			clientesThread.add(new Cliente("no" + i, articulosEnStrockRepositorio, clientes));
		}
		Cajere caja = new Cajere("1", 1, 10f, clientes, articulosEnStrockRepositorio);
		executorService.execute(caja);
		List<Future> collect = clientesThread.stream().map(cliente -> {
			return executorService.submit(cliente);
		}).collect(Collectors.toList());
		executorService.shutdown();
	}

}
