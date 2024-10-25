package presentacion;

import synchro01.Cola;

public class ClientProvider implements Runnable {
	private Cola cola;
	
	
	public ClientProvider(Cola cola) {
		super();
		this.cola = cola;
	}


	@Override
	public void run() {
		do {
			cola.push(ClientesOM.getRandomClient());
			try {
				int max = 1000;
				Thread.currentThread().sleep(ClientesOM.getRandomValue(max));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(true);
		
	}

}
