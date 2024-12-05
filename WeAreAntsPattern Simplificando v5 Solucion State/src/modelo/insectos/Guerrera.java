package modelo.insectos;

public class Guerrera extends Comportamiento implements Vivible {
	private final int incrementoGuerrero = 2;
	private Hormiga hormiga;

	public Guerrera(Hormiga hormiga) {
		this.hormiga = hormiga;
	}

	@Override
	public void hacerTarea() {
		recorre();
	}

	private void recorre() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("hace cosas de guerera");
		this.hormiga.incrementaEdad(incrementoGuerrero);
	}

	@Override
	public boolean isAlive() {
		return this.hormiga.isAlive();
	}

}
