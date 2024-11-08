
package sihilos.reentrant;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import nohilos.GeographicalPoint;

// Clase Subscriber modificada para actuar como un hilo que espera datos del GPS
public class GPSSubscriber implements Subscriber<GeographicalPoint>, Runnable {
    private final String name;
    private Subscription subscription;
    private GeographicalPoint currentPoint;
    private final int requested; // Solicitud de elementos configurable
    private boolean working = true;

    /*
	 * ReentrantLock es una clase en Java que proporciona un mecanismo de bloqueo (lock) 
	 * avanzado para controlar el acceso de múltiples hilos a un recurso compartido.
	 *  A diferencia de synchronized, que también ofrece control de concurrencia, 
	 *  ReentrantLock proporciona más control y flexibilidad. Se encuentra en el paquete 
	 *  java.util.concurrent.locks y es especialmente útil en situaciones en las que se
	 *   necesita un control de bloqueo avanzado.
	 */
    private final Lock lock = new ReentrantLock();
    private final Condition newPointArrived = lock.newCondition();
    
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
    	lock.lock();
        currentPoint = item;
        System.out.println(name + " recibió posición: " + currentPoint);
        subscription.request(requested); 
        newPointArrived.signal();
        lock.unlock();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + " tuvo un error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
    	lock.lock();
        System.out.println(name + " ha completado la recepción de datos.");
        working = false;
        lock.unlock();
    }

    @Override
    public void run() {
        while (working) {
        	lock.lock();
        	try {
				newPointArrived.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if (currentPoint != null) {
                // Realiza alguna acción con la posición recibida
                System.out.println(name + " está procesando la posición: " + currentPoint);
                currentPoint = null; // Limpia el punto actual después de procesarlo
            }
            lock.unlock();
            System.out.println("sigo currando");
        }
        System.out.println("Termina " + name);
    }
}

