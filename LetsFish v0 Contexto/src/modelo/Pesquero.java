// Interface Pesquero
package modelo;

import java.util.List;
import java.util.Optional;

interface Pesquero {
	/**
	 * Decide un cardumen adecuado segun criterios internos
	 * entre a lista pasada por parametros
	 * @param cardumenes
	 */
	void decidirCardumen(List<Cardumen> cardumenes);

	/**
	 * se encarga de cambiar la posicion del Pesquero a 
	 * una coordenada seleccionada segun criterios internos
	 */
	void mover();

	/**
	 * El Pesquero va a recoger peces hasta que cumpla
	 * condiciones segundo criterios internos
	 */
	void pescar();

	/**
	 * Se actualiza la posicion actual del Pesquero, cuando
	 * termina su actividad, a la posicion de su puerto base
	 */
	void regresarAPuerto();
}