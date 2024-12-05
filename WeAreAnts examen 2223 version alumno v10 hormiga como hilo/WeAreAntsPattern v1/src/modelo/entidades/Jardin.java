package modelo.entidades;

import modelo.insectos.Bicho;
import modelo.insectos.Escarabajo;
import modelo.insectos.Grillo;
import modelo.insectos.Mariposa;

public class Jardin {
	public Bicho crearBicho(int bicho) {
		switch (bicho) {
		case 1:
			return new Mariposa();
		case 2:
			return new Grillo();
		default:
			return new Escarabajo();
		}
	}
}
