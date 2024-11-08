package sihilos.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import nohilos.GeographicalPoint;

public class TestGPSSystem {

    public static void main(String[] args) throws InterruptedException {
        // Crear Publisher
    	System.out.println("version 3");
        SubmissionPublisher<GeographicalPoint> publisher = new SubmissionPublisher<>();

        // Crear el servicio de ejecutores para los tres subscribers
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Crear tres instancias de GPSSubscriber con diferentes solicitudes de elementos
        GPSSubscriber subscriber1 = new GPSSubscriber("Subscriber1", 1);
        GPSSubscriber subscriber2 = new GPSSubscriber("Subscriber2", 2);
        GPSSubscriber subscriber3 = new GPSSubscriber("Subscriber3", 3);

        // Suscribir cada subscriber al publisher
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        // Ejecutar cada suscriptor en un hilo separado
        executor.submit(subscriber1);
        executor.submit(subscriber2);
        executor.submit(subscriber3);

        // Publicar más elementos en el publisher con una pausa más larga entre cada uno
        for (int i = 0; i < 5; i++) {
            GeographicalPoint point = new GeographicalPoint(i, i * 2, 1);
            System.out.println("Publishing point: " + point);
            publisher.submit(point);
            Thread.sleep(1500); // Pausa de 1.5 segundos entre publicaciones
        }

        // Cerrar el publisher después de que todos los datos hayan sido enviados
        publisher.close();

        // Dar tiempo a los suscriptores para procesar todos los datos antes de finalizar
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }
}

