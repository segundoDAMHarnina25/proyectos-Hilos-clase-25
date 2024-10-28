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
			cola.put(ClientesOM.getRandomClient());
			try {
				int max = 1000;
				Thread.sleep(ClientesOM.getRandomValue(max));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(true);
		
	}

}
