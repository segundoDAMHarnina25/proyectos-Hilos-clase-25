package modelo.insectos;

import java.util.ArrayList;
import java.util.List;

import modelo.soporte.Alimento;

public class HormigaRecolectora extends Hormiga {

	List<Alimento> alimentos;

	public HormigaRecolectora(long id) {
		super(id);
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
			incrementaEdad(incrementoVidaPorDefecto);
		} while (isAlive());
	}

}
