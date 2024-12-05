// Clase abstracta Barco
package modelo;

import java.util.List;

public class Barco implements Pesquero {
	protected String nombre;
	public Coordenada posicionActual;
	protected Puerto puertoBase;
	protected int capacidad;
	private int pescaActual;
	protected Cardumen objetivo;
	protected int velocidad;
	private TipoBarco tipo;

	public Barco(String nombre, Coordenada posicionActual, Puerto puertoBase, int capacidad, int velocidad,
			TipoBarco tipoBarco) {
		super();
		this.nombre = nombre;
		this.puertoBase = puertoBase;
		this.capacidad = capacidad;
		this.posicionActual = posicionActual;
		this.velocidad = velocidad;
		this.tipo = tipoBarco;
		this.pescaActual = 0;
	}

	///////////////Propios
	@Override
	public void decidirCardumen(List<Cardumen> cardumenes) {
		// TODO
	}

	@Override
	public void regresarAPuerto() {
		// TODO
	}

	@Override
	public void mover() {
		// TODO
	}

	@Override
	public void pescar() {
		// TODO
	}

	/////////////////Getters
	public int getCapacidad() {
		return capacidad;
	}

	public int getPescaActual() {
		return pescaActual;
	}

	public Cardumen getObjetivo() {
		return objetivo;
	}

	public TipoBarco getTipo() {
		return tipo;
	}

	private boolean isSuitableBoat(Cardumen cardumen) {
		return cardumen.isSuitable(getTipo());
	}

}