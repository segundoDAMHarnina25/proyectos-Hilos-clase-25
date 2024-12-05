package modelo.insectos;

import java.util.ArrayList;
import java.util.List;

import modelo.soporte.Alimento;

public class Recolectora extends Comportamiento {

	List<Alimento> alimentos;
	
	public Recolectora(Hormiga hormiga) {
		super();
		alimentos = new ArrayList();
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
			alimentos.add(Alimento.getRandomAlimento());
			this.hormiga.incrementaEdad(this.hormiga.incrementoVidaPorDefecto);
		}
	}

}
