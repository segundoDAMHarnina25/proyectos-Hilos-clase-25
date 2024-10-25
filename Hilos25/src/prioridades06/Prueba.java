package prioridades06;
import java.time.Instant;

public class Prueba {
	public static void main(String[] args) {
		// La prioridad no se nota mucho con elementos de poca duraci�n
		// al subir a 100 el iterador en el hilo el proceso se hace m�s pesado y
		// necesita m�s
		// tiempo de procesador
		Element uno = new Element("uno", Instant.now());
		uno.setPriority(Thread.MIN_PRIORITY);
		uno.start();
		//Tener mayor prioridad no asegura nada con pocos hilos
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Element dos = new Element("dos", Instant.now());
		dos.setPriority(Thread.MAX_PRIORITY);
		dos.start();
		
	}
}
