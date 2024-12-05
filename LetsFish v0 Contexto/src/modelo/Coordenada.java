package modelo;

import java.util.*;

// Coordenada
public class Coordenada {
	private int x, y;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordenada(Coordenada posicion) {
		this.x = posicion.x;
		this.y = posicion.y;
	}

	public double distanciaA(Coordenada otra) {
		return Math.sqrt(Math.pow(otra.x - this.x, 2) + Math.pow(otra.y - this.y, 2));
	}

	/**
	 * colocamos la coordenada this en una posicion contigua determinada por la
	 * velocidad. Si la diferencia entre la distancia de this y la de destino es
	 * menor a la velocidad, se igualan las coordenadas, es decir this y destino
	 * tienen el mismo valor
	 * 
	 * @param destino
	 * @param velocidad
	 */
	public void avanzarHacia(Coordenada destino, int velocidad) {
		double distancia = distanciaA(destino);
		if (distancia > velocidad) {
			double factor = velocidad / distancia;
			double d = (destino.x - x) * factor;
			double e = (destino.y - y) * factor;
			this.setDelta(d, e);
		} else {
			this.x = destino.x;
			this.y = destino.y;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordenada))
			return false;
		Coordenada otra = (Coordenada) obj;
		return Double.compare(x, otra.x) == 0 && Double.compare(y, otra.y) == 0;
	}

	private void setDelta(double d, double e) {
		this.x += d;
		this.y += e;
	}

	/**
	 * Se encarga de establecer un nuevo valor de this basandose en la velocidad de
	 * movimiento y las dimensiones limite de ancho y alto Este nuevo valor this, se
	 * basa en el anterior y se le pone un desplazamiento basado en velocidad
	 * 
	 * @param velocidadMovimiento cuanto se desplaza
	 * @param ancho               ancho max
	 * @param alto                alto max
	 */
	public void setRandomDelta(int velocidadMovimiento, int ancho, int alto) {
		int horizontal = 0, vertical = 0;
		Random random = new Random();
		do {
			boolean horizontalFlag = random.nextBoolean();
			boolean verticalFlag = true;
			int i = random.nextBoolean() ? 1 : -1;
			if (horizontalFlag) {
				horizontal = velocidadMovimiento * i;
				verticalFlag = random.nextBoolean();
			}
			if (verticalFlag)
				vertical = velocidadMovimiento * i;

		} while (!isInToLimits(ancho, alto, this.x + horizontal, this.y + vertical));
		setDelta(horizontal, vertical);
	}

	private boolean isInToLimits(double ancho, double alto, double x2, double y2) {
		return x2 >= 0 && x2 < ancho && y2 >= 0 && y2 < alto;
	}

	@Override
	public String toString() {
		return "posicion X:" + x + " posicionY:" + y;
	}
}
