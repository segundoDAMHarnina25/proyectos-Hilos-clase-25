package modelo.repos;

import java.util.List;
import java.util.Random;

import modelo.data.Articulo;
import modelo.data.Cart;

public class CartOMDummy extends CartOM {
	public static Cart getListaCompra(int elementos,int cantidad) {
		ArticuloEnStockOM articuloEnStockOM = new ArticuloEnStockOM();
		Cart respuesta=new Cart();
		List<Articulo> all = articuloEnStockOM.getAll();
		for (int i = 0; i < elementos; ) {
			if(respuesta.agregar(all.get(i), 1)) i++;
		}
		return respuesta;
	}
}
