package modelo.repos;

import java.util.Random;

import modelo.data.Cart;

public class CartOM {

	public static Cart getListaCompra(int elementos,int cantidad) {
		ArticuloEnStockOM articuloEnStockOM = new ArticuloEnStockOM();
		Cart respuesta=new Cart();
		for (int i = 0; i < elementos; ) {
			if(respuesta.agregar(articuloEnStockOM.getRandomOne(), new Random().nextInt(cantidad)+1)) i++;
		}
		return respuesta;
	}

}
