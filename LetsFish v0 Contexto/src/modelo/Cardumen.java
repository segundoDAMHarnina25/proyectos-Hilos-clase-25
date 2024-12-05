// Clase Cardumen
package modelo;

public class Cardumen {
	private final EspeciePez especie;
	private Coordenada posicion;
	private int peso;
	private double factorBiologico;
	private final int pesoInicial;
	private final double velocidadRegeneracion;
	private final int velocidadMovimiento;
	private boolean enPesca;

	public Cardumen(EspeciePez especie, Coordenada posicion, int peso, double velocidadRegeneracion) {
		this.especie = especie;
		this.posicion = posicion;
		this.peso = peso;
		this.pesoInicial = peso;
		this.velocidadRegeneracion = velocidadRegeneracion;
		this.factorBiologico = .25;
		this.velocidadMovimiento = 1;
	}

	////////////////Propios
	/**
	 * Se retira del cardumen una cantidad de 1
	 * cada vez que se llama a este metodo.
	 * @return una cantidad fija de 1 de peces en peso.
	 */
	public int pescar() {
		//TODO
		return -1;
	}
	
	/**
	 * El cardumen cambia de posicion a otra contigua
	 * en una distancia dependiente de su velocidad
	 * siempre mas lentos que los barcos  
	 */
	public void mover() {
		//TODO
	}

	/**
	 * El cardumen que no esta siendo pescado por ningun
	 * Pesquero se regenera, en cada llamada, en una cantidad
	 * fija determinada por la velocidad de regeneracion.
	 * Tras muchas llamadas a este metodo se restablece 
	 * a su peso inicial
	 */
	public void regenerar() {
		//TODO
	}

	////////////////////Getters
	public Coordenada getPosicion() {
		return posicion;
	}

	public boolean isSuitable(TipoBarco tipo) {
		return especie.isAdecuado(tipo);
	}

	public double getPeso() {
		return peso;
	}

	public double getPesoInicial() {
		return pesoInicial;
	}

	
	public boolean isLimiteAlcanzado() {
		return peso <= pesoInicial * factorBiologico;
	}

	public double getFactorBiologico() {
		return factorBiologico;
	}

	public boolean isEnPesca() {
		return enPesca;
	}

	public void setEnPesca(boolean enPesca) {
		this.enPesca = enPesca;
	}

	public EspeciePez getEspecie() {
		return especie;
	}

}
