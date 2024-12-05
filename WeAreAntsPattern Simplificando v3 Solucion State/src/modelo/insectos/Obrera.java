package modelo.insectos;

import modelo.soporte.AreaConstruccion;

public class Obrera extends Comportamiento implements Vivible{


	AreaConstruccion areaConstruccion;

	Hormiga hormiga;

	public Obrera(AreaConstruccion areaConstruccion) {
		super();
		this.areaConstruccion = areaConstruccion;
	}

	public void repara() {
		areaConstruccion.toString();
		this.hormiga.incrementaEdad(hormiga.incrementoVidaPorDefecto);
	}

	@Override
	public void hacerTarea() {
		repara();
	}

	public void setAreaConstruccion(AreaConstruccion areaConstruccion) {
		this.areaConstruccion = areaConstruccion;
	}

	@Override
	public boolean isAlive() {
		return this.hormiga.isAlive();
	}

}
