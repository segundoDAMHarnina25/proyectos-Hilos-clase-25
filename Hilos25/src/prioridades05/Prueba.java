package prioridades05;
import java.time.Instant;

public class Prueba {
	public static void main(String[] args) {
		// La prioridad no se nota mucho con elementos de poca duraci�n
		// al subir a 100 el iterador en el hilo el proceso se hace m�s pesado y
		// necesita m�s
		// tiempo de procesador
		Element uno = new Element("uno", Instant.now());
		Element dos = new Element("dos", Instant.now());
		uno.setPriority(Thread.MIN_PRIORITY);
		dos.setPriority(Thread.NORM_PRIORITY);
		uno.start();
		dos.start();		
		int size = 100;
		Element random;
		for (int i = 0; i < size; i++) {
			random = new Element("tres"+i, Instant.now());
			random.setPriority(Thread.MAX_PRIORITY);
			random.start();
		}
//		System.out.println("yielding");
//		dos.yield();
	}
}
