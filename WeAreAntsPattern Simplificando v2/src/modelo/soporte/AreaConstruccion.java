package modelo.soporte;

import java.util.List;
import java.util.Random;

import modelo.insectos.HormigaObrera;
import utiles.Limpiador;

public class AreaConstruccion {
	private String nombre;
	List<HormigaObrera> constructoras;
	private boolean crecimientoAcabado = false;

	public AreaConstruccion(String nombre, List<HormigaObrera> hormigasObreras) {
		super();
		this.nombre = nombre;
		constructoras = hormigasObreras;
		constructoras.forEach((constructora) -> {
			constructora.setAreaConstruccion(this);
		});
	}

	public void insertar(HormigaObrera hormigaObrera) {
		hormigaObrera.setAreaConstruccion(this);
		constructoras.add(hormigaObrera);
	}

	public int reparar(int pendiente) {
		if (pendiente == 0) {
			pendiente = new Random().nextInt(1000);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		do {
			for (HormigaObrera hormiga : constructoras) {
				hormiga.repara();
				pendiente--;
			}
			new Limpiador<HormigaObrera>().enterrarHormigas(constructoras);
		} while (pendiente != 0 && !constructoras.isEmpty());
		return pendiente;

	}

	@Override
	public String toString() {
		return nombre;
	}

	private boolean isCrecimientoAcabado() {
		return crecimientoAcabado;
	}

	private void setCrecimientoAcabado(boolean crecimientoAcabado) {
		this.crecimientoAcabado = crecimientoAcabado;
	}
}
