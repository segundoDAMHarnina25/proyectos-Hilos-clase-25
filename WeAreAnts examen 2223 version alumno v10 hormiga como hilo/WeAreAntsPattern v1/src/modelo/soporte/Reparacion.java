package modelo.soporte;

import java.util.List;

import modelo.insectos.HormigaObrera;

public class Reparacion {
	private int id;
	private int estimacion;

	public Reparacion(int id, int estimacion) {
		super();
		this.id = id;
		this.estimacion = estimacion;
	}

	public boolean reparando(List<HormigaObrera> hormigas) {
		hormigas.forEach((hormiga) -> {
			if (estimacion > 0) {
				estimacion--;
				if (hormiga.isAlive()) {
					hormiga.repara();
				}
			}
		});
		return estimacion > 0;
	}
}
