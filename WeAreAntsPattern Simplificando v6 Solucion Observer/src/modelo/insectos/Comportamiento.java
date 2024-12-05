package modelo.insectos;

public abstract class Comportamiento {
	Hormiga hormiga;

	public Comportamiento() {
		super();
	}

	public void setHormiga(Hormiga hormiga) {
		this.hormiga = hormiga;
	}

	public abstract void hacerTarea();
}
