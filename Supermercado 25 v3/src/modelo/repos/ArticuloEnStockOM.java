package modelo.repos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import modelo.data.Articulo;

public class ArticuloEnStockOM {

	private String[] nombres = { "pan", "agua", "chocolate", "fresa", "sardina", "pollo", "aguacate", "limon", "mero",
			"filete", "pechuga", "muslo", "arenque", "huevos", "leche", "harina", "te", "arroz", "lentejas",
			"garbanzos", "alubias" };
	private float[] precios = { 2, 3, 4, 2, 3, 4, 2, 3, 4, 5, 6, 3, 4, 6, 7, 3, 2, 3, 4, 6, 7, 4, 3, 2 };
	private ArrayList<Articulo> articulos;

	public ArticuloEnStockOM() {
		super();
		articulos = new ArrayList<>();
		for (int i = 0; i < nombres.length; i++) {
			articulos.add(new Articulo(String.valueOf(i + 1), nombres[i], precios[i]));
		}
	}

	public List<Articulo> getAll() {
		return articulos;
	}

	public Articulo getRandomOne() {
		return getAll().get(new Random().nextInt(articulos.size()));
	}

	public ArrayList<Articulo> cloneArticulos() throws CloneNotSupportedException {
		return (ArrayList<Articulo>) articulos.clone();
	}
}
