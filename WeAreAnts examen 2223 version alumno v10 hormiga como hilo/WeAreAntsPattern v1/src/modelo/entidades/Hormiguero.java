package modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import modelo.insectos.Hormiga;
import modelo.insectos.HormigaGuerrera;
import modelo.insectos.HormigaObrera;
import modelo.insectos.HormigaRecolectora;
import modelo.soporte.AreaConstruccion;
import modelo.soporte.ZonaDefensa;
import utiles.Limpiador;

public class Hormiguero {
	private AreaConstruccion global;
	private ZonaDefensa territorio;
	private int cantidadHormigasPorSector = 100;
	private List<HormigaObrera> hormigasObreras;
	private List<HormigaGuerrera> hormigasGuerreras;
	private List<HormigaRecolectora> hormigaRecolectoras;
	private static long id = 1;
	private boolean tareaAcabada = false;

	public Hormiguero() {
		super();
		hormigaRecolectoras = new ArrayList<HormigaRecolectora>();
		hormigasGuerreras = new ArrayList<HormigaGuerrera>();
		hormigasObreras = new ArrayList<HormigaObrera>();
		createGroup(hormigaRecolectoras, HormigaRecolectora.class);
		createGroup(hormigasGuerreras, HormigaGuerrera.class);
		territorio = new ZonaDefensa("territorio", hormigasGuerreras);
		createGroup(hormigasObreras, HormigaObrera.class);
		global = new AreaConstruccion("global", hormigasObreras);
	}

	public void funciona() {
		int cantidad = 0;
		do {
			tareaRecoleccion();
			tareaDefensa();
			tareaConstruccion();
			if (cantidad++ == 10)
				setTareaAcabada(true);
		} while (isTareaAcabada());
	}

	private void tareaConstruccion() {
		do {
			boolean reparar = global.reparar();
			if(!reparar) {
				for (int i = hormigasObreras.size(); i <= cantidadHormigasPorSector; i++) {
					global.insertar(new HormigaObrera(id++));
				}
			}
		} while (!isTareaAcabada());

	}

	private void tareaDefensa() {
		do {
			do {
				territorio.recorrer();
			} while (territorio.isFinalGuardia());
			createGroup(hormigasGuerreras, HormigaGuerrera.class);
			territorio.insertar(hormigasGuerreras);
		} while (!isTareaAcabada());
	}

	private void tareaRecoleccion() {
		hormigaRecolectoras.forEach((hormiga) -> {
			hormiga.hacerTarea();
		});
		int muertas = new Limpiador().enterrarHormigas(hormigaRecolectoras);
		// hacer algo con las hormigas muertas, complicado
		for (int i = 0; i < muertas; i++) {
			hormigaRecolectoras.add(new HormigaRecolectora(id++));
		}
//		List enterrarHormigas = new Limpiador().enterrarHormigas(hormigaRecolectoras);
//		//hacer algo con las hormigas muertas, complicado
//		for (int i = 0; i < enterrarHormigas.size(); i++) {
//			hormigaRecolectoras.add(new HormigaRecolectora())
//		}
	}

	private void createGroup(List hormigas, Class tipo) {
		for (int i = 0; i < cantidadHormigasPorSector; i++) {
			Hormiga hormiga;
			if (tipo == HormigaObrera.class) {
				hormiga = new HormigaObrera((id++));
			} else if (tipo == HormigaGuerrera.class) {
				hormiga = new HormigaGuerrera(id++);
			} else {
				hormiga = new HormigaRecolectora(id++);
			}
			hormigas.add(hormiga);
		}
	}

	private boolean isTareaAcabada() {
		return tareaAcabada;
	}

	private void setTareaAcabada(boolean tareaAcabada) {
		this.tareaAcabada = tareaAcabada;
	}

}
