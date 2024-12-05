package modelo.soporte;

import java.util.Random;

public enum Alimento {
	hoja, pata, pan, azucar, plastico;

	public static Alimento getRandomAlimento() {
		return Alimento.values()[new Random().nextInt(Alimento.values().length)];
	}
}
