package modelo.soporte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.insectos.HormigaGuerrera;
import utiles.Limpiador;

public class ZonaDefensa {
	private String nombre;
	private List<HormigaGuerrera> defensoras;
	private boolean finalGuardia;

	public ZonaDefensa(String name, List<HormigaGuerrera> hormigasGuerrera) {
		super();
		this.nombre = name;
		defensoras = hormigasGuerrera;
	}

	public void insertar(List<HormigaGuerrera> hormigasGuerreras) {
		defensoras.addAll(hormigasGuerreras);
	}

	public void recorrer() {
		finalGuardia=false;
		defensoras.forEach((defensora) -> {
			defensora.hacerTarea();
		});
		new Limpiador<HormigaGuerrera>().enterrarHormigas(defensoras);
		if(defensoras.isEmpty()) finalGuardia=true;
	}

	public boolean isFinalGuardia() {
		return finalGuardia;
	}

	protected void setFinalGuardia(boolean finalGuardia) {
		this.finalGuardia = finalGuardia;
	}
}
