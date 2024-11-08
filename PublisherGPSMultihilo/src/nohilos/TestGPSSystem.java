package nohilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class TestGPSSystem {

    public static void main(String[] args) throws InterruptedException {
        // Crear Publisher
        SubmissionPublisher<GeographicalPoint> publisher = new SubmissionPublisher<>();

        // Crear el servicio de ejecutores con tres hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Crear tres instancias de GPSSubscriber
        GPSSubscriber subscriber1 = new GPSSubscriber("Subscriber1",1);
        GPSSubscriber subscriber2 = new GPSSubscriber("Subscriber2",2);
        GPSSubscriber subscriber3 = new GPSSubscriber("Subscriber3",1);

        // Suscribir cada subscriber al publisher
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        // Publicar elementos en el publisher
        for (int i = 0; i < 10; i++) {
            GeographicalPoint point = new GeographicalPoint(i, i * 2,1);
            System.out.println("Publishing point: " + point);
            Thread.sleep(1000);
            publisher.submit(point);
        }

        // Cerrar el publisher después de que todos los datos hayan sido enviados
        publisher.close();

        // Esperar a que los suscriptores terminen de procesar los datos
        executor.awaitTermination(3, TimeUnit.SECONDS);
        executor.shutdown();
    }
}

