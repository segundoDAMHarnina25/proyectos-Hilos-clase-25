package modelo.soporte;

import java.util.List;

import modelo.insectos.Hormiga;
import utiles.Limpiador;

public class ZonaDefensa {
	private String nombre;
	private List<Hormiga> defensoras;
	private boolean finalGuardia;

	public ZonaDefensa(String name, List<Hormiga> hormigasGuerrera) {
		super();
		this.nombre = name;
		defensoras = hormigasGuerrera;
	}

	public void insertar(List<Hormiga> hormigasGuerreras) {
		defensoras.addAll(hormigasGuerreras);
	}

	public void recorrer() {
		finalGuardia=false;
		defensoras.forEach((defensora) -> {
			defensora.hacerTarea();
		});
		new Limpiador<Hormiga>().enterrarHormigas(defensoras);
		if(defensoras.isEmpty()) finalGuardia=true;
	}

	public boolean isFinalGuardia() {
		return finalGuardia;
	}

	protected void setFinalGuardia(boolean finalGuardia) {
		this.finalGuardia = finalGuardia;
	}
}
