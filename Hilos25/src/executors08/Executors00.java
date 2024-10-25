package executors08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executors00 {
	public static void main(String[] args) {
		// tres objetos que iplmentan Runnable
		TareaImprimir tarea1 = new TareaImprimir("tarea1");
		TareaImprimir tarea2 = new TareaImprimir("tarea2");
		TareaImprimir tarea3 = new TareaImprimir("tarea3");
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.execute(tarea1);
		executorService.execute(tarea2);
		executorService.execute(tarea3);
		System.out.println("termina el main");
		executorService.shutdown();
		System.out.println("termina el executors");
	}
}
