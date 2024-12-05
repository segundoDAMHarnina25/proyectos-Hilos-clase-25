package objectMother;

import java.util.Random;

import modelo.soporte.Reparacion;

public class ReparacionOM {
	private static final int maximo = 500;
	private static int id = 1;

	public static Reparacion getReparacion() {
		if(new Random().nextInt(3)!=0) return new Reparacion(id++, new Random().nextInt(maximo));
		return null;
	}
}
