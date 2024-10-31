package future13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjecutorTareas00 {
	public static void main(String[] args) {
		// crea y nombra cada objeto Runnable
		//Comparar con Ejecutor00 de teoriahilosv11
		TareaImprimir tarea1 = new TareaImprimir("tarea1");
		TareaImprimir tarea2 = new TareaImprimir("tarea2");
		TareaImprimir tarea3 = new TareaImprimir("tarea3");
		System.out.println("Iniciando Executor");
		// crea objeto ExecutorService para administrar los subprocesos
		ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
		ejecutorSubprocesos.execute(new Runnable() {
			public void run() {
				System.out.println();
			}
		});
		//haciendolo más corto para saber si termina main cuando termina este
		tarea1.setTiempoInactividad(100);
		Future<String> submitResponse = ejecutorSubprocesos.submit(tarea1);
		ejecutorSubprocesos.submit(tarea2);
		ejecutorSubprocesos.submit(tarea3);
		ejecutorSubprocesos.shutdown();
		try {
			//esperando la respuesta del Callable cuando este termina, termina main
			System.out.println("submit response "+submitResponse.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tareas iniciadas, main termina.\n");
	}
}
