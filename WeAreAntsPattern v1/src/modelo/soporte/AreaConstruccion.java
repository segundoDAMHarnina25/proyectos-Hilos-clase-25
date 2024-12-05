package modelo.soporte;

import java.util.List;

import modelo.insectos.HormigaObrera;
import objectMother.ReparacionOM;
import utiles.Limpiador;

public class AreaConstruccion {
	private String nombre;
	List<HormigaObrera> constructoras;
	private boolean crecimientoAcabado = false;

	public AreaConstruccion(String nombre, List<HormigaObrera> hormigasObreras) {
		super();
		this.nombre = nombre;
		constructoras = hormigasObreras;
		constructoras.forEach((constructora)->{constructora.setAreaConstruccion(this);});
	}

	public void insertar(HormigaObrera hormigaObrera) {
		hormigaObrera.setAreaConstruccion(this);
		constructoras.add(hormigaObrera);
	}

	public boolean reparar() {
			boolean reparacionAcabada=false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Reparacion reparacion = ReparacionOM.getReparacion();
			if (reparacion != null) {
				do {
					new Limpiador<HormigaObrera>().enterrarHormigas(constructoras);
					reparacionAcabada=reparacion.reparando(constructoras);
				} while (reparacionAcabada);
			}
		return reparacionAcabada;
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
