package modelo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import objectsmothers.HormigasOM;

public class Criadero implements Callable<List<Hormiga>> {

	Map<Alimento, Integer> despensa;
	List<Hormiga> hormigas;
	List<IdoneidadADN> idoneidades;
	Hormiguero hormiguero;
	
	
	public Criadero(Map<Alimento, Integer> despensa, List<Hormiga> hormigas, List<IdoneidadADN> idoneidades,
			Hormiguero hormiguero) {
		super();
		this.despensa = despensa;
		this.hormigas = hormigas;
		this.idoneidades = idoneidades;
		this.hormiguero = hormiguero;
	}

	@Override
	public List<Hormiga> call() {
		eliminar();
		hormigas.addAll(criar());
		System.out.println("finalizando la cria");
		return hormigas;
	}
	
	private void eliminar() {
		for (Iterator iterator = hormigas.iterator(); iterator.hasNext();) {
			Hormiga hormiga = (Hormiga) iterator.next();
			if (!hormiga.isAlive())
				iterator.remove();
		}
	}
	
	private List<Hormiga> criar() {
		long sumatorio = 0;
		for (Map.Entry<Alimento, Integer> entry : despensa.entrySet()) {
			Alimento key = entry.getKey();
			// el inidice se salubridad es solo para saber si el adn es guay para
			// seleccionar
			int poder = key.getPoder();
			Integer val = entry.getValue();
			sumatorio += poder * val;
		}
		long cantidadHormigas = sumatorio / Hormiga.cantidadPoderNacimiento;
		IdoneidadADN idoneidadADN = obtenerMasIdoneo(idoneidades);
		vaciarDespensa();
		return HormigasOM.getHormigasInicio(cantidadHormigas, idoneidadADN.getAdn(),hormiguero);
	}
	
	private void vaciarDespensa() {
		for (Map.Entry<Alimento, Integer> entry : despensa.entrySet()) {
			entry.setValue(0);
		}
	}

	private IdoneidadADN obtenerMasIdoneo(List<IdoneidadADN> idoneidades) {
		IdoneidadADN idoneo = idoneidades.get(0);
		for (int i = 1; i < idoneidades.size(); i++) {
			idoneo = idoneidades.get(i).isMejor(idoneo);
		}
		return idoneo;
	}

}
