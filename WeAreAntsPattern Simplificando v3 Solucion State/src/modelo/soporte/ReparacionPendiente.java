package modelo.soporte;

public class ReparacionPendiente {
	private boolean pendiente;
	private int cantidadPendiente;
	
	public ReparacionPendiente(boolean pendiente, int cantidadPendiente) {
		super();
		this.pendiente = pendiente;
		this.cantidadPendiente = cantidadPendiente;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public int getCantidadPendiente() {
		return cantidadPendiente;
	}

	public void repara() {
		cantidadPendiente--;
		if(cantidadPendiente==0) pendiente=false;
	}
	
	
	
}
