package modelo.insectos;

import modelo.soporte.AreaConstruccion;

public class HormigaObrera extends Hormiga{
	AreaConstruccion areaConstruccion;

	public HormigaObrera(long id) {
		super(id);
	}

	public void repara() {
		areaConstruccion.toString();
		incrementaEdad(incrementoVidaPorDefecto);
	}

	@Override
	public void hacerTarea() {
		repara();
	}

	public void setAreaConstruccion(AreaConstruccion areaConstruccion) {
		this.areaConstruccion = areaConstruccion;
	}
}
