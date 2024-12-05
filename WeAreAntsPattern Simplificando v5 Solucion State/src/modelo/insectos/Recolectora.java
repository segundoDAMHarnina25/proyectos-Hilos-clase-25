package modelo.insectos;

import java.util.ArrayList;
import java.util.List;

import modelo.soporte.Alimento;

public class Recolectora extends Comportamiento {


	
	public Recolectora(Hormiga hormiga) {
		super();
		this.hormiga=hormiga;
	}

	@Override
	public void hacerTarea() {
		if(this.hormiga.isAlive()){
		try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.hormiga.addAlimento(Alimento.getRandomAlimento());
			this.hormiga.incrementaEdad(this.hormiga.incrementoVidaPorDefecto);
		}
	}

}
