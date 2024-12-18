package modelo.data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
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

	public Cliente(String creditCard, ArticulosEnStrockRepositorio articulosEnStrockRepositorio) {
		super();
		this.creditCard = creditCard;
		this.articulosEnStrockRepositorio = articulosEnStrockRepositorio;
	}

	public void configurarListaAleatoria(int elementos, int cantidadMaxima) {
		elementosAleatoriosMaximos = elementos;
		cantidadaleatoriaMaxima = cantidadMaxima;
	}

	@Override
	public Object call() throws Exception {
		iniciarCompra();
		while (!listaCompra.isEmpty()) {
			hacerCompra();
		}
		pagarCompra();
		return carrito;
	}
	

	public void hacerCompra() {
		Entry<Articulo, Integer> first = listaCompra.getFirst();
		listaCompra.remove(first);
		synchronized (articulosEnStrockRepositorio) {
			if (articulosEnStrockRepositorio.comprar(first.getKey(), first.getValue())) {
				carrito.agregar(first.getKey(), first.getValue());
			}
		}

	}

	public void setListaAleatoria(boolean listaAleatoria) {
		this.listaAleatoria = listaAleatoria;
	}

//Esto se aplica cuando no queremos la lista aleatoria. Es bueno para hacer test
	public void asignarListaCompra(Cart listaCompra) {
		this.setListaAleatoria(false);
		this.listaCompra = listaCompra;
	}

	public void iniciarCompra() {
		this.carrito = new Cart();
		if (listaAleatoria) {
			this.listaCompra = CartOMDummy.getListaCompra(elementosAleatoriosMaximos, cantidadaleatoriaMaxima);
		}
	}

	public boolean agregarCarrito(Articulo articulo, int cantidad) {
		return carrito.agregar(articulo, cantidad);
	}

	private String getCreditCard() {
		return creditCard;
	}

	public boolean pagarCompra() {
		System.out.println("soy "+creditCard+" y he comprado "+carrito.size()+" cosas");
		System.out.println("he tardado "+Duration.between(inicial, LocalTime.now()));
		return false;
	}

	public List<Entry<Articulo, Integer>> getListaCompra() {
		return set2List(listaCompra.get());
	}

	public List<Entry<Articulo, Integer>> getCarrito() {
		return set2List(carrito.get());
	}

	private List set2List(Set set) {
		return (List) set.stream().collect(Collectors.toList());
	}

	

}
