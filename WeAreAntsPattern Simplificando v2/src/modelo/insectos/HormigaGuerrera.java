package modelo.insectos;

public class HormigaGuerrera extends Hormiga {
	private final int incrementoGuerrero=2;
	public HormigaGuerrera(long id) {
		super(id);
	}

	@Override
	public void hacerTarea() {
		recorre();
	}

	private void recorre()  {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		incrementaEdad(incrementoGuerrero);
	}


}
