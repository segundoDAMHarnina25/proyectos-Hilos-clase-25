package presentacion;

import java.util.Optional;
import java.util.Random;

public class ClientesOM {

	public static Optional<Cliente> getRandomClient() {
		int max = 3;
		int bound = 100;
		return Optional.of(new Cliente("cliente "+new Random().nextInt(bound),
				new int[] { getRandomValue(max),
							getRandomValue(max),
							getRandomValue(max)}));
	}
	public static int getRandomValue(int max) {
		return new Random().nextInt(max)+1;
	}
	public static Optional<Cliente> getRandomClientPossiblyNull() {
		Cliente retorno=new Cliente("cliente "+new Random().nextInt(100), new int[] {1,1,1});
		return new Random().nextBoolean()?Optional.ofNullable(retorno):Optional.ofNullable(null);
	}
	
	public void dimequesi(int condicion,int dos) {
		boolean resultado=condicion>dos;
		 String cosa=resultado?"es verdadero":"es falso";
	}
}
