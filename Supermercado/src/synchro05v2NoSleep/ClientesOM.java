package synchro05v2NoSleep;

import java.util.Optional;
import java.util.Random;

public class ClientesOM {

	public static Optional<Cliente> getRandomClient() {
		Cliente retorno=new Cliente("cliente "+new Random().nextInt(100), new int[] {1,1,1});
		return new Random().nextBoolean()?Optional.ofNullable(retorno):Optional.ofNullable(null);
	}
	public static Optional<Cliente> getRandomClientNotOptional() {
		return Optional.of(new Cliente("cliente "+new Random().nextInt(100), new int[] {1,1,1}));
	}
}
