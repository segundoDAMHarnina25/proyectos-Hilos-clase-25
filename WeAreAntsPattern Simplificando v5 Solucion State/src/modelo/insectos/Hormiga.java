package modelo.insectos;

import modelo.soporte.Alimento;
import modelo.soporte.HormigaData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hormiga extends Comportamiento implements Vivible {

	PropertyChangeSupport miEvent=new PropertyChangeSupport(this);
	List<Alimento> alimentos;
	private final int maxima=50;
	private int vida = new Random().nextInt(maxima)+1;
	private int edad = 0;
	public long id;
	protected int incrementoVidaPorDefecto = 1;
	private Comportamiento comportamiento;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		miEvent.addPropertyChangeListener(listener);
	}

	public Hormiga(long id) {
		super();
		this.id = id;
		this.comportamiento=new Recolectora(this);
		alimentos = new ArrayList();
	}

	public void setComportamiento(Comportamiento comportamiento) {
		this.comportamiento = comportamiento;
	}

	@Override
	public void hacerTarea() {
		comportamiento.hacerTarea();
		incrementaEdad(incrementoVidaPorDefecto);
		if(!isAlive()){
			this.miEvent.firePropertyChange("vida",true,new HormigaData(alimentos,vida,id));
		}
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

	public void addAlimento(Alimento randomAlimento) {
		this.alimentos.add(randomAlimento);
	}
}
