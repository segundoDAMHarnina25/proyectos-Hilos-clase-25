package modelo.data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import modelo.repos.ArticulosEnStrockRepositorio;
import modelo.repos.CartOM;
import modelo.repos.CartOMDummy;

public class Cliente implements Callable {
	private final String creditCard;
	private Cart carrito;
	private Cart listaCompra;
	private boolean listaAleatoria = true;
	private int elementosAleatoriosMaximos = 5;
	private int cantidadaleatoriaMaxima = 5;
	private ArticulosEnStrockRepositorio articulosEnStrockRepositorio;
	private LocalTime inicial;
	private Queue<Cliente> clientes;

	private Cliente(String creditCard, ArticulosEnStrockRepositorio articulosEnStrockRepositorio) {
		super();
		this.creditCard = creditCard;
		this.articulosEnStrockRepositorio = articulosEnStrockRepositorio;
	}

	public Cliente(String creditCard, ArticulosEnStrockRepositorio articulosEnStrockRepositorio,
			Queue<Cliente> clientes) {
		this(creditCard, articulosEnStrockRepositorio);
		this.clientes = clientes;
	}

	public void configurarListaAleatoria(int elementos, int cantidadMaxima) {
		elementosAleatoriosMaximos = elementos;
		cantidadaleatoriaMaxima = cantidadMaxima;
	}

	@Override
	public Cart call() throws Exception {
		iniciarCompra();
		while (!listaCompra.isEmpty()) {
			hacerCompra();
		}
		hacerCola();
		return carrito;
	}

	private void hacerCompra() {
		Entry<Articulo, Integer> first = listaCompra.getFirst();
		listaCompra.remove(first);
		synchronized (articulosEnStrockRepositorio) {
			if (articulosEnStrockRepositorio.comprar(first.getKey(), first.getValue())) {
				carrito.agregar(first.getKey(), first.getValue());
			}
		}
	}

	private void setListaAleatoria(boolean listaAleatoria) {
		this.listaAleatoria = listaAleatoria;
	}

//Esto se aplica cuando no queremos la lista aleatoria. Es bueno para hacer test
	private void asignarListaCompra(Cart listaCompra) {
		this.setListaAleatoria(false);
		this.listaCompra = listaCompra;
	}

	private void iniciarCompra() {
		this.carrito = new Cart();
		if (listaAleatoria) {
			this.listaCompra = CartOMDummy.getListaCompra(elementosAleatoriosMaximos, cantidadaleatoriaMaxima);
		}
	}

	private boolean agregarCarrito(Articulo articulo, int cantidad) {
		return carrito.agregar(articulo, cantidad);
	}

	private String getCreditCard() {
		return creditCard;
	}

	private void hacerCola() {
		clientes.add(this);
	}

	private List<Entry<Articulo, Integer>> getListaCompra() {
		return set2List(listaCompra.get());
	}

	public List<Entry<Articulo, Integer>> getCarrito() {
		return set2List(carrito.get());
	}

	private List set2List(Set set) {
		return (List) set.stream().collect(Collectors.toList());
	}

	public boolean pagar(double compra) {
		int saldo=10;
		return saldo>=compra;
	}

}
