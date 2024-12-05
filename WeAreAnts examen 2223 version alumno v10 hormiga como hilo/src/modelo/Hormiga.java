package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import objectsmothers.AdnOM;
import objectsmothers.AlimentoOM;

public class Hormiga implements Callable<IdoneidadADN>{
	private int vidaMax = 100, vidaMin = 80;
	public int vida = getVidaAleatoria(vidaMin, vidaMax);
	public int edad = 0;
	private String adn;
	public static final int cantidadPoderNacimiento = 1000;
	private List<Alimento> recogidos;
	private Hormiguero hormiguero;
	public int id;
	public int cantidadAlimentos=0;
	public int cantidadRecogidas=0;

	public Hormiga(int id, String adnProgenitor, Hormiguero hormiguero) {
		super();
		this.adn = AdnOM.getNuevoAdn(adnProgenitor);
		this.hormiguero = hormiguero;
		recogidos = new ArrayList<Alimento>();
		this.id = id;
	}

	@Override
	public IdoneidadADN call() throws Exception {
		return hacerTarea();
	}

	public IdoneidadADN hacerTarea() {
		while (isAlive()) {
			Alimento alimento = recoger();
			if (alimento != null) {
				acarrear(alimento);
				cantidadAlimentos++;
				recogidos.add(alimento);
			}
			edad++;
			cantidadRecogidas++;
		}
		return new IdoneidadADN(adn, getIndiceSalubridadPoderMedio());

	}

	/**
	 * Compara si el alimento encontrado merece la pena ser recogido o no
	 * 
	 * @return
	 */
	private Alimento recoger() {
		Alimento encontrado = AlimentoOM.getAlimentoOM();
		float indiceSaludYPoderActual = getIndiceSalubridadPoderMedio();
		if (encontrado.getIndiceSalubridadPoder() >= indiceSaludYPoderActual / 2) {
			return encontrado;
		}
		return null;
	}

	/**
	 * calcula el indice actual de salubridad/poder que le permite recoger el
	 * alimento, o no y al final de su vida, crear un objeto de tipo IdoneidadADN
	 * 
	 * @return
	 */
	private float getIndiceSalubridadPoderMedio() {
		float sumatorio = 0;
		// java 8
		for (Alimento alimento : recogidos) {
			sumatorio += alimento.getIndiceSalubridadPoder();
		}
		if (recogidos.isEmpty())
			return 0;
		return sumatorio / recogidos.size();
	}

	private int getVidaAleatoria(int vidaMin, int vidaMax) {
		return new Random().nextInt(vidaMax - vidaMin) + vidaMin;
	}

	/**
	 * lleva el alimento recogido a la despensa
	 * 
	 * @param alimento
	 */
	private void acarrear(Alimento alimento) {
		hormiguero.descargar(alimento);
	}

	boolean isAlive() {
		return vida > edad;
	}

	
	

}
