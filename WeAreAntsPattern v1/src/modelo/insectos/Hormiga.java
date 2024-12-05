package modelo.insectos;

public abstract class Hormiga implements Vivible{
	private int vida = 100;
	private int edad = 0;
	public long id;
	protected int incrementoVidaPorDefecto=1;
	public Hormiga(long id) {
		super();
		this.id = id;
	}
	
	public abstract void hacerTarea();
	
	@Override
	public boolean isAlive() {
		return vida > edad;
	}

	protected void incrementaEdad(int i) {
		this.edad+=i;
	}
	
}
