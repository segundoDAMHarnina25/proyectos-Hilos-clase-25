package comienzo01;

public class Comienzo5 {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("Pa habernos matao");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread vamos = new Thread(task);
		vamos.start();
		System.out.println("antes de la espera");
		vamos.sleep(1000);
		System.out.println("despues de la espera");
//		vamos.stop();
//		vamos.suspend();
//		Thread.sleep(1000);
//		vamos.resume();
//		vamos.destroy();
		
	}

}
