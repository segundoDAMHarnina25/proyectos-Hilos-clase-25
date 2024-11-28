package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CriaderoTest {

	@Test
	void testCriadero() {
		Hormiguero hormiguero=new Hormiguero();
		//pata, poder de cien, cantidad para criar hormiga 1000
		//con esto puede crear solo una hormiga
		int anterior=hormiguero.getHormigas().size();
		hormiguero.getDespensa().put(Alimento.pata, 10);		
		Criadero criadero=new Criadero(hormiguero);
		int posterior=hormiguero.getHormigas().size();
		assertEquals(anterior+1, posterior);
		assertEquals(0,hormiguero.getDespensa().get(Alimento.pata));
	}

}
