package future13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PruebaRecogedor {
public static void main(String[] args) {
	/*
	 * Este programa permite desligar el hilo main de otro hilo que recoge las
	 * respuestas de los Future
	 */
	TareaImprimir tarea1 = new TareaImprimir("tarea1");
	ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
	Future<String> submitResponse = ejecutorSubprocesos.submit(tarea1);
	Recogedor recogedor=new Recogedor(submitResponse);
	ejecutorSubprocesos.execute(recogedor);
	System.out.println("el main acaba");
}
}
