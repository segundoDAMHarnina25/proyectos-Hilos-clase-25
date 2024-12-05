package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Barco;
import modelo.Cardumen;
import modelo.Coordenada;
import modelo.EspeciePez;
import modelo.Puerto;
import modelo.TipoBarco;

class BarcoTest {
	List<Cardumen> cardumenes;
	Barco barco;
	Puerto puerto;
	Cardumen cardumen;

	@BeforeEach
	void before() {
		cardumenes=new ArrayList();
		cardumenes.add(new Cardumen(EspeciePez.ATUN, new Coordenada(0, 0), 1000, 10));
		puerto=new Puerto(1l,"Siracusa", new Coordenada(100,100));
		barco = new Barco("Cosica", new Coordenada(50, 50), puerto, 500, 3, TipoBarco.PALANGRE);
		cardumen = cardumenes.get(0);
	}

	@Test
	void testMoverse() {
		
		do {
			barco.mover();
		} while (!barco.posicionActual.equals(cardumen.getPosicion()));
		assertEquals(barco.posicionActual, cardumen.getPosicion());
	}

	@Test
	void testMoverseConCardumen() {
		do {
			cardumen.mover();
			barco.mover();
		} while (!barco.posicionActual.equals(cardumen.getPosicion()));
		assertEquals(barco.posicionActual, cardumen.getPosicion());
	}

	@Test
	void testPescarCapacidadSuficiente() {
		do {
			barco.mover();
		} while (!barco.posicionActual.equals(cardumen.getPosicion()));
		barco.pescar();
		assertEquals(barco.getPescaActual(), barco.getCapacidad());
	}

	@Test
	void testPescarCapacidadExcedida() {
		int capacidad = 1500;
		barco = new Barco("Cosica", new Coordenada(50, 50), puerto, capacidad, 3,TipoBarco.PALANGRE);
		barco.decidirCardumen(cardumenes);
		try {
			// porque el barco tiene mucha capacidad para el cardumen
			barco.pescar();
			fail();
		} catch (Exception e) {
		}
		assertTrue(barco.getObjetivo()!=null);
	}
	
	@Test
	void testPescarCapacidadaCorrectaCardumenYaPescado() {
		barco.pescar();
		//no deberia dejar pescar porque no respetas el indiceBiologico
		int capacidad = 500;
		barco = new Barco("Cosica", new Coordenada(50, 50), 
				puerto, capacidad, 3,
				TipoBarco.PALANGRE);
		barco.decidirCardumen(cardumenes);
		barco.pescar();
		assertNotEquals(barco.getCapacidad(),barco.getPescaActual());
	}
}
