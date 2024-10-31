package synchro07v1Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

class MainRunnableTest {

	@Test
	void test()  {
		MainRunnable superMercado;
		try {
			// primero probamos sin arrancar el hilo del super
			ExecutorService executorService = Executors.newCachedThreadPool();
			superMercado = new MainRunnable(executorService);
			assertEquals(superMercado.getTotalClientes(), superMercado.getTotalClientesCajero());
			assertEquals(superMercado.getClientesCajero().get(0),superMercado.getClientesCajero().get(1),20);
//			System.out.println("The zero \n"+superMercado.getClientesCajero().get(0));
//			System.out.println(superMercado.getClientesCajero().get(1));
//			assertNotEquals(superMercado.getClientesCajero().get(0), 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
