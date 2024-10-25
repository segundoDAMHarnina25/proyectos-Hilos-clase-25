package joining09;

public class Joning {

	public static void main(String[] args) {
		Thread hiloUnoIndependiente = new Thread() {
			@Override
			public void run() {
				int max = 10;
				do {
					System.out.println("el uno");
					try {
						this.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (--max > 0);
			}

		};
		Thread hiloDosDependiente = new Thread() {
			@Override
			public void run() {
				int max = 10;
				System.out.println("Soy el hilo dependiente, pero todavia no");
				try {
					hiloUnoIndependiente.join();
					System.out.println("Soy el hilo dependiente, ahora si");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				do {
					System.out.println("el dos");
					try {
						this.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (--max > 0);
			}

		};
		hiloDosDependiente.start();
		hiloUnoIndependiente.start();
	}
}
