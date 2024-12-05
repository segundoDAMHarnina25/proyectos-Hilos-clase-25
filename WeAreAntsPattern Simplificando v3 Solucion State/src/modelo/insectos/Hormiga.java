package modelo.insectos;

public abstract class Hormiga extends Comportamiento implements Vivible {
	private int vida = 100;
	private int edad = 0;
	public long id;
	protected int incrementoVidaPorDefecto = 1;
	private Comportamiento comportamiento;

	public Hormiga(long id) {
		super();
		this.id = id;
		this.comportamiento=new Recolectora();
	}

	public void setComportamiento(Comportamiento comportamiento) {
		this.comportamiento = comportamiento;
	}

	@Override
	public void hacerTarea() {
		comportamiento.hacerTarea();
	};

	@Override
	public boolean isAlive() {
		return vida > edad;
	}

	protected void incrementaEdad(int i) {
		this.edad += i;
	}

	public Comportamiento getComportamiento() {
		return this.comportamiento;
	}

}
