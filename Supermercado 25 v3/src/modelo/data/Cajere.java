package modelo.data;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;

import modelo.repos.ArticulosEnStrockRepositorio;

public class Cajere implements Runnable {
	private final String DNI;
	private int numeroCaja;
	private float lapso;
	private Queue<Cliente> clientes;
	private boolean trabaja=true;
	private ArticulosEnStrockRepositorio articulosEnStrockRepositorio;

	public Cajere(String dNI, int numeroCaja, float lapso, Queue<Cliente> clientes,ArticulosEnStrockRepositorio articulosEnStrockRepositorio) {
		super();
		DNI = dNI;
		this.numeroCaja = numeroCaja;
		this.lapso = lapso;
		this.clientes = clientes;
		this.articulosEnStrockRepositorio=articulosEnStrockRepositorio;
	}

	@Override
	public void run() {
		while (trabaja) {
			if (!clientes.isEmpty()) {
				Cliente cliente = clientes.poll();
				double compra = procesarCompra(cliente.getCarrito());
				if(!cliente.pagar(compra)) {
					articulosEnStrockRepositorio.devolverCompra(cliente.getCarrito());
				}
			}
		}
	}

	private double procesarCompra(List<Entry<Articulo, Integer>> carrito) {
		return carrito.stream()
				.mapToDouble(articulo -> {
						return articulo.getKey().getPrecio() * articulo.getValue();
					})
				.sum();
	}

	private int getNumeroCaja() {
		return numeroCaja;
	}

	private void setNumeroCaja(int numeroCaja) {
		this.numeroCaja = numeroCaja;
	}

	private String getDNI() {
		return DNI;
	}

	private float getLapso() {
		return lapso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DNI);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cajere other = (Cajere) obj;
		return Objects.equals(DNI, other.DNI);
	}

}
