package modelo.insectos;

import java.util.ArrayList;
import java.util.List;

import modelo.soporte.Alimento;

public class Recolectora extends Comportamiento {

	List<Alimento> alimentos;
	
	public Recolectora() {
		super();
		alimentos = new ArrayList();
	}

	@Override
	public void hacerTarea() {
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			alimentos.add(Alimento.getRandomAlimento());
			this.hormiga.incrementaEdad(this.hormiga.incrementoVidaPorDefecto);
		} while (this.hormiga.isAlive());
	}

}
