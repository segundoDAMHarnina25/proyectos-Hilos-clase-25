
package sihilos.lock;

import nohilos.GeographicalPoint;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

// Clase Subscriber modificada para actuar como un hilo que espera datos del GPS
public class GPSSubscriber implements Subscriber<GeographicalPoint>, Runnable {
	private final String name;
	private Subscription subscription;
	private GeographicalPoint currentPoint;
	private final int requested; // Solicitud de elementos configurable
	private boolean working = true;
	// locking object
	private final Object lock = new Object();

	public GPSSubscriber(String name, int requestAmount) {
		this.name = name;
		this.requested = requestAmount;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println(name + " solicitando " + requested + " elementos.");
		this.subscription.request(requested); // Solicita el primer lote de elementos
	}

	@Override
	public void onNext(GeographicalPoint item) {
		currentPoint = item;
		System.out.println(name + " recibió posición: " + currentPoint);
		subscription.request(requested);
		synchronized (lock) {
			lock.notify();
		}
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(name + " tuvo un error: " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println(name + " ha completado la recepción de datos.");
		working = false;
	}

	@Override
	public void run() {
		while (working) {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (currentPoint != null) {
					// Realiza alguna acción con la posición recibida
					System.out.println(name + " está procesando la posición: " + currentPoint);
					currentPoint = null; // Limpia el punto actual después de procesarlo
				}
//            try {
//                Thread.sleep(500); // Simula espera o procesamiento
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
			}
			System.out.println("sigo currando "+name);
		}
		System.out.println("Termina " + name);
	}
}
