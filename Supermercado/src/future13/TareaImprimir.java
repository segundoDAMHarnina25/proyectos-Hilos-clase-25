package future13;

import java.util.Random;
import java.util.concurrent.Callable;

public class TareaImprimir implements Callable<String> {
	private  int tiempoInactividad; // tiempo de inactividad aleatorio para el subproceso
	public void setTiempoInactividad(int tiempoInactividad) {
		this.tiempoInactividad = tiempoInactividad;
	}

	private final String nombreTarea; // nombre de la tarea
	private final static Random generador = new Random();

	public TareaImprimir(String nombre) {
		nombreTarea = nombre;
		tiempoInactividad = generador.nextInt(5000);
	}

	@Override
	public String call() throws Exception {
		try {
			System.out.printf("%s va a estar inactivo durante %d milisegundos.\n", nombreTarea, tiempoInactividad);
			Thread.sleep(tiempoInactividad);
		} catch (InterruptedException excepcion) {
			System.out.printf("%s %s\n", nombreTarea, "termino en forma prematura, debido a la interrupcion");
		}

		System.out.printf("%s termino su inactividad\n", nombreTarea);
		return nombreTarea;
	}
}