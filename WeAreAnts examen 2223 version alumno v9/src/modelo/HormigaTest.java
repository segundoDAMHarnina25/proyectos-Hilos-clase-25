package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

class HormigaTest {

	@Test
	void testHormiga() throws InterruptedException, ExecutionException {
		Hormiguero hormiguero=new Hormiguero();
		String adnProgenitor = "AA";
		Hormiga hormiga=new Hormiga(1,adnProgenitor,hormiguero);
		ExecutorService executorService=Executors.newCachedThreadPool();
		Future<IdoneidadADN> adn = executorService.submit(hormiga);
		int extensionAdn = 3;
		assertEquals(adnProgenitor.length()+extensionAdn,adn.get().getAdn().length());
		executorService.shutdown();
		System.out.println();
	}
	@Test
	void testHormigas() throws Exception {
		Hormiguero hormiguero=new Hormiguero();
		String adnProgenitor = "AA";
		int cantidadHormigas=10;
		ArrayList<Future<IdoneidadADN>> lista=new ArrayList();
		ExecutorService executorService=Executors.newCachedThreadPool();
		for (int i = 0; i < cantidadHormigas; i++) {
			lista.add(executorService.submit(new Hormiga(i,adnProgenitor,hormiguero)));
			
		}
		int extensionAdn = 3;
		lista.forEach(future->{
				try {
					assertEquals(adnProgenitor.length()+extensionAdn,future.get().getAdn().length());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		});
		
		executorService.shutdown();
		System.out.println();
	}

}
