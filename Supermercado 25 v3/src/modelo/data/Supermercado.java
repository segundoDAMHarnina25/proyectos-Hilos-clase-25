package modelo.data;

import java.util.List;
import java.util.concurrent.Future;

import modelo.repos.ArticulosEnStrockRepositorio;
import modelo.repos.ClientProvider;

public class Supermercado {
	private ArticulosEnStrockRepositorio articulosEnStrockRepositorio;
	private List<Cajere> cajas;
	private ClientProvider provider;
	private Future<Cart> compras;
}
