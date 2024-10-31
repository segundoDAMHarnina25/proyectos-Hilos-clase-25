package future13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Recogedor implements Runnable {
	Future nose;

	public Recogedor(Future nose) {
		super();
		this.nose = nose;
	}

	@Override
	public void run() {
		System.out.println("recogedor de fruto ha comenzado");
		do {
		} while (!nose.isDone());
		System.out.println("Pues ya me han contestado");
		try {
			System.out.println("venia de este hilo "+nose.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
